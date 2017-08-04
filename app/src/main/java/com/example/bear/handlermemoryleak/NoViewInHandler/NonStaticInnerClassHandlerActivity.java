package com.example.bear.handlermemoryleak.NoViewInHandler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bear.handlermemoryleak.R;

public class NonStaticInnerClassHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_static_handler_inner_class);
    }
}
