package com.example.niweizong.okhttptest;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.InputStream;

public class JsonCallBackListener<T> implements CallBackListener {

    private Class<T> responClass;
    private Handler handler = new Handler(Looper.getMainLooper());

    private IJsonDataListener jsonDataListener;

    public JsonCallBackListener(Class<T> responClass,IJsonDataListener jsonDataListener){
        this.responClass = responClass;
        this.jsonDataListener = jsonDataListener;
    }
    @Override
    public void onSuccess(InputStream inputStream) {
        String string = getContent(inputStream);
        final T clazz = JSON.parseObject(string,responClass);
        handler.post(new Runnable() {
            @Override
            public void run() {
                jsonDataListener.onSuccess(clazz);
            }
        });

    }


    @Override
    public void onFailure() {

    }
    private String getContent(InputStream inputStream) {
        //将字符流转为字符串；

        return "123";
    }


}
