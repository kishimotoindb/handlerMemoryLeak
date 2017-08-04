package com.example.bear.handlermemoryleak.OtherRelevantProblem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bear.handlermemoryleak.R;

/*
 * 1.B对象持有A对象中的成员变量a，并不会引起A对象不能被释放，a和A是独立的两个对象。
 * 2.View中的context引用了Activity，所以View被引用，Activity就无法释放。View被
 *   Handler持有，就会引起内存泄漏。
 * 3.Activity.getApplication()拿到的context不会引起Activity的泄漏
 * 4.Activity.getBaseContext()拿到的context会引起Activity的泄漏
 */
public class HoldViewCanHoldActivityActivity extends AppCompatActivity {
    HoldedNoContext mNoContext = new HoldedNoContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hold_view_can_hold_activity);
//        HoldViewStaticClass.holded = findViewById(R.id.holded);
        HoldViewStaticClass.holdedNoContext = mNoContext;
        mNoContext.mContext = getBaseContext();
    }
}
