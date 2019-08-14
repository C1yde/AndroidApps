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
import com.example.chuckorcats.recyclerView.JokesAdapter;

public class ChuckFragment extends Fragment {

    private static String title;
    private static int page;

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
        JokesAdapter jokesAdapter = new JokesAdapter();

        jokesRecyclerView.setHasFixedSize(true);
        jokesRecyclerView.setLayoutManager(layoutManager);
        jokesRecyclerView.setAdapter(jokesAdapter);

        return root;
    }
}