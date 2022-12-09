package com.portfioly.utils;

public interface VolleyCallBack {
    void onSuccess(Object o);
    void onError(int error);
    void beforeSend();
    void onFinish();
}
