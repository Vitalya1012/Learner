package com.vitaliy.learner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordViewHolder> {

    private int mode;

    private OnWordClickListener onWordClickListener;

    List<Word> words = new ArrayList<>();

    public void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    public WordsAdapter(int mode) {
        this.mode = mode;
    }

    interface OnWordClickListener{
        void onWordClick(Word word);
    }

    public void setOnWordClickListener(OnWordClickListener onWordClickListener) {
        this.onWordClickListener = onWordClickListener;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutResId;
        if (mode == Const.IRREGULAR_VERBS){
            layoutResId = R.layout.irregular_word_item;
        } else {
            layoutResId = R.layout.word_item;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = words.get(position);
        holder.textViewRussianWord.setText(word.getRussian());
        holder.textViewForeignWord.setText(word.getForeign());
        holder.textViewExample.setText(word.getExamples());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onWordClickListener != null){
                    onWordClickListener.onWordClick(word);
                    if (holder.textViewExample.getVisibility() == View.VISIBLE) {
                        holder.textViewExample.setVisibility(View.GONE);
                    } else {
                        holder.textViewExample.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder{

        private final TextView textViewRussianWord;
        private TextView textViewForeignWord;
        private TextView textViewExample;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRussianWord=itemView.findViewById(R.id.textViewRussianWord);
            textViewForeignWord=itemView.findViewById(R.id.textViewForeignWord);
            textViewExample=itemView.findViewById(R.id.textViewExample);
        }
    }
}
