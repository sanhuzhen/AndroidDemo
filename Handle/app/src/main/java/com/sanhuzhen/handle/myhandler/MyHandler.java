package com.sanhuzhen.handle.myhandler;

import android.os.SystemClock;

/**
 * description: 自己写一个Handler，当有了存放Message的MessageQueue，那么就需要一个去发送Message给Queue的类，即Handler
 *    <p>Handler的一些功能：</p>
 *    <p>1.希望除了可以发送 Message 类型的消息外还可以发送 Runnable 类型的消息。这个简单，Handler 内部将 Runnable 包装为 Message 即可</p>
 *    <p>2.希望可以发送延时消息，以此来执行延时任务。这个也简单，用 Message 内部的 when 字段来标识希望任务执行时的时间戳即可</p>
 *    <p>3.希望可以实现线程切换，即从子线程发送的 Message 可以在主线程被执行，反过来也一样。这个也不难，子线程可以向一个特定的 mainMessageQueue 发送消息，然后让主线程负责循环从该队列中取消息并执行即可，这样不就实现了线程切换了吗？</p>
 * author: sanhuzhen
 * date: 2024/12/2 23:14
 */
public class MyHandler {
    private MyMessageQueue mQueue;

    public MyHandler(MyMessageQueue queue) {
        mQueue = queue;
    }
    public final void post(Runnable r) {
        sendMessageDelayed(getPostMessage(r), 0);
    }

    public final void postDelayed(Runnable r, long delayMillis) {
        sendMessageDelayed(getPostMessage(r), delayMillis);
    }

    public final void sendMessage(MyMessage r) {
        sendMessageDelayed(r, 0);
    }

    public final void sendMessageDelayed(MyMessage msg, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        sendMessageAtTime(msg, SystemClock.uptimeMillis() + delayMillis);
    }

    public void sendMessageAtTime(MyMessage msg, long uptimeMillis) {
        msg.target = this;
        mQueue.enqueueMessage(msg, uptimeMillis);
    }

    private static MyMessage getPostMessage(Runnable r) {
        MyMessage m = new MyMessage();
        m.callback = r;
        return m;
    }

    //由外部来重写该方法，以此来消费 Message
    public void handleMessage(MyMessage msg) {

    }
    //用于分发消息
    public void dispatchMessage(MyMessage msg) {
        if (msg.callback != null) {
            msg.callback.run();
        } else {
            handleMessage(msg);
        }
    }
}
