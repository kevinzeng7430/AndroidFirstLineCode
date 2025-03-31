package com.kzeng.chart5BroadcastReceiver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kzeng.R;

public class LoginActivity extends BroadcastReceiverBaseActivity {

    private EditText accountEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEditText = findViewById(R.id.account);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (account.equals("admin") && password.equals("123456")) {
                    Toast.makeText(LoginActivity.this, "Account or Password cannot be empty", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, LoginSuccess.class);
                    startActivity(intent);
                } else {
                    // 处理登录逻辑
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}