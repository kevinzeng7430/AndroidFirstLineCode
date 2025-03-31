package com.kzeng.chart4News;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kzeng.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPanel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_news_title_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView(view);
        // 检查是否为双面板模式
        view.post(() -> {
            isTwoPanel = requireActivity().findViewById(R.id.news_content_layout) != null;
        });
    }
    private void initRecyclerView(View view) {
        RecyclerView newsRecyclerView = view.findViewById(R.id.news_title_recycler_view);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsRecyclerView.setAdapter(new NewsAdapter(getNews()));
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            News news = new News();
            news.setTitle("This is news title " + i);
            news.setContent(getRandomLengthContent("This is news content " + i + ". "));
            newsList.add(news);
        }
        return newsList;
    }
    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private final List<News> mNewsList;

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView newsTitle;
            public ViewHolder(View view) {
                super(view);
                newsTitle = view.findViewById(R.id.news_title);
            }
        }

        public  NewsAdapter(List<News> newsList) {
            this.mNewsList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(v -> handleItemClick(holder));
            return holder;
        }

        private void handleItemClick(ViewHolder holder) {
            News news = mNewsList.get(holder.getBindingAdapterPosition());
            if (isTwoPanel) {
                NewsContentFragment fragment = (NewsContentFragment) getParentFragmentManager()
                        .findFragmentById(R.id.news_content_fragment);
                if (fragment != null) {
                    fragment.refresh(news.getTitle(), news.getContent());
                }
            } else {
                NewsContentActivity.actionStart(requireActivity(),
                        news.getTitle(), news.getContent());
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitle.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }
}