package com.tinkoff.fintech.news.tinkoffnews;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tinkoff.fintech.news.tinkoffnews.model.NewsObject;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private NewsViewModel model;

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        model = ViewModelProviders.of(this).get(NewsViewModel.class);

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            model.getNews();
            mSwipeRefreshLayout.setRefreshing(false); });

        model.getNews().observe(this, newsObjects -> mAdapter.addNews(newsObjects));
    }

    private void initViews() {

        mSwipeRefreshLayout = findViewById(R.id.swiperefresh);
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CustomAdapter(new ArrayList<NewsObject>());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(mRecyclerView.getContext(),  DividerItemDecoration.VERTICAL));
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress);
    }
}
