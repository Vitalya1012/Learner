package com.vitaliy.learner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DictionaryActivity extends AppCompatActivity {

    private static final String EXTRA_LANG = "lang";
    private static final String EXTRA_MODE = "mode";

    private RecyclerView recyclerView;
    private WordsAdapter adapter;

    private String lang;
    private int mode;

    private LearnViewModel viewModel;
    private LearnViewModelFactory learnViewModelFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        lang = getIntent().getStringExtra(EXTRA_LANG);
        mode = getIntent().getIntExtra(EXTRA_MODE, Const.GENERAL);
        learnViewModelFactory = new LearnViewModelFactory(lang, mode);
        viewModel = new ViewModelProvider(this, learnViewModelFactory).get(LearnViewModel.class);

        recyclerView = findViewById(R.id.recyclerViewWords);
        adapter = new WordsAdapter(mode);
        recyclerView.setAdapter(adapter);
        viewModel.getWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setWords(words );
            }
        });
        adapter.setOnWordClickListener(new WordsAdapter.OnWordClickListener() {
            @Override
            public void onWordClick(Word word) {

            }
        });

    }

    public static Intent newIntent(Context context, String lang, int mode) {
        Intent intent = new Intent(context, DictionaryActivity.class);
        intent.putExtra(EXTRA_LANG, lang);
        intent.putExtra(EXTRA_MODE, mode);
        return intent;
    }
}