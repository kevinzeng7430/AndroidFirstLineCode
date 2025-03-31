package com.kzeng.chart10BindService;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.kzeng.R;

public class ServiceMainActivity extends AppCompatActivity implements View.OnClickListener{

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BindMyService.DownloadBinder downloadBinder = (BindMyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProcess();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);
        Button bindService1 = findViewById(R.id.bind_service);
        Button unbindService1 = findViewById(R.id.unbind_service);
        bindService1.setOnClickListener(this);
        unbindService1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.bind_service){
            Intent bindintent = new Intent(this, BindMyService.class);
            bindService(bindintent, connection, BIND_AUTO_CREATE);
        }
        if(id == R.id.unbind_service){
            unbindService(connection);
        }
    }
}