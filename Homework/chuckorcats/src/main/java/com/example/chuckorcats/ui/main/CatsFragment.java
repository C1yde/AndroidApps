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
import com.example.chuckorcats.models.Fact;
import com.example.chuckorcats.recyclerView.FactsAdapter;
import com.example.chuckorcats.repository.RepositoryProvider;

import java.io.IOException;
import java.util.ArrayList;

public class CatsFragment extends Fragment {

    private static String title;
    private static int page;
    private FactsAdapter factsAdapter;

    public static CatsFragment newInstance(int page, String title) {
        CatsFragment fragment = new CatsFragment();
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
        View root = inflater.inflate(R.layout.cats_fragment, container, false);

        RecyclerView factsRecyclerView = root.findViewById(R.id.cats_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        factsAdapter = new FactsAdapter();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(factsRecyclerView.getContext(),
                layoutManager.getOrientation());
        factsRecyclerView.addItemDecoration(dividerItemDecoration);

        factsRecyclerView.setHasFixedSize(true);
        factsRecyclerView.setLayoutManager(layoutManager);
        factsRecyclerView.setAdapter(factsAdapter);

        reloadFactsAboutCats();

        return root;
    }

    private void reloadFactsAboutCats() {
        GetFactsRequest getFactsRequest = new GetFactsRequest();
        getFactsRequest.execute(10);
    }

    @SuppressLint("StaticFieldLeak")
    private class GetFactsRequest extends AsyncTask<Integer, Void, ArrayList<Fact>> {

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
}