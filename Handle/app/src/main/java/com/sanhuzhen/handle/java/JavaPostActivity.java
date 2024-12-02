package com.sanhuzhen.handle.java;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.sanhuzhen.handle.base.BaseJavaActivity;
import com.sanhuzhen.handle.databinding.ActivityJavapostBinding;

public class JavaPostActivity extends BaseJavaActivity<ActivityJavapostBinding> {


    private Handler mHandler;

    @Override
    public ActivityJavapostBinding getViewBinding() {
        return ActivityJavapostBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler(getMainLooper());

        binding.btJava.setOnClickListener(v -> {
            MyThread t = new MyThread();
            t.start();
        });
    }

    private static class MyThread extends Thread {
        public void run() {
            super.run();
            //发送消息1给主线程，通知TextView更新
            Message msg = new Message();
            msg.what = 1;
            for (int i = 0; i < 100; i++) {
                try {
                    for (int j = 0; j < 100; j++) {
                        Thread.sleep(100);
                        Bundle bundle = new Bundle();
                        bundle.putInt("CURRENT_PROCESS_KEY", i);

                        Message message = new Message();
                        message.what = 2;


                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}