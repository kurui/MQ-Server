package com.kurui.kums.message.jms.webspere.send;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 2009-4-12
 * Time: 9:16:50
 * To change this template use File | Settings | File Templates.
 */

import java.io.IOException;
import java.util.Date;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class SendService extends AbsThread1 {
    /* ���ô�ѡ���Ա���������Ķ��У������й���������ֹͣ������Ҳ��������ѡ��ȥӦ�Բ��ɹ������ */
    private static int openSendOptions = MQC.MQOO_OUTPUT
            | MQC.MQOO_FAIL_IF_QUIESCING | MQC.MQOO_SET_IDENTITY_CONTEXT;
    private static int openRcvOptions = MQC.MQOO_INPUT_SHARED
            | MQC.MQOO_FAIL_IF_QUIESCING;

    private int count;

    public void run() {
        MQEnvironment.hostname = "156.16.20.22";
        MQEnvironment.port = 9009;
        MQEnvironment.channel = "SVRCONN";
        MQEnvironment.CCSID = 819;
        MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,
                MQC.TRANSPORT_MQSERIES_CLIENT);
        MQQueueManager qMgr = null;
        MQQueue sendQueue = null;
        MQQueue rcvQueue = null;
        try {
            System.out.println("111 ==========��" + count + "�������:" + new Date());
            qMgr = QMGRINIT1.getT();
            String mqct = "";
            if (mqCount >= 5) mqct = "�����";
            else
                mqct =  mqCount+"��";
            System.out.println("��" + count + "�������:����ʹ�õ�" + mqct + "���й�������ж��з���");
            System.out.println("222 ==========��" + mqCount + "�������:" + new Date());
            /*���÷�����Ϣѡ�ʹ��Ĭ������*/
            MQPutMessageOptions pmo = new MQPutMessageOptions();
            pmo.options = pmo.options + MQC.MQPMO_NEW_MSG_ID;
            pmo.options = pmo.options + MQC.MQPMO_SYNCPOINT;
            pmo.options = pmo.options + MQC.MQPMO_SET_IDENTITY_CONTEXT;
            sendQueue = qMgr.accessQueue("PBC.EXT.ONLINE.IN",
                    openSendOptions, null, null, null);
            /*���÷�����Ϣѡ�ʹ��Ĭ������*/
            MQGetMessageOptions gmo = new MQGetMessageOptions();
            /*��ͬ��������»�ȡ��Ϣ*/
            gmo.options = gmo.options + MQC.MQGMO_SYNCPOINT;
            /*����ڶ�����û����Ϣ��ȴ�*/
            gmo.options = gmo.options + MQC.MQGMO_WAIT;
            /*�����й�����ͣ����ʧ��*/
            gmo.options = gmo.options + MQC.MQGMO_FAIL_IF_QUIESCING;
            gmo.matchOptions = MQC.MQMO_MATCH_CORREL_ID;
            /*���õȴ��ʱ������*/
            gmo.waitInterval = 60000;
            System.out.println("333 ==========��" + count + "�������:" + new Date());
            rcvQueue = qMgr.accessQueue("PBC.202710000010.ONLINE.OUT",
                    openRcvOptions, null, null, null);
            System.out.println("444 ==========��" + count + "�������:" + new Date());
            String msgID = String.valueOf(new Date().getTime()+"="+Math.random()*10000000);
            System.out.println("msgID="+msgID);
            MQMessage sendMessage = new MQMessage();
            sendMessage.messageId = msgID.getBytes();
            sendMessage.correlationId = "REQ000000000000000000000".getBytes();
            sendMessage.format = MQC.MQFMT_NONE;
            sendMessage.userId = "CHINATAX";
            sendMessage.expiry = 600;
            sendMessage.write("myfriend2010������Ϣ����й�����".getBytes("GBK"));
            System.out.println("555 ==========��" + count + "�������:" + new Date());
            sendQueue.put(sendMessage, pmo);
            qMgr.commit();
            System.out.println("666 ==========��" + count + "�������:" + new Date());
            MQMessage getMessage = new MQMessage();
            getMessage.messageId = MQC.MQMI_NONE;
            getMessage.correlationId = sendMessage.messageId;
            System.out.println("777 ==========��" + count + "�������:" + new Date());
            rcvQueue.get(getMessage, gmo);
            byte[] msgBuffer = new byte[getMessage.getMessageLength()];
            getMessage.readFully(msgBuffer);
            String string = new String(msgBuffer, "GBK");
            System.out.println("���ձ���string=" + string);
            qMgr.commit();
        } catch (MQException e) {
            System.out.println("888 ==========��" + count + "������ݳ�?ʱ��:" + new Date());
            e.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (sendQueue != null) sendQueue.close();
                if (rcvQueue != null) rcvQueue.close();
                if (mqCount >= 5) {
                    System.out.println("��" + count + "�������:�ر��´򿪵Ķ��й�����");
                    if (qMgr != null) qMgr.disconnect();
                } else {
                    System.out.println("��" + count + "�������:�ͷŶ��й�����" + mqCount);
                    QMGRINIT1.MQUseFlagMap.put(String.valueOf(mqCount), "0");
                }
//                qMgr.disconnect();
                System.out.println("end ==========��" + count + "�������:" + new Date());
            } catch (MQException e) {
                e.printStackTrace();
                System.out.println("reasonCode=" + e.reasonCode);
            }
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}