package com.example.vkwall;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private ArrayList<Post> mPosts;

    PostAdapter(ArrayList<Post> posts) {
        mPosts = posts;
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
        
        PostViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            username = itemView.findViewById(R.id.username);
            postDate = itemView.findViewById(R.id.postDate);
            postText = itemView.findViewById(R.id.postText);
            postImage = itemView.findViewById(R.id.postImage);
            likeText = itemView.findViewById(R.id.likeText);
            commentText = itemView.findViewById(R.id.commentText);
            shareText = itemView.findViewById(R.id.shareText);
            likeButton = itemView.findViewById(R.id.likeButton);

            itemView.setOnClickListener(view -> {
                Context context = view.getContext();
                Intent intent = new Intent(view.getContext(), DetailedPostActivity.class);
                context.startActivity(intent);
            });
        }
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder postViewHolder, int i) {
        final Post currentItem = mPosts.get(i);

        currentItem.setAvatar(postViewHolder.avatar);
        currentItem.setPostImage(postViewHolder.postImage);
        postViewHolder.username.setText(currentItem.getUsername());
        postViewHolder.postText.setText(currentItem.getPostText());
        postViewHolder.postDate.setText(currentItem.getPostDate());
        postViewHolder.likeText.setText(currentItem.getLikeText());
        postViewHolder.commentText.setText(currentItem.getCommentText());
        postViewHolder.shareText.setText(currentItem.getShareText());
        postViewHolder.likeButton.setImageResource(currentItem.getLikeImage());
        postViewHolder.likeButton.setOnClickListener(view -> {
            ImageButton button = (ImageButton)view;
            currentItem.changeLike();
            button.setImageResource(currentItem.getLikeImage());
            postViewHolder.likeText.setText(currentItem.getLikeText());

        });
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}

