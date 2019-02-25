package com.example.vkwall;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Post implements Parcelable {
    private String avatar_url;
    private String username;
    private String post_text;
    private String post_image;
    private String post_date;
    private Integer likes_count;
    private Integer comments_count;
    private Integer shares_count;
    private Boolean is_user_like;

    private Post(Parcel in) {
        avatar_url = in.readString();
        username = in.readString();
        post_text = in.readString();
        post_image = in.readString();
        post_date = in.readString();
        if (in.readByte() == 0) {
            likes_count = null;
        } else {
            likes_count = in.readInt();
        }
        if (in.readByte() == 0) {
            comments_count = null;
        } else {
            comments_count = in.readInt();
        }
        if (in.readByte() == 0) {
            shares_count = null;
        } else {
            shares_count = in.readInt();
        }
        byte tmpIs_user_like = in.readByte();
        is_user_like = tmpIs_user_like == 0 ? null : tmpIs_user_like == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatar_url);
        dest.writeString(username);
        dest.writeString(post_text);
        dest.writeString(post_image);
        dest.writeString(post_date);
        if (likes_count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(likes_count);
        }
        if (comments_count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(comments_count);
        }
        if (shares_count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(shares_count);
        }
        dest.writeByte((byte) (is_user_like == null ? 0 : is_user_like ? 1 : 2));
    }

    void setPostImage(ImageView imageView){
        Picasso.get().load(this.post_image).into(imageView);
    }

    void setAvatar(ImageView imageView){
        Picasso.get().load(this.avatar_url).transform(new CircleTransform()).into(imageView);
    }

    String getCommentText(){
        return String.format(Locale.getDefault(),"%d", this.comments_count);
    }

    String getShareText(){
        return String.format(Locale.getDefault(),"%d", this.shares_count);
    }

    String getLikeText(){
        return String.format(Locale.getDefault(),"%d", this.likes_count);
    }

    String getPostText(){
        return this.post_text;
    }

    String getUsername(){
        return this.username;
    }

    String getPostDate(){
        long millisecond = Long.parseLong(this.post_date) * 1000;
        Date postDate =  new Date(millisecond);
        Date currentDate = new Date();
        long msDiff = currentDate.getTime() - postDate.getTime();
        long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
        if (daysDiff > 7){
            return DateFormat.format("dd.MM.yyyy hh:mm", new Date(millisecond)).toString();
        }

        return String.format(Locale.getDefault(),"%d дней назад", daysDiff);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
