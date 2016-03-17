package com.reeuse.androidmvp.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reeuse.androidmvp.R;
import com.reeuse.androidmvp.model.FollowingModel;

import java.util.List;

/**
 * Created by Rajiv M.
 */
public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder> {


    private List<FollowingModel> followingModelList;

    public FollowingAdapter(List<FollowingModel> followingModelList) {
        this.followingModelList = followingModelList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View notificationListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_followings, parent, false);
        return new ViewHolder(notificationListView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FollowingModel followingModel = followingModelList.get(position);

    }

    @Override
    public int getItemCount() {
        return followingModelList.size();
    }


    /**
     * View Holder class to hold the recycler view item widgets.
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}