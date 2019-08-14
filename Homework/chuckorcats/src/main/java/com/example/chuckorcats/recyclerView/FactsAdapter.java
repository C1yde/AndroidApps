package com.example.chuckorcats.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuckorcats.R;
import com.example.chuckorcats.models.Fact;

import java.util.ArrayList;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.FactsViewHolder> {
    private ArrayList<Fact> mFacts;

    public FactsAdapter(){
        mFacts = new ArrayList<>();
    }

    public void setFacts(ArrayList<Fact> facts){
        mFacts.clear();
        mFacts.addAll(facts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fact_view, viewGroup, false);
        return new FactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FactsViewHolder factsViewHolder, int i) {
        final Fact currentItem = mFacts.get(i);

        factsViewHolder.textView.setText(currentItem.text);
    }

    @Override
    public int getItemCount() {
        return mFacts.size();
    }

    static class FactsViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        FactsViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.factTextView);
        }
    }
}