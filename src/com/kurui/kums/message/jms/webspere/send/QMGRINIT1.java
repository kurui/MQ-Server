package com.kurui.kums.message.jms.webspere.send;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 2009-4-12
 * Time: 9:17:38
 * To change this template use File | Settings | File Templates.
 */

import com.ibm.mq.MQQueueManager;
import com.ibm.mq.MQException;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQC;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA. User: davidyu Date: 2009-4-10 Time: 15:10:13 To
 * change this template use File | Settings | File Templates.
 */
public class QMGRINIT1 {
    private static Map MQQueueMap = new HashMap();//MQ���е�MAP
    private static int QueueCount;
    public static Map MQUseFlagMap = new HashMap();//mq�����Ƿ�ʹ��

    public static MQQueueManager getT() {
        if (t == null) {
            try {
                System.out.println("���й������ʼ����ʼ");
                MQEnvironment.hostname = "156.16.20.22";
                MQEnvironment.port = 9009;
                MQEnvironment.channel = "SVRCONN";
                MQEnvironment.CCSID = 819;
                MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,
                        MQC.TRANSPORT_MQSERIES_CLIENT);
                for (int i = 0; i < 5; i++) {
                    MQQueueManager a = new MQQueueManager("QM_TIPS_2027100000_01");
                    System.out.println("����" + i + "�����ɹ�");
                    MQQueueMap.put(String.valueOf(i), a);
                    MQUseFlagMap.put(String.valueOf(i), "0");
                }
                System.out.println("���й������ʼ������");
            } catch (MQException e) {
                e.printStackTrace();
            }
        }
        int g = getCount();
        if((Thread.currentThread() instanceof AbsThread1)) {
            ((AbsThread1) Thread.currentThread()).setMqCount(g);
        }
        if (g >= 5) {
            //50����ж���ռ�ã���Ҫ�³�ʼ��һ�����
            try {
                System.out.println("���й�����ȫ��ʹ���У���Ҫ�������");
                t = getNewMQQueue();
            } catch (MQException e) {
                System.out.println("�������ʧ��" + e);
            }
        } else {
            System.out.println("QueueCount=" + g);
            t = (MQQueueManager) MQQueueMap.get(String.valueOf(g));
            if (!t.isConnected()) {
                System.out.println("���й������Ѿ��رգ���Ҫ�������");
                try {
                    t.disconnect();
                } catch (MQException e) {
                }
                try {
                    t = getNewMQQueue();
                } catch (MQException e) {
                    System.out.println("�������ʧ��1" + e);
                }
            }
        }

        return t;
    }
    /**
     * ѭ��ȡ��ʼ�����У�ȡ��ǰ����ʹ�õĶ��й�����
     * ����������ʼ�����У����������ʵĻ�������
     */
    private synchronized static int getCount() {
        int ct = QueueCount;
        int i = 0;
        for (; i < 5; i++) {
            ct = QueueCount;
            QueueCount++;
            if (QueueCount == 5) QueueCount = 0;
            if ((MQUseFlagMap.get(String.valueOf(ct))).equals("0")) {
                MQUseFlagMap.put(String.valueOf(ct), "1");
                break;
            }
        }
        if (i >= 5) {
            ct = i;
            QueueCount++;
        }
        return ct;
    }

    public static MQQueueManager t;
    /**
     * ��һ���µ�MQ���й�����
     */
    public static MQQueueManager getNewMQQueue() throws MQException {
        MQEnvironment.hostname = "156.16.20.22";
        MQEnvironment.port = 9009;
        MQEnvironment.channel = "SVRCONN";
        MQEnvironment.CCSID = 819;
        MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,
                MQC.TRANSPORT_MQSERIES_CLIENT);
        MQQueueManager a = null;
        a = new MQQueueManager("QM_TIPS_2027100000_01");
        System.out.println("������й�����ɹ�");
        return a;
    }

    /**
     * �رմ򿪵�MQ���У��ֿͻ��˳��ʱ�����
     */
    public synchronized static void closeMQQueue(){
        if(!MQQueueMap.isEmpty()){
            for(int i=0;i<5;i++){
               MQQueueManager mqQueue= (MQQueueManager)MQQueueMap.get(String.valueOf(i));
               if(mqQueue.isConnected())
                   try {
                       mqQueue.disconnect();
                   } catch (MQException e) {
                   }
            }
        }
    }

}
