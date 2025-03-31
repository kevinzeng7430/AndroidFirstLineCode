package com.kzeng.chart6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.kzeng.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileMainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;

    private Button saveData;

    private Button restoreData;
    private EditText editName;
    private EditText editAge;
    private EditText editMarry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_main);
        editText = findViewById(R.id.edit);
        saveData = findViewById(R.id.save_data);
        saveData.setOnClickListener(this); // 绑定监听器
        restoreData = findViewById(R.id.restore_data);
        editName = findViewById(R.id.edit_name);
        editAge = findViewById(R.id.edit_age);
        editMarry = findViewById(R.id.edit_marry);
        restoreData.setOnClickListener(this);
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            editText.setText(inputText);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.save_data){
            SharedPreferences.Editor editor= getPreferences( Context.MODE_PRIVATE).edit();
            editor.putString("name", "Tom");
            editor.putInt("age", 23);
            editor.putBoolean("married", false);
            editor.apply();
            // 可选：提示用户保存成功
            Toast.makeText(this, "数据已保存", Toast.LENGTH_SHORT).show();
        }

        if(v.getId() == R.id.restore_data){
            restoreDataFromSharedPreferences();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = editText.getText().toString();
        save(inputText);
    }

    private void restoreDataFromSharedPreferences() {
        try {
            SharedPreferences pref = getSharedPreferences("data", Context.MODE_PRIVATE);

            // 恢复 name（String）
            String name = pref.getString("name", "");
            editName.setText(name);

            // 恢复 age（Int → 转 String）
            int age = pref.getInt("age", 0);
            editAge.setText(String.valueOf(age));

            // 恢复 married（Boolean → 显示 "TRUE"/"FALSE"）
            boolean isMarried = pref.getBoolean("married", false);
            editMarry.setText(isMarried ? getString(R.string.T) : getString(R.string.F));

            // 提示用户恢复成功
            Toast.makeText(this, "数据恢复成功", Toast.LENGTH_SHORT).show();
            Log.d("FileMainActivity", "数据恢复成功");
        } catch (Exception e) {
            Toast.makeText(this, "恢复数据失败", Toast.LENGTH_SHORT).show();
            Log.e("FileMainActivity", "恢复数据失败", e);
        }
    }
    public String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            // data1是com.kzeng.files下的文件名
            in = openFileInput("data1");
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content.toString();
    }
    private void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data1", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}