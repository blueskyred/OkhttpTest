package com.example.niweizong.okhttptest;

public class NetUtils {

    public static<T,M> void sendJsonRequest(String url,T requestData,Class<M> response,IJsonDataListener listener){
        IHttpRequest httpRequest = new JsonHttpRequest();
        CallBackListener callBackListener = new JsonCallBackListener<>(response,listener);
        HttpTask httpTask = new HttpTask(url,requestData,httpRequest,callBackListener);

        ThreadPoolManger.getInstance().addTask(httpTask);
    }
}
