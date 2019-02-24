package com.example.vkwall;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private ArrayList<Post> mPosts;

    public PostAdapter(ArrayList<Post> posts) {
        mPosts = posts;
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView postDate;
        TextView postText;
        ImageView avatar;
        ImageView postImage;
        
        public PostViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            username = itemView.findViewById(R.id.username);
            postDate = itemView.findViewById(R.id.postDate);
            postText = itemView.findViewById(R.id.postText);
            postImage = itemView.findViewById(R.id.postImage);
        }
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        Post currentItem = mPosts.get(i);

        //postViewHolder.avatar.setImageResource(currentItem.avatar_url);
        //postViewHolder.postImage.setImageResource(currentItem.post_image);
        postViewHolder.username.setText(currentItem.username);
        postViewHolder.postText.setText(currentItem.post_text);
        postViewHolder.postDate.setText(new Date(currentItem.post_date).toString());
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}

