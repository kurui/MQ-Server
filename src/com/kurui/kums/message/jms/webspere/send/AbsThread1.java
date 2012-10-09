package com.kurui.kums.message.jms.webspere.send;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 2009-4-12
 * Time: 9:18:07
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbsThread1 extends Thread {

    public int getMqCount() {
        return mqCount;
    }

    public void setMqCount(int mqCount) {
        this.mqCount = mqCount;
    }

    protected int mqCount;

}