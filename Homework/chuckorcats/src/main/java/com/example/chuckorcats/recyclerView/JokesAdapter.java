package com.example.chuckorcats.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuckorcats.R;
import com.example.chuckorcats.models.Fact;
import com.example.chuckorcats.models.Joke;

import java.util.ArrayList;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokesViewHolder> {
    private ArrayList<Joke> mJokes;

    public JokesAdapter(){
        mJokes = new ArrayList<>();
    }

    public void setJokes(ArrayList<Joke> jokes){
        mJokes.clear();
        mJokes.addAll(jokes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public JokesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.joke_view, viewGroup, false);
        return new JokesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokesViewHolder jokesViewHolder, int i) {
        final Joke currentItem = mJokes.get(i);

        jokesViewHolder.textView.setText(currentItem.value);
    }

    @Override
    public int getItemCount() {
        return mJokes.size();
    }

    static class JokesViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        JokesViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.jokeTextView);
        }
    }
}