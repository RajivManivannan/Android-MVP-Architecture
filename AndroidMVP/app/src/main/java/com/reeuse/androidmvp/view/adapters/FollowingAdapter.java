package com.reeuse.androidmvp.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.reeuse.androidmvp.R;
import com.reeuse.androidmvp.helper.VolleyRequestHelper;
import com.reeuse.androidmvp.model.FollowingModel;

import java.util.List;

/**
 * Created by Rajiv M.
 */
public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder> {

    private Context context;
    private List<FollowingModel> followingModelList;

    public FollowingAdapter(Context context, List<FollowingModel> followingModelList) {
        this.followingModelList = followingModelList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View notificationListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_followings, parent, false);
        return new ViewHolder(notificationListView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FollowingModel followingModel = followingModelList.get(position);
        holder.avatarNameTxt.setText(followingModel.getLogin());
        ImageLoader imageLoader = new VolleyRequestHelper(context).getImageLoader();
        holder.avatarPhotoImg.setImageUrl(followingModel.getAvatarUrl(), imageLoader);
    }

    @Override
    public int getItemCount() {
        return followingModelList.size();
    }


    /**
     * View Holder class to hold the recycler view item widgets.
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {

        NetworkImageView avatarPhotoImg;
        TextView avatarNameTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            avatarPhotoImg = (NetworkImageView) itemView.findViewById(R.id.following_avatar_img);
            avatarNameTxt = (TextView) itemView.findViewById(R.id.following_avatar_name);
        }
    }
}