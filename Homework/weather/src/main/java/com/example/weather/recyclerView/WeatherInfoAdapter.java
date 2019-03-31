package com.example.weather.recyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.data.models.WeatherInfo;

import java.util.ArrayList;

public class WeatherInfoAdapter extends RecyclerView.Adapter<WeatherInfoAdapter.PostViewHolder> {
    private ArrayList<WeatherInfo> mWeatherInfo;

    WeatherInfoAdapter(ArrayList<WeatherInfo> weatherInfo) {
        mWeatherInfo = weatherInfo;
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView postDate;
        TextView postText;
        TextView likeText;
        TextView commentText;
        TextView shareText;
        ImageView avatar;
        ImageView postImage;
        ImageButton likeButton;
        CardView cardView;

        PostViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            avatar = itemView.findViewById(R.id.avatar);
            username = itemView.findViewById(R.id.username);
            postDate = itemView.findViewById(R.id.postDate);
            postText = itemView.findViewById(R.id.postText);
            postImage = itemView.findViewById(R.id.postImage);
            likeText = itemView.findViewById(R.id.likeText);
            commentText = itemView.findViewById(R.id.commentText);
            shareText = itemView.findViewById(R.id.shareText);
            likeButton = itemView.findViewById(R.id.likeButton);
        }
    }
}
