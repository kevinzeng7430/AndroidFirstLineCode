package com.kzeng.SQLite;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.kzeng.R;

public class SQLiteMainActivity extends AppCompatActivity implements View.OnClickListener{
    private  MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sqlite_main);
        myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.create_database){
            myDatabaseHelper.getWritableDatabase();
        }
    }
}