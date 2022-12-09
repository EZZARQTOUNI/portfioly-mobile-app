package com.portfioly.utils;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketClient extends WebSocketListener  {
    private static final int NORMAL_CLOSURE_STATUS = 15000;
    private WebSocketCallBack webSocketCallBack;

    public WebSocketClient(WebSocketCallBack webSocketCallBack) {
        this.webSocketCallBack = webSocketCallBack;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket,response);
        webSocketCallBack.onOpen();
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket,text);
        webSocketCallBack.onMessage(text);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocketCallBack.onClosing();
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        System.out.println("*************** FIALED ***********");
        t.printStackTrace();
    }

}
