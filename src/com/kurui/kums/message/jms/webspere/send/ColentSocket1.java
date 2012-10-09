package com.kurui.kums.message.jms.webspere.send;

import java.net.Socket;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 2009-4-12
 * Time: 17:34:18
 * To change this template use File | Settings | File Templates.
 */
public class ColentSocket1 {
    public static void main(String[] args) {
        try {
            Socket t = new Socket("156.16.20.22", 9104);
            BufferedWriter a = new BufferedWriter(new OutputStreamWriter(t.getOutputStream()));
            a.write("����");
            a.newLine();
            a.flush();
            a.close();
            t.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
