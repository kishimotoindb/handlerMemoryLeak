package com.example.bear.handlermemoryleak.NoViewInHandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bear.handlermemoryleak.R;

/*
   1.会内存泄漏的情况：
   Handler mHandler1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //匿名内部类是非静态的，所以会持有外部类Activity的引用
            NewCallBackInHandlerConstructorActivity activity = NewCallBackInHandlerConstructorActivity.this;
            return false;
        }
    });

    因为callback持有Activity，handler持有callback，message持有handler，messageQueue持有message，
    所以即使callback中不包含对View进行处理，也会因为持有外部类引用，而造成Activity的内存泄漏。

    2.不会发生内存泄漏的情况：
    Handler mHandler2 = new Handler(new MyCallBack());
        因为MyCallBack是静态内部类，对象本身不持有外部Activity的引用，所以只要MyCallBack中的成员变量
    或者方法中没有变量持有Activity，那么就不会引起内存泄漏。

 */

public class NewCallBackInHandlerConstructorActivity extends AppCompatActivity {

    Handler mHandler1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //匿名内部类是非静态的，所以会持有外部类Activity的引用
            NewCallBackInHandlerConstructorActivity activity = NewCallBackInHandlerConstructorActivity.this;
            return false;
        }
    });

    Handler mHandler2 = new Handler(new MyCallBack());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_call_back_in_handler_constructor);
//        mHandler1.sendEmptyMessageDelayed(1, 3600000);
        mHandler2.sendEmptyMessageDelayed(1, 3600 * 1000);
    }

    static class MyCallBack implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    }

}
