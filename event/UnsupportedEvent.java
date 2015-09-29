package com.gamemaker.event;

@SuppressWarnings("serial")
public class UnsupportedEvent extends Exception {
    public UnsupportedEvent(String message) {
        super(message);
    }
}