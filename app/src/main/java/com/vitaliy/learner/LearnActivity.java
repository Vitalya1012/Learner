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
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LearnActivity extends AppCompatActivity {

    private static final String EXTRA_LANG = "lang";
    private static final String EXTRA_MODE = "mode";
    private static final String EXTRA_TRANSLATE_MODE = "translate_mode";

    private CardView cardViewWord;
    private TextView textViewOriginalWord;
    private TextView textViewTranslatedWord;
    private TextView textViewExampleOnCard;
    private TextView textViewHint;
    private ImageView imageViewLeft;
    private ImageView imageViewRight;

    private List<Word> words;

    private String lang;
    private int mode;
    private int translateMode;

    private LearnViewModel viewModel;
    private LearnViewModelFactory learnViewModelFactory;

    @SuppressLint("ClickableViewAccessibility")
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
        viewModel.loadWords();
        viewModel.getWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> wordsFromFB) {
                words = wordsFromFB;
            }
        });
        imageViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWord();
                textViewHint.setVisibility(View.VISIBLE);
                textViewTranslatedWord.setVisibility(View.INVISIBLE);
                textViewExampleOnCard.setVisibility(View.INVISIBLE);
            }
        });
        imageViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWord();
                textViewHint.setVisibility(View.VISIBLE);
                textViewTranslatedWord.setVisibility(View.INVISIBLE);
                textViewExampleOnCard.setVisibility(View.INVISIBLE);
            }
        });
        textViewHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewHint.setVisibility(View.INVISIBLE);
                textViewTranslatedWord.setVisibility(View.VISIBLE);
                textViewExampleOnCard.setVisibility(View.VISIBLE);
            }
        });

    }

    private void setWord() {
        if (translateMode == Const.FROM_RUSSIAN) {
            Word word = words.get(new Random().nextInt(words.size()));
            textViewOriginalWord.setText(word.getRussian());
            textViewTranslatedWord.setText(word.getForeign());
            textViewExampleOnCard.setText(word.getExamples());
        } else {
            Word word = words.get(new Random().nextInt(words.size()));
            textViewOriginalWord.setText(word.getForeign());
            textViewTranslatedWord.setText(word.getRussian());
            textViewExampleOnCard.setText(word.getExamples());
        }
    }

    private void initViews() {
        cardViewWord = findViewById(R.id.cardViewWord);
        textViewOriginalWord = findViewById(R.id.textViewOriginalWord);
        textViewTranslatedWord = findViewById(R.id.textViewTranslatedWord);
        textViewExampleOnCard = findViewById(R.id.textViewExampleOnCard);
        textViewHint = findViewById(R.id.textViewHint);
        imageViewLeft = findViewById(R.id.imageViewLeft);
        imageViewRight = findViewById(R.id.imageViewRight);

    }

    public static Intent newIntent(Context context, String lang, int mode, int translateMode) {
        Intent intent = new Intent(context, LearnActivity.class);
        intent.putExtra(EXTRA_LANG, lang);
        intent.putExtra(EXTRA_MODE, mode);
        intent.putExtra(EXTRA_TRANSLATE_MODE, translateMode);
        return intent;
    }
}