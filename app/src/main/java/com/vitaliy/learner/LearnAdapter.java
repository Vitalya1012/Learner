package com.vitaliy.learner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LearnAdapter extends BaseAdapter {

    private List<Word> words;
    private Context context;
    private int translateMode;

    public LearnAdapter(Context context, int translateMode) {
        this.context = context;
        this.translateMode = translateMode;
    }

    public void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int finalPosition, View convertView, ViewGroup parent) {

        LearnViewHolder viewHolder;
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learn_item, parent, false);
            viewHolder = new LearnViewHolder();
            viewHolder.textViewOriginalWord = view.findViewById(R.id.textViewOriginalWord);
            viewHolder.textViewTranslatedWord = view.findViewById(R.id.textViewTranslatedWord);
            viewHolder.textViewExampleOnCard = view.findViewById(R.id.textViewExampleOnCard);
            view.setTag(viewHolder);
        } else {
            viewHolder = (LearnViewHolder) convertView.getTag();
        }

        if (translateMode == Const.FROM_RUSSIAN) {
            viewHolder.textViewOriginalWord.setText(words.get(finalPosition).getRussian());
            viewHolder.textViewTranslatedWord.setText(words.get(finalPosition).getForeign());
            viewHolder.textViewExampleOnCard.setText(words.get(finalPosition).getExamples());
        } else {
            viewHolder.textViewOriginalWord.setText(words.get(finalPosition).getForeign());
            viewHolder.textViewTranslatedWord.setText(words.get(finalPosition).getRussian());
            viewHolder.textViewExampleOnCard.setText(words.get(finalPosition).getExamples());
        }

        return view;
    }

    static class LearnViewHolder {
        private TextView textViewOriginalWord;
        private TextView textViewTranslatedWord;
        private TextView textViewExampleOnCard;

    }
}
