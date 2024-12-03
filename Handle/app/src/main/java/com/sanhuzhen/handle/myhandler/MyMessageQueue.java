package com.sanhuzhen.handle.myhandler;

import android.os.SystemClock;

/**
 * description: 有了Message之后，要有一个地方用于存放Message，我们使用队列这种模式，根据时间戳来排列Message，先进先出
 * author: sanhuzhen
 * date: 2024/12/2 23:20
 */
public class MyMessageQueue {

    private MyMessage msg;
    //用来标记 next() 方法是否正处于阻塞等待的状态
    private boolean mBlocked = false;

    /**
     * 要用一个方法用于向一个链表中插入Message，同时用防止多线程同时向链表中插入Message，所以要加个🔒，实现线程同步
     */
    void enqueueMessage(MyMessage message, long when) {
        synchronized (this) {
            //用于标记是否需要唤醒 next 方法
            boolean needWake = false;
            MyMessage p = msg;
            // 如果队列为空或者要插入的Message的when小于队列中第一个Message的when，则将message插入到队列的开头
            if (p == null || when == 0 || when < p.when) {
                message.next = p;
                msg = message;
                //需要唤醒
                needWake = mBlocked;
            } else {
                MyMessage prev;
                //从链表头向链表尾遍历，寻找链表中第一条时间戳比 msg 大的消息，将 msg 插到该消息的前面
                do {
                    prev = p;
                    p = p.next;
                } while (p != null && when >= p.when);
                message.next = p;
                prev.next = message;
            }
            if (needWake) {
                //唤醒 next() 方法
                nativeWake();
            }
        }
    }

    //唤醒 next() 方法
    private void nativeWake() {

    }

    /**
     * 获取队列中的第一个Message
     * 我们在MessageQueue中肯定是要拿到第一个Message去处理的，所以要提供一个方法，用于获取队列中的第一个Message
     */
    MyMessage next(){
        int nextPollTimeoutMillis = 0;

        for(;;){
            nativePollOnce(nextPollTimeoutMillis);

            synchronized (this) {
                //当前时间
                final long now = SystemClock.uptimeMillis();
                MyMessage message = msg;
                if (message != null) {
                    if (now < message.when) {
                        //如果当前时间还未到达消息的的处理时间，那么就计算还需要等待的时间
                        nextPollTimeoutMillis = (int) Math.min(message.when - now, Integer.MAX_VALUE);
                    } else {
                        //可以处理队头的消息了，第二条消息成为队头
                        msg = message.next;
                        message.next = null;
                        mBlocked = false;
                        return message;
                    }
                } else {
                    // No more messages.
                    nextPollTimeoutMillis = -1;
                }
                mBlocked = true;
            }
        }
    }

    //将 next 方法的调用线程休眠指定时间
    private void nativePollOnce(long nextPollTimeoutMillis) {

    }
}
