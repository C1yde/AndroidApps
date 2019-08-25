package com.example.themoviedb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.themoviedb.ui.main.PagerAdapter;
import com.example.themoviedb.ui.main.WatchedMovieFragment;
import com.example.themoviedb.ui.main.WatchlistMovieFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);
        ButterKnife.bind(this);

        PagerAdapter pagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = getSupportFragmentManager().getFragments().get(position);
                if(fragment != null && fragment.isAdded() && viewPager.getCurrentItem() == 1){
                    ((WatchedMovieFragment)fragment).reloadWatchedMovies();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        WatchlistMovieFragment fragment = (WatchlistMovieFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + viewPager.getCurrentItem());
        if(fragment != null && fragment.isAdded()){
            fragment.reloadWatchlist();
        }
    }
}