package com.c.j.w.net.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MinaClientHandler extends IoHandlerAdapter {

    /**
     * 当客户端接受到消息时
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        Integer num = Integer.valueOf((String) message);

        if (num == null || num > 10) {
            session.close(true);
            return;
        }

        System.out.println("receive server num : [ " + num + " ]");

        Thread.sleep(1000);

        session.write("client received num is : " + num + ", request next num");

    }

    /**
     * 当一个客户端被关闭时
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("client disconnect");
    }

    /**
     * 当一个客户端连接进入时
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {

        System.out.println("create connection to server :" + session.getRemoteAddress());
        session.write("client started");
        session.write("Hello World!");
    }

}