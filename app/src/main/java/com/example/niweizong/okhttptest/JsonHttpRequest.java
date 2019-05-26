package com.example.niweizong.okhttptest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonHttpRequest implements IHttpRequest {

    private String url;
    private byte[] data;
    private CallBackListener listener;
    HttpURLConnection urlConnection;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public void setListener(CallBackListener callbackListener) {
            this.listener = callbackListener;
    }

    @Override
    public void execute() {

    }

    private void getData(){
        URL url = null;
        try {
            url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(6000);//连接超时
            urlConnection.setUseCaches(false);//不使用缓存
            urlConnection.setInstanceFollowRedirects(true);//是成员函数，仅作用于当前函数设置这个链接是否可以被重定向
            urlConnection.setReadTimeout(3000);//响应的超时时间
            urlConnection.setDoInput(true);//设置连接是否可以写入数据
            urlConnection.setDoOutput(true);//设置这个链接是否可以输出数据
            urlConnection.setRequestMethod("post");
            urlConnection.setRequestProperty("Content-Type","application/json;charset=UTF-8");//设置消息的类型
            urlConnection.connect();
            //-----使用字节流发送数据--
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedOutputStream bufferedInputStream = new BufferedOutputStream(outputStream);
            bufferedInputStream.write(data);
            bufferedInputStream.flush();
            outputStream.close();
            bufferedInputStream.close();
            //字符流写入数据
            if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream inputStream = urlConnection.getInputStream();
                listener.onSuccess(inputStream);
            }else {
                throw new RuntimeException("请求失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("请求失败");
        }finally {
//            urlConnect
        }

    }
}
