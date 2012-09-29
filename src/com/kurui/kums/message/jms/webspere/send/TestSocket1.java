package com.kurui.kums.message.jms.webspere.send;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 2009-4-12
 * Time: 16:42:52
 * To change this template use File | Settings | File Templates.
 */
public class TestSocket1 {

    ServerSocket t;
    public boolean init() {
        try {
             t= new ServerSocket(9104);
            int i = 0;
            while (true) {
                i++;
                Socket tt = t.accept();
                NewThreadSend1 q = new NewThreadSend1();
                q.setTs(tt);
                q.setCount(i);
                q.start();
            }
        } catch (IOException e) {
            System.out.println("�ر�");
            if(t!=null&&!t.isClosed())
                try {
                t.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            QMGRINIT1.closeMQQueue();//�ر�MQ��ʼ������
        }
        catch (Exception ex) {
            System.out.println("sss�ر�");
        }
        return true;
    }

    public static void main(String[] args) {
        TestSocket1 test = new TestSocket1();
        test.init();
    }
}
