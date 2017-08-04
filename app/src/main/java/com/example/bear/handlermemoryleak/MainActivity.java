package com.example.bear.handlermemoryleak;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bear.handlermemoryleak.NoViewInHandler.NewCallBackInHandlerConstructorActivity;
import com.example.bear.handlermemoryleak.OtherRelevantProblem.HoldViewCanHoldActivityActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btnHoldViewCanHoldActivity,R.id.btnNewCallBackInHandlerConstructorActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnHoldViewCanHoldActivity:
                startActivity(new Intent(this, HoldViewCanHoldActivityActivity.class));
                break;
            case R.id.btnNewCallBackInHandlerConstructorActivity:
                startActivity(new Intent(this, NewCallBackInHandlerConstructorActivity.class));
                break;
        }
    }
}
