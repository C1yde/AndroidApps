package com.example.vkwall;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Gson gson;
    ArrayList<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readPostsJson();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        PostAdapter adapter = new PostAdapter(posts);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void readPostsJson() {
        gson = new Gson();
        try {
            InputStream stream = getAssets().open("vk_posts.json");
            Reader reader = new InputStreamReader(stream);
            Type listType = new TypeToken<ArrayList<Post>>(){}.getType();
            posts = gson.fromJson(reader, listType);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
