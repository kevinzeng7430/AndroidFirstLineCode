package com.kzeng;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.kzeng.DownloadTest.MHServiceMainActivity;

import com.kzeng.SQLite.SQLiteMainActivity;
import com.kzeng.chart10BindService.ServiceMainActivity;
import com.kzeng.chart2.FirstActivity;
import com.kzeng.chart3UIBestpractice.UIBestPractice;
import com.kzeng.chart4Fragment.BasicChartFourActivity;
import com.kzeng.chart4News.NewsMainActivity;
import com.kzeng.chart6.FileMainActivity;
import com.kzeng.chart7Contactors.ContactMainActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1111111111111111
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate: executed");

        Button button_chart2 = findViewById(R.id.button_chart2);
        button_chart2.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart2");
            Intent intent = new Intent(MainActivity.this, FirstActivity.class);
            startActivity(intent);
        });

        Button button_chart3 = findViewById(R.id.button_chart3);
        button_chart3.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3");
            Intent intent = new Intent(MainActivity.this, com.kzeng.chart3.ListViewTest.class);
            startActivity(intent);
        });
        Button button_chart3_1 = findViewById(R.id.button_chart3_1);
        button_chart3_1.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3_1");
            Intent intent = new Intent(MainActivity.this, com.kzeng.chart3.RecycleViewTest.class);
            startActivity(intent);
        });
        Button button_chart3_2 = findViewById(R.id.button_chart3_2);
        button_chart3_2.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3_2");
            Intent intent = new Intent(MainActivity.this, com.kzeng.chart3.RecycleViewTest2.class);
            startActivity(intent);
        });
        Button button_chart3_3 = findViewById(R.id.button_chart3_3);
        button_chart3_3.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3_3");
            Intent intent = new Intent(MainActivity.this, com.kzeng.chart3.RecyclerViewTest3.class);
            startActivity(intent);
        });
        Button button_chart3_4 = findViewById(R.id.button_chart3_4);
        button_chart3_4.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3_3");
            Intent intent = new Intent(MainActivity.this, UIBestPractice.class);
            startActivity(intent);
        });
        Button button_chart4_1 = findViewById(R.id.button_chart4_1);
        button_chart4_1.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3_3");
            Intent intent = new Intent(MainActivity.this, BasicChartFourActivity.class);
            startActivity(intent);
        });
        Button button_chart4_2 = findViewById(R.id.button_chart4_2);
        button_chart4_2.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3_3");
            Intent intent = new Intent(MainActivity.this, NewsMainActivity.class);
            startActivity(intent);
        });

        Button button_chart6 = findViewById(R.id.button_chart6);
        button_chart6.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3_3");
            Intent intent = new Intent(MainActivity.this, FileMainActivity.class);
            startActivity(intent);
        });

        Button button_chart6_1 = findViewById(R.id.button_chart6_1);
        button_chart6_1.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3_3");
            Intent intent = new Intent(MainActivity.this, SQLiteMainActivity.class);
            startActivity(intent);
        });

        Button button_chart7_1 = findViewById(R.id.button_chart7_1);
        button_chart7_1.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3_3");
            Intent intent = new Intent(MainActivity.this, ContactMainActivity.class);
            startActivity(intent);
        });
        Button button_chart10 = findViewById(R.id.button_chart10);
        button_chart10.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3_3");
            Intent intent = new Intent(MainActivity.this, ServiceMainActivity.class);
            startActivity(intent);
        });
        Button button_chart10_1 = findViewById(R.id.button_chart10_1);
        button_chart10_1.setOnClickListener(v -> {
            Log.d("MainActivity", "onClick: button_chart3_3");
            Intent intent = new Intent(MainActivity.this, MHServiceMainActivity.class);
            startActivity(intent);
        });



    }
}