package com.gamemaker.action;

@SuppressWarnings("serial")
public class UnsupportedAction extends Exception {
    public UnsupportedAction(String message) {
        super(message);
    }
}