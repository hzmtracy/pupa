package com.cmy.desn.builder;

public class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("Sms send");
    }

}
