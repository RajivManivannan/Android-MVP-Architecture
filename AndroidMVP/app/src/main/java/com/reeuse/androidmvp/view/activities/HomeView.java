package com.reeuse.androidmvp.view.activities;

import com.reeuse.androidmvp.model.FollowingModel;

import java.util.List;

/**
 * Created by Rajiv M.
 */
public interface HomeView {


    void showProgress();

    void hideProgress();

    void updateData(List<FollowingModel> followingModelList);

}
