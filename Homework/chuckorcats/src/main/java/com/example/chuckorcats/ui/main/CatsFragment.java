package com.example.chuckorcats.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuckorcats.R;
import com.example.chuckorcats.recyclerView.FactsAdapter;

public class CatsFragment extends Fragment {

    private static String title;
    private static int page;

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
        FactsAdapter factsAdapter = new FactsAdapter();

        factsRecyclerView.setHasFixedSize(true);
        factsRecyclerView.setLayoutManager(layoutManager);
        factsRecyclerView.setAdapter(factsAdapter);

        return root;
    }
}