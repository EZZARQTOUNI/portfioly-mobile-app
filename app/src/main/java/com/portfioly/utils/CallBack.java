package com.portfioly.utils;

public interface CallBack {
    void beforeProcessing();
    void onSuccess(String success);
    void onError();
    void onFinish();
}
