package com.example.chuckorcats.ui.main;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuckorcats.R;
import com.example.chuckorcats.models.ChuckResponse;
import com.example.chuckorcats.recyclerView.JokesAdapter;
import com.example.chuckorcats.repository.RepositoryProvider;

import java.io.IOException;

public class ChuckFragment extends Fragment {

    private static String title;
    private static int page;
    private JokesAdapter jokesAdapter;

    public static ChuckFragment newInstance(int page, String title) {
        ChuckFragment fragment = new ChuckFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("someInt", page);
        bundle.putString("someTitle", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.chuck_fragment, container, false);

        RecyclerView jokesRecyclerView = root.findViewById(R.id.chuck_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        jokesAdapter = new JokesAdapter();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(jokesRecyclerView.getContext(),
                layoutManager.getOrientation());
        jokesRecyclerView.addItemDecoration(dividerItemDecoration);

        jokesRecyclerView.setHasFixedSize(true);
        jokesRecyclerView.setLayoutManager(layoutManager);
        jokesRecyclerView.setAdapter(jokesAdapter);

        reloadJokesAboutChuck();

        return root;
    }

    private void reloadJokesAboutChuck() {
        GetJokesRequest getJokesRequest = new GetJokesRequest();
        getJokesRequest.execute(10);
    }

    @SuppressLint("StaticFieldLeak")
    private class GetJokesRequest extends AsyncTask<Integer, Void, ChuckResponse> {

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