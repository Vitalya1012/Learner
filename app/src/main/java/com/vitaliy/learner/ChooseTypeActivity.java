package com.vitaliy.learner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseTypeActivity extends AppCompatActivity {

    private static final String EXTRA_LANG = "lang";
    private static final String EXTRA_MODE = "mode";
    private static final String EXTRA_TRANSLATE_MODE = "translate_mode";


    private ImageView imageViewFlag;
    private Button buttonModeFromRu;
    private Button buttonModeToRu;
    private Button buttonDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);
        initViews();
        String lang = getIntent().getStringExtra(EXTRA_LANG);
        int mode = getIntent().getIntExtra(EXTRA_MODE, 0);
        if (lang.equals(Const.ENGLISH_LANG)) {
            imageViewFlag.setImageResource(R.drawable.flag_en);
            buttonModeFromRu.setText(R.string.from_russian_to_english);
            buttonModeToRu.setText(R.string.from_english_to_russian);
        } else {
            imageViewFlag.setImageResource(R.drawable.flag_de);
            buttonModeFromRu.setText(R.string.from_russian_to_german);
            buttonModeToRu.setText(R.string.from_german_to_russian);
        }
        buttonDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = DictionaryActivity.newIntent(ChooseTypeActivity.this, lang, mode);
                startActivity(intent);
            }
        });
        buttonModeFromRu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LearnActivity.newIntent(ChooseTypeActivity.this, lang, mode, Const.FROM_RUSSIAN);
                startActivity(intent);
            }
        });
        buttonModeToRu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LearnActivity.newIntent(ChooseTypeActivity.this, lang, mode, Const.TO_RUSSIAN);
                startActivity(intent);
            }
        });

    }

    public static Intent newIntent(Context context, String lang, int mode) {
        Intent intent = new Intent(context, ChooseTypeActivity.class);
        intent.putExtra(EXTRA_LANG, lang);
        intent.putExtra(EXTRA_MODE, mode);
        return intent;
    }

    private void initViews(){
        imageViewFlag=findViewById(R.id.imageViewFlag);
        buttonModeFromRu=findViewById(R.id.buttonModeFromRu);
        buttonModeToRu=findViewById(R.id.buttonModeToRu);
        buttonDictionary=findViewById(R.id.buttonDictionary);

    }
}