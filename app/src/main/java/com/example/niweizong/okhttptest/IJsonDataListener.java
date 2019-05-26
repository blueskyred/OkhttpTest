package com.example.niweizong.okhttptest;

public interface IJsonDataListener<T> {
    void onSuccess(T t);
    void onFailure();
}
