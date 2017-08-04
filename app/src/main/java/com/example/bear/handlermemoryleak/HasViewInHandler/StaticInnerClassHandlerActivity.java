package com.example.bear.handlermemoryleak.HasViewInHandler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.bear.handlermemoryleak.R;

public class StaticInnerClassHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_handler_inner_class);
    }
}
