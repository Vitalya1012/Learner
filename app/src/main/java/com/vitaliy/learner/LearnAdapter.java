package com.vitaliy.learner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.LearnViewHolder> {


    @NonNull
    @Override
    public LearnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class LearnViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewOriginalWord;
        private TextView textViewTranslatedWord;
        private TextView textViewExampleOnCard;


        public LearnViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewOriginalWord = itemView.findViewById(R.id.textViewOriginalWord);
            textViewTranslatedWord = itemView.findViewById(R.id.textViewTranslatedWord);
            textViewExampleOnCard = itemView.findViewById(R.id.textViewExampleOnCard);
        }
    }
}
