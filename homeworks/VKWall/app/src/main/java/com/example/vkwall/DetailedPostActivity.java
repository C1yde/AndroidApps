package com.example.vkwall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.Instant;

public class DetailedPostActivity extends AppCompatActivity {
    TextView username;
    TextView postDate;
    TextView postText;
    TextView likeText;
    TextView commentText;
    TextView shareText;
    ImageView avatar;
    ImageView postImage;
    ImageButton likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);

        username = findViewById(R.id.username);
        postDate = findViewById(R.id.postDate);
        postText = findViewById(R.id.postText);
        likeText = findViewById(R.id.likeText);
        commentText = findViewById(R.id.commentText);
        shareText = findViewById(R.id.shareText);
        avatar = findViewById(R.id.avatar);
        postImage = findViewById(R.id.postImage);
        likeButton = findViewById(R.id.likeButton);

        Intent intent = getIntent();
        Post post = intent.getParcelableExtra("post");

        post.setAvatar(avatar);
        post.setPostImage(postImage);
        username.setText(post.getUsername());
        postText.setText(post.getPostText());
        postDate.setText(post.getPostDate());
        likeText.setText(post.getLikeText());
        commentText.setText(post.getCommentText());
        shareText.setText(post.getShareText());
        likeButton.setImageResource(post.getLikeImage());
        likeButton.setOnClickListener(view -> {
            ImageButton button = (ImageButton)view;
            post.changeLike();
            button.setImageResource(post.getLikeImage());
            likeText.setText(post.getLikeText());

        });
    }
}
