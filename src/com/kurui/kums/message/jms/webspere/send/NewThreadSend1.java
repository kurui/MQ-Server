package com.kurui.kums.message.jms.webspere.send;

import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 2009-4-12
 * Time: 16:36:00
 * To change this template use File | Settings | File Templates.
 */
public class NewThreadSend1 extends AbsThread1 {
    private Socket ts;

    public Socket getTs() {
        return ts;
    }

    public void setTs(Socket ts) {
        this.ts = ts;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;
    public void run() {
        try {
            BufferedReader t = new BufferedReader(new InputStreamReader(ts.getInputStream()));
            while (t.readLine() != null) {
                System.out.println("��"+count+"�β������");
                SendService a = new SendService();
                a.setCount(count);
                a.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
