package com.reeuse.androidmvp.view.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.reeuse.androidmvp.R;
import com.reeuse.androidmvp.model.FollowingModel;
import com.reeuse.androidmvp.presenter.HomePresenter;
import com.reeuse.androidmvp.view.adapters.FollowingAdapter;

import java.util.List;

public class HomeActivity extends BaseActivity implements HomeView {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;


    private HomePresenter homePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.home_following_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.home_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.fetchData();
            }
        });
        // Setup the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        homePresenter = new HomePresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.fetchData();
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void updateData(List<FollowingModel> followingModelList) {
        if (followingModelList != null) {
            FollowingAdapter followingAdapter = new FollowingAdapter(HomeActivity.this,followingModelList);
            recyclerView.setAdapter(followingAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        homePresenter.onDestroy();
        super.onDestroy();
    }
}
