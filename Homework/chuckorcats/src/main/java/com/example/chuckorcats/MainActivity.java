package com.example.chuckorcats;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.chuckorcats.models.ChuckResponse;
import com.example.chuckorcats.models.Fact;
import com.example.chuckorcats.recyclerView.FactsAdapter;
import com.example.chuckorcats.recyclerView.JokesAdapter;
import com.example.chuckorcats.repository.RepositoryProvider;
import com.example.chuckorcats.ui.main.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static FactsAdapter factsAdapter;

    static JokesAdapter jokesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PagerAdapter pagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabs = findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = pagerAdapter.getItem(position);
                Toast.makeText(MainActivity.this,
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        reloadFactsAboutCats();
        reloadJokesAboutChuck();
    }

    void reloadFactsAboutCats() {
        GetFactsRequest getFactsRequest = new GetFactsRequest();
        getFactsRequest.execute(10);
    }

    void reloadJokesAboutChuck() {
        GetJokesRequest getJokesRequest = new GetJokesRequest();
        getJokesRequest.execute(10);
    }

    private static class GetFactsRequest extends AsyncTask<Integer, Void, ArrayList<Fact>> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected ArrayList<Fact> doInBackground(Integer... counts) {
            try {
                return RepositoryProvider.get()
                        .provideCatsRepository()
                        .getFactsAboutCats(counts[0])
                        .execute()
                        .body();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(@Nullable ArrayList<Fact> facts) {
            if (facts != null) {
                factsAdapter.setFacts(facts);
            }
        }
    }

    private static class GetJokesRequest extends AsyncTask<Integer, Void, ChuckResponse> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected ChuckResponse doInBackground(Integer... amounts) {
            try {
                return RepositoryProvider.get()
                        .provideChuckRepository()
                        .getJokesAboutChuck(amounts[0])
                        .execute()
                        .body();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(@Nullable ChuckResponse chuckResponse) {
            if (chuckResponse != null) {
                jokesAdapter.setJokes(chuckResponse.getJokes());
            }
        }
    }
}