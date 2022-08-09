package com.vitaliy.learner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Switch switchFlags;
    private ImageView imageViewFlagEn;
    private ImageView imageViewFlagDe;
    private Button buttonMode0;
    private Button buttonMode1;
    private Button buttonMode2;
    private Button buttonMode3;

    private String lang = Const.ENGLISH_LANG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        switchFlags.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                setLanguage(isChecked);
            }
        });
        imageViewFlagEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLanguage(false);
                switchFlags.setChecked(false);
            }
        });
        imageViewFlagDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLanguage(true);
                switchFlags.setChecked(true);
            }
        });
        buttonMode0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ChooseTypeActivity.newIntent(MainActivity.this, lang, Const.GENERAL);
                startActivity(intent);
            }
        });
        buttonMode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ChooseTypeActivity.newIntent(MainActivity.this, lang, Const.PREPOSITION);
                startActivity(intent);
            }
        });
        buttonMode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ChooseTypeActivity.newIntent(MainActivity.this, lang, Const.PHRASAL_VERBS);
                startActivity(intent);
            }
        });
        buttonMode3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ChooseTypeActivity.newIntent(MainActivity.this, lang, Const.IRREGULAR_VERBS);
                startActivity(intent);
            }
        });

    }

    private void setLanguage(boolean isGerman) {
        if (isGerman) {
            lang = Const.GERMAN_LANG;
            imageViewFlagEn.setAlpha(0.3f);
            imageViewFlagDe.setAlpha(1f);
            buttonMode1.setVisibility(View.GONE);
            buttonMode2.setVisibility(View.GONE);
            buttonMode3.setVisibility(View.GONE);
        } else {
            lang = Const.ENGLISH_LANG;
            imageViewFlagDe.setAlpha(0.3f);
            imageViewFlagEn.setAlpha(1f);
            buttonMode1.setVisibility(View.VISIBLE);
            buttonMode2.setVisibility(View.VISIBLE);
            buttonMode3.setVisibility(View.VISIBLE);
        }
    }

    private void initViews() {
        switchFlags = findViewById(R.id.switchFlags);
        imageViewFlagEn = findViewById(R.id.imageViewFlagEn);
        imageViewFlagDe = findViewById(R.id.imageViewFlagDe);
        buttonMode0 = findViewById(R.id.buttonMode0);
        buttonMode1 = findViewById(R.id.buttonMode1);
        buttonMode2 = findViewById(R.id.buttonMode2);
        buttonMode3 = findViewById(R.id.buttonMode3);
    }


}