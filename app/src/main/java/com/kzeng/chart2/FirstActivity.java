package com.kzeng.chart2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.kzeng.R;

public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart2_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            //Toast.makeText(FirstActivity.this, "Ni Hao", Toast.LENGTH_SHORT).show();
            finish();
        });

        //隐式Intent
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.baidu.com"));
            startActivity(intent);
        });

        Button button3 = findViewById(R.id.secondActivity);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个隐式 Intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_item) {
            Toast.makeText(FirstActivity.this, "You clicked add", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.remove_item) {
            Toast.makeText(FirstActivity.this, "You clicked remove", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


}