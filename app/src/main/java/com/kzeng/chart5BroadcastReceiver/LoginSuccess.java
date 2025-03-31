package com.kzeng.chart5BroadcastReceiver;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kzeng.R;

public class LoginSuccess extends BroadcastReceiverBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        Button forceOffline = findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(v -> {
            Intent intent = new Intent("com.kzeng.chart5BroadcastReceiver.FORCE_OFFLINE");
            sendBroadcast(intent);
        });
    }
}

