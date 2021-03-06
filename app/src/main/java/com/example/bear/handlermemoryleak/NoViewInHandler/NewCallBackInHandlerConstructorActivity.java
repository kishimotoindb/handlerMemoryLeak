package com.example.bear.handlermemoryleak.NoViewInHandler;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bear.handlermemoryleak.R;

import java.lang.ref.WeakReference;

/*
   new Callback()可能发生泄漏是因为Handler持有Activity，new Runnable()可能发生泄漏是因为Message
   持有Activity。

   1.会内存泄漏的情况：
   1)CallBack使用匿名内部类
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

    2)Runnable使用匿名内部类
    mHandler2.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 3600 * 1000);
    与1)是同样的道理，因为Runnable使用了匿名内部类，所以会持有外部Activity的引用，Runnable以callback
    的形式保存在message中，message在messageQueue中，所以Activity泄漏

    2.不会发生内存泄漏的情况：
    1)CallBack使用静态内部类

        Handler mHandler2 = new Handler(new MyCallBack());
            因为MyCallBack是静态内部类，对象本身不持有外部Activity的引用，所以只要MyCallBack中的成员变量
        或者方法中没有变量持有Activity，那么就不会引起内存泄漏。

    2)Runnable使用静态内部类

        static class MyRunnable implements Runnable {
            @Override
            public void run() {

            }
        }
        与1）相同，不会泄漏。

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

    Handler mHandler2 = new Handler(new MyCallBack(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_call_back_in_handler_constructor);
//        mHandler1.sendEmptyMessageDelayed(1, 3600000);
        mHandler2.sendEmptyMessageDelayed(1, 3600 * 1000);

//        mHandler2.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 3600 * 1000);

//        mHandler2.postDelayed(new MyRunnable(), 3600 * 1000);
    }

    /*
     * 如果是静态的Callback，需要使用Activity，使用WeakReference就不会发生内存泄漏。
     */
    static class MyCallBack implements Handler.Callback {
        WeakReference<Activity> mWeakReference;
        Activity a;

        public MyCallBack(Activity activity) {
            mWeakReference = new WeakReference<Activity>(activity);
            a=activity;
        }

        @Override
        public boolean handleMessage(Message msg) {
            if (mWeakReference.get() != null) {
            }
            return false;
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
