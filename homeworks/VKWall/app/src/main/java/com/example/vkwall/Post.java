package com.example.vkwall;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    String avatar_url;
    String username;
    String post_text;
    String post_image;
    Integer post_date;
    Integer likes_count;
    Integer comments_count;
    Integer shares_count;
    Boolean is_user_like;

    private Post(Parcel in) {
        avatar_url = in.readString();
        username = in.readString();
        post_text = in.readString();
        post_image = in.readString();
        if (in.readByte() == 0) {
            post_date = null;
        } else {
            post_date = in.readInt();
        }
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
        if (post_date == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(post_date);
        }
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
