package com.sanhuzhen.handle.java;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.sanhuzhen.handle.base.BaseJavaActivity;
import com.sanhuzhen.handle.databinding.ActivityJavademoBinding;

public class JavaDemoActivity extends BaseJavaActivity<ActivityJavademoBinding> {
    public static final String CURRENT_PROCESS_KEY = "CURRENT_PROCESS";
    private Handler mHandle;
    @Override
    public ActivityJavademoBinding getViewBinding() {
        return ActivityJavademoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handlerUI();
        initView();
    }

    private void initView() {
        binding.btJava.setOnClickListener(v -> {
//            MyThread thread = new MyThread();
//            thread.start();
            new Thread(() -> {
                try {
                    Message message = new Message();
                    message.what = 1;

                    for (int i = 0; i < 100; i++) {
                        //模拟耗时任务
                        Thread.sleep(100);

                        Bundle bundle = new Bundle();
                        bundle.putInt(CURRENT_PROCESS_KEY, i);
                        Message msg = new Message();
                        msg.setData(bundle);
                        mHandle.sendMessage(msg);
                        msg.what = 2;

                        if (i==99){
                            mHandle.sendMessage(message);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    private void handlerUI() {
        mHandle = new Handler(Looper.getMainLooper()){
            @SuppressLint("SetTextI18n")
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch(msg.what) {
                    case 1:
                        binding.tvJava.setText("UI线程");
                        break;
                    case 2:
                        //获取 ProgressBar 的进度，然后显示进度值
                        Bundle bundle = msg.getData();
                        int process = bundle.getInt(CURRENT_PROCESS_KEY);
                        binding.pbJava.setProgress(process);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    /**
     * 新开一个线程，在新线程中处理耗时任务
     */
    public class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            //发送消息1给主线程，通知TextView更新
            Message msg = new Message();
            msg.what = 1;
            for (int i = 0; i < 100; i++) {
                try {
                    //模拟耗时任务
                    Thread.sleep(100);

                    //发送消息给主线程
                    Message message = new Message();
                    message.what = 2;
                    Bundle bundle = new Bundle();
                    bundle.putInt(CURRENT_PROCESS_KEY, i);
                    message.setData(bundle);
                    mHandle.sendMessage(message);
                    if(i==99){
                        mHandle.sendMessage(msg);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}