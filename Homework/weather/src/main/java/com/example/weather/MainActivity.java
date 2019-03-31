package com.example.weather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.weather.data.models.FiveDayForecastResponse;
import com.example.weather.data.repository.RepositoryProvider;
import com.example.weather.recyclerView.WeatherInfoAdapter;

import java.io.IOException;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvDayForecast)
    TextView dayInfoView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static void start(@NonNull Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.default_city);
        setSupportActionBar(toolbar);

        reloadForecast();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        WeatherInfoAdapter adapter = new WeatherInfoAdapter(posts);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    void reloadForecast() {
        ActionBar bar = getSupportActionBar();
        GetTemperatureRequest getTemperatureRequest = new GetTemperatureRequest();
        getTemperatureRequest.execute(bar.getTitle().toString());
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @SuppressLint("StaticFieldLeak")
    private class GetTemperatureRequest extends AsyncTask<String, Void, FiveDayForecastResponse> {

        @Override
        protected void onPreExecute() {
            showProgressBar();
        }

        @Override
        protected FiveDayForecastResponse doInBackground(String... city) {
            try {
                return RepositoryProvider.get()
                        .provideNewsFeedRepository()
                        .getFiveDayForecast(city[0])
                        .execute()
                        .body();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(@Nullable FiveDayForecastResponse fullWeatherInfo) {
            if (fullWeatherInfo != null) {
                dayInfoView.setText(fullWeatherInfo.getCity().toString());
            }
            hideProgressBar();
        }
    }
}