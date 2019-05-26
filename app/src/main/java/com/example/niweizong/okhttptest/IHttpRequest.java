package com.example.niweizong.okhttptest;

public interface IHttpRequest {
    void setUrl(String string);

    void setData(byte[] data);

    void setListener(CallBackListener callbackListener);

    void execute();

}
