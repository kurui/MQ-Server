package com.kurui.kums.message.jms.webspere.receive;

/**
 * Created by IntelliJ IDEA.
 * User: Z.X.T
 * Date: 2009-3-6
 * Time: 17:59:14
 * To change this template use File | Settings | File Templates.
 */

import com.ibm.mq.*;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther Z.X.T
 * @creaton 2009-3-5 ������MQ����Դ�������ĳһ������Ϸ��������Ϣ�������Ϣ�� ���Է��������Ϣ�����Ƚ��ȳ�ķ�ʽȡ��
 */
public class MQTest implements Runnable {


    String qManager = "QM_TIPS_402100000010_01";// QueueManager��

    private MQQueueManager qMgr;

    private MQQueue sQueue;

    private MQQueue rQueue;

    private MQMessage retrievedMessage;

    String HOST_NAME = "127.0.0.1";

    public boolean isContFlag() {
        return contFlag;
    }

    public void setContFlag(boolean contFlag) {
        this.contFlag = contFlag;
    }

    private boolean contFlag = true;

    int PORT = 1415;

    String R_NAME = "PBC.402100000010.ONLINE.OUT";

    String S_NAME = "PBC.EXT.ONLINE.IN";

    String CHANNEL = "SVRCONN1";

    int CCSID = 819;// ��ʾ�Ǽ������ģ�

    // CCSID��ֵ��AIX��һ����Ϊ1383�����Ҫ֧��GBK����Ϊ1386����WIN����Ϊ1381��

    public void init() {

        try {
            qMgr = new MQQueueManager(qManager);
        } catch (MQException e) {
            System.out.println("�򿪶��й�������д��� : Completion code "
                    + e.completionCode + " Reason Code is " + e.reasonCode);
        }
        JOptionPane.showMessageDialog(null, "����������");
    }

    void finalizer() {
        try {
            qMgr.disconnect();
        } catch (MQException e) {
            System.out.println("�ر�MQ���� : Completion code " + e.completionCode
                    + " Reason Code is " + e.reasonCode);
        }
    }

     /**//*
		 * ȡ����
		 */
    public String GetMsg() {
        String msgStr = null;
        try {
            retrievedMessage = new MQMessage();
            MQGetMessageOptions gmo = new MQGetMessageOptions();
            gmo.options += MQC.MQPMO_NO_SYNCPOINT;// ��ͬ��������»�ȡ��Ϣ
            gmo.options = gmo.options + MQC.MQGMO_WAIT;// ����ڶ�����û����Ϣ��ȴ�
            gmo.options = gmo.options + MQC.MQGMO_FAIL_IF_QUIESCING;// �����й�����ͣ����ʧ��
            gmo.waitInterval = 1999999999;// ���õȴ��ʱ������
            getRQueue().get(retrievedMessage, gmo);
            qMgr.commit();
            int length = retrievedMessage.getDataLength();
            byte[] msg = new byte[length];
            retrievedMessage.readFully(msg);
            String string = new String(msg, "GBK");
            System.out.println(string);
            msgStr = string;
        }
        catch (MQException e) {
            if (e.reasonCode != 2033) // û����Ϣ
            {
                e.printStackTrace();
                System.out.println("MQ���մ��� : Completion code "
                        + e.completionCode + " Reason Code is " + e.reasonCode);
            }
        } catch (java.io.IOException e) {
            System.out.println("��ȡ��Ϣ���� " + e);
        }
        return msgStr;
    }

    public void SendMsg(byte[] qByte) {
        try {
            MQMessage qMsg = new MQMessage();
            qMsg.messageId = MQC.MQMI_NONE;
            qMsg.correlationId = retrievedMessage.messageId;
            qMsg.write(qByte);
            MQPutMessageOptions pmo = new MQPutMessageOptions();
            pmo.options = pmo.options + MQC.MQPMO_NEW_MSG_ID;
            pmo.options = pmo.options + MQC.MQPMO_NO_SYNCPOINT;
            pmo.options = pmo.options + MQC.MQPMO_SET_IDENTITY_CONTEXT;
            getSQueue().put(qMsg, pmo);
            qMgr.commit();
            System.out.println("The message is sent!");
            System.out.println("\tThe message is " + new String(qByte, "GBK"));
        } catch (MQException e) {
            System.out
                    .println("A WebSphere MQ error occurred : Completion code "
                            + e.completionCode + " Reason Code is "
                            + e.reasonCode);
        } catch (java.io.IOException e) {
            System.out
                    .println("An error occurred whilst to the message buffer "
                            + e);
        }

    }

    public MQQueueManager getQMgr() {
        if (qMgr == null)
            init();
        return qMgr;
    }

    public MQQueue getSQueue() {
        int openSendOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING
                | MQC.MQOO_SET_IDENTITY_CONTEXT;
        try {
            sQueue = getQMgr().accessQueue(S_NAME, openSendOptions);
        } catch (MQException e) {
            e.printStackTrace();
            return null;
        }
        return sQueue;
    }

    public MQQueue getRQueue() {
        int openRcvOptions = MQC.MQOO_INPUT_AS_Q_DEF
                | MQC.MQOO_FAIL_IF_QUIESCING;
        try {
            rQueue = getQMgr().accessQueue(R_NAME, openRcvOptions);
        } catch (MQException e) {
            e.printStackTrace();
            return null;
        }
        return rQueue;
    }

    public String getQManager() {
        return qManager;
    }

    public void setQManager(String qManager) {
        this.qManager = qManager;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public String getR_NAME() {
        return R_NAME;
    }

    public void setR_NAME(String r_NAME) {
        R_NAME = r_NAME;
    }

    public String getS_NAME() {
        return S_NAME;
    }

    public void setS_NAME(String s_NAME) {
        S_NAME = s_NAME;
    }

    public String getCHANNEL() {
        return CHANNEL;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }

    public int getCCSID() {
        return CCSID;
    }

    public void setCCSID(int CCSID) {
        this.CCSID = CCSID;
    }

    public String getHOST_NAME() {
        return HOST_NAME;
    }

    public void setHOST_NAME(String HOST_NAME) {
        this.HOST_NAME = HOST_NAME;
    }

    public void run() {
        MQEnvironment.hostname = HOST_NAME; // ���bMQ���ڵ�ip address
        MQEnvironment.port = PORT; // TCP/IP port
        MQEnvironment.channel = CHANNEL;
        MQEnvironment.CCSID = CCSID;
        MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,
                MQC.TRANSPORT_MQSERIES_CLIENT);
        init();
        try {
            while (contFlag) {
                String readMsg = GetMsg();
                if (readMsg != null) {
                    System.out.println("z");
                    SendMsg(readMsg.getBytes("GBK"));
                    SskkRcvServerFrame.getRowData().add(new String[]{(new SimpleDateFormat("yyyy-MM-dd kk:mm:ss")).format(new Date()).toString(), readMsg, "ok"});
                    SskkRcvServerFrame.getTm().fireTableDataChanged();
                }
                try {
                    sQueue.close();
                    rQueue.close();
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finalizer();
    }
}
