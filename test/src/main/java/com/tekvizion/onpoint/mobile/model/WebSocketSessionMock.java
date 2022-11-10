package com.tekvizion.onpoint.mobile.model;

import org.springframework.web.socket.TextMessage;

public class WebSocketSessionMock {

    public void sendMessage(TextMessage textMessage){
        System.out.println("WebSocketSessionMock.sendMessage(): TextMessage received " + textMessage.getPayload());
    }
}
