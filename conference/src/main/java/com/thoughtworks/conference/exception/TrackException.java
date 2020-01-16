package com.thoughtworks.conference.exception;

import java.io.UnsupportedEncodingException;

public class TrackException extends RuntimeException {
    private static final long serialVersionUID = 4730597402855274362L;

    public TrackException(Object  message) {
        String messageStr = null;
        try {
            messageStr = new String(message.toString().getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(messageStr);
        return ;
    }

    public TrackException(Throwable cause) {
        System.out.println(cause);
    }

    public TrackException(Object message, Throwable throwable) {
        System.out.println(message.toString()+":"+throwable);
    }


}
