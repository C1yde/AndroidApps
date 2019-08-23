package com.example.themoviedb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.themoviedb.ui.main.PagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);
        ButterKnife.bind(this);

        PagerAdapter pagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabs.setupWithViewPager(viewPager);

        fab.setOnClickListener(view -> {
            Context context = view.getContext();
            Intent intent = new Intent(view.getContext(), SearchActivity.class);
            context.startActivity(intent);
        });
    }
}