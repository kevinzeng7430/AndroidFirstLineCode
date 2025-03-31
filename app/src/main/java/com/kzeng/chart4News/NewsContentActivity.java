package com.kzeng.chart4News;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kzeng.R;

public class NewsContentActivity extends AppCompatActivity {
    private static final String EXTRA_TITLE = "news_title";
    private static final String EXTRA_CONTENT = "news_content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        // 使用Bundle获取数据更安全
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String newsTitle = extras.getString(EXTRA_TITLE);
            String newsContent = extras.getString(EXTRA_CONTENT);

            NewsContentFragment fragment = (NewsContentFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.news_content_fragment);
            if (fragment != null) {
                fragment.refresh(newsTitle, newsContent);
            }
        }
    }

    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra(EXTRA_TITLE, newsTitle);
        intent.putExtra(EXTRA_CONTENT, newsContent);
        context.startActivity(intent);
    }
}

//优化点：
//
//添加常量定义Intent的extra key
//
//使用Bundle更安全地获取Intent数据
//
//
//
//添加null检查更健壮