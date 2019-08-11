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

public class FactAdapter extends RecyclerView.Adapter<FactAdapter.FactViewHolder> {
    private ArrayList<Fact> mFacts;

    public FactAdapter(){
        mFacts = new ArrayList<>();
    }

    public void setFacts(ArrayList<Fact> facts){
        mFacts.clear();
        mFacts.addAll(facts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fact_view, viewGroup, false);
        return new FactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FactViewHolder factViewHolder, int i) {
        final Fact currentItem = mFacts.get(i);

        factViewHolder.textView.setText(currentItem.value);
    }

    @Override
    public int getItemCount() {
        return mFacts.size();
    }

    static class FactViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        FactViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.factTextView);
        }
    }
}