package com.cmy.desn.factory.multiMethod;

public class MailSender implements Sender {

    @Override
    public void send() {
        System.out.println("Mail send");
    }
}
