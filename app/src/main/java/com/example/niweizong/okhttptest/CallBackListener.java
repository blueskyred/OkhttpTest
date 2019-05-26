package com.example.niweizong.okhttptest;

import java.io.InputStream;

public interface CallBackListener {
    void onSuccess(InputStream inputStream);

    void onFailure();
}
