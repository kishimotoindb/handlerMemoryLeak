package com.example.bear.handlermemoryleak.HasViewInHandler;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.bear.handlermemoryleak.R;

public class NewCallBackInHandlerConstructorActivity extends AppCompatActivity {
   Handler h =  new Handler(){};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_call_back_in_handler_constructor);
    }
}
