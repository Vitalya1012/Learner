package com.vitaliy.learner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.motion.widget.OnSwipe;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LearnActivity extends AppCompatActivity {

    private static final String EXTRA_LANG = "lang";
    private static final String EXTRA_MODE = "mode";
    private static final String EXTRA_TRANSLATE_MODE = "translate_mode";

    private LearnAdapter adapter;
    private SwipeFlingAdapterView flingContainer;

    private List<Word> words = new ArrayList<>();

    private String lang;
    private int mode;
    private int translateMode;

    private LearnViewModel viewModel;
    private LearnViewModelFactory learnViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        initViews();
        lang = getIntent().getStringExtra(EXTRA_LANG);
        mode = getIntent().getIntExtra(EXTRA_MODE, 0);
        translateMode = getIntent().getIntExtra(EXTRA_TRANSLATE_MODE, 0);
        learnViewModelFactory = new LearnViewModelFactory(lang, mode);
        viewModel = new ViewModelProvider(this, learnViewModelFactory).get(LearnViewModel.class);
        adapter = new LearnAdapter(LearnActivity.this, translateMode);
        flingContainer.setAdapter(adapter);
        viewModel.getWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> wordsFromFB) {
                Log.d("LearnL", wordsFromFB.toString());
                words.addAll(wordsFromFB);
                adapter.setWords(words);
            }
        });
        viewModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (!isLoading){
                    adapter.setWords(words);
                    Log.d("LearnLL", words.toString());
                }
            }
        });

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object o) {
                words.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onRightCardExit(Object o) {
                words.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();

            }
        });
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int i, Object o) {
                View view = flingContainer.getSelectedView();
                TextView textViewTranslatedWord = view.findViewById(R.id.textViewTranslatedWord);
                textViewTranslatedWord.setVisibility(View.VISIBLE);
                TextView textViewExampleOnCard = view.findViewById(R.id.textViewExampleOnCard);
                textViewExampleOnCard.setVisibility(View.VISIBLE);
                TextView textViewHint = view.findViewById(R.id.textViewHint);
                textViewHint.setVisibility(View.INVISIBLE);
            }
        });

    }


    private void initViews() {
        flingContainer = findViewById(R.id.frame);
    }

    public static Intent newIntent(Context context, String lang, int mode, int translateMode) {
        Intent intent = new Intent(context, LearnActivity.class);
        intent.putExtra(EXTRA_LANG, lang);
        intent.putExtra(EXTRA_MODE, mode);
        intent.putExtra(EXTRA_TRANSLATE_MODE, translateMode);
        return intent;
    }

}