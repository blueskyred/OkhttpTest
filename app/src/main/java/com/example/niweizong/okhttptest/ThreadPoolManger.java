package com.example.niweizong.okhttptest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManger {

    private static ThreadPoolManger instance;

    public static ThreadPoolManger getInstance(){
        if (instance == null){
            synchronized (ThreadPoolManger.class){
                if (instance ==null){
                    instance = new ThreadPoolManger();
                }
            }
        }
        return instance;
    }

    //创建队列，将网络请求任务添加到队列中
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();
    public void addTask(Runnable runnable){
        if (runnable!=null){
            try {
                mQueue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //创建线程池，
    private ThreadPoolExecutor mThreadPoolExecutor;
    private ThreadPoolManger(){
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        //将拒绝的线程，抛出来
                        addTask(r);
                    }
                });
        mThreadPoolExecutor.execute(coreThread);
    }
    //创建“叫号员”线程，不停的获取
    public Runnable coreThread =  new Runnable() {
        Runnable runnable;
        @Override
        public void run() {
            while (true){
                try {
                    runnable = mQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mThreadPoolExecutor.execute(runnable);
            }
        }
    };

}
