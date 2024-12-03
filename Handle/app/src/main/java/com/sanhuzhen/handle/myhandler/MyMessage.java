package com.sanhuzhen.handle.myhandler;

/**
 * description: Handler中的载体，用于在线程中传递数据
 * author: sanhuzhen
 * date: 2024/12/2 23:15
 */
public class MyMessage {

    //唯一标识符
    public int what;
    //携带的数据
    public Object obj;
    //时间戳
    public long when;

    //使用链表数据结构用来存放Message
    public MyMessage next;

    //用于将 Runnable 包装为 Message
    public Runnable callback;
    //指向 Message 的发送者，同时也是 Message 的最终处理者
    public MyHandler target;
}
