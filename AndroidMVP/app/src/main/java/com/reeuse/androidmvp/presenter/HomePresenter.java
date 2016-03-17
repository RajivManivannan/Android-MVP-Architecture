package com.reeuse.androidmvp.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reeuse.androidmvp.app.AppConstants;
import com.reeuse.androidmvp.helper.VolleyRequestHelper;
import com.reeuse.androidmvp.model.FollowingModel;
import com.reeuse.androidmvp.view.activities.HomeView;

import java.util.List;

/**
 * Created by Rajiv M.
 */
public class HomePresenter {
    private Context context;
    private HomeView homeView;
    /* The request completed listener */
    private VolleyRequestHelper.OnRequestCompletedListener requestCompletedListener = new VolleyRequestHelper.OnRequestCompletedListener() {
        @Override
        public void onRequestCompleted(String requestName, boolean status,
                                       String response, String errorMessage) {
            homeView.hideProgress();
            if (status) {
                Gson gson = new Gson();
                List<FollowingModel> followingModelList = gson.fromJson(response, new TypeToken<List<FollowingModel>>() {
                }.getType());
                homeView.updateData(followingModelList);
            }

        }
    };
    private VolleyRequestHelper volleyRequestHelper;

    public HomePresenter(Context context, HomeView homeView) {
        this.context = context;
        this.homeView = homeView;
        volleyRequestHelper = new VolleyRequestHelper(context, requestCompletedListener);
    }

    public void fetchData() {
        homeView.showProgress();
        volleyRequestHelper.requestString(AppConstants.REQUEST_FOLLOWINGS, AppConstants.URL_FOLLOWINGS, null, Request.Method.GET, false);
    }

    public void onDestroy() {
        homeView = null;
    }
}
