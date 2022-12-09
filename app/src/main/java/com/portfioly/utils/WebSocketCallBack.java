package com.portfioly.utils;

public interface WebSocketCallBack {
    void onOpen();
    void onMessage(String text);
    void onClosing();
}
