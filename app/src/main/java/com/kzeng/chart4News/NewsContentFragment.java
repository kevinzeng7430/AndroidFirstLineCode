package com.kzeng.chart4News;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kzeng.R;

public class NewsContentFragment extends Fragment {
    private TextView newsTitleText;
    private TextView newsContentText;
    private View visibilityLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_content_frag, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        visibilityLayout = view.findViewById(R.id.visibility_layout);
        newsTitleText = view.findViewById(R.id.news_title);
        newsContentText = view.findViewById(R.id.news_content);
    }

    public void refresh(String newsTitle, String newsContent) {
        if (visibilityLayout != null) {
            visibilityLayout.setVisibility(View.VISIBLE);
        }
        if (newsTitleText != null) {
            newsTitleText.setText(newsTitle);
        }
        if (newsContentText != null) {
            newsContentText.setText(newsContent);
        }
    }
}