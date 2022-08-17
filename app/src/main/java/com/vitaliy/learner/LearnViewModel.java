package com.vitaliy.learner;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import io.reactivex.rxjava3.disposables.Disposable;

public class LearnViewModel extends ViewModel {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference wordsReference;

    private String lang;
    private int mode;

    private MutableLiveData<List<Word>> words = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public LiveData<List<Word>> getWords() {
        return words;
    }

    public LiveData<Boolean> isLoading() {
        return loading;
    }

    public LearnViewModel(String lang, int mode) {
        loading.setValue(true);
        this.lang = lang;
        this.mode = mode;
        firebaseDatabase = Utils.getDatabase();
        if (lang.equals(Const.GERMAN_LANG)) {
            wordsReference = firebaseDatabase.getReference("words_general_de");
        } else if (lang.equals(Const.ENGLISH_LANG) && mode == Const.GENERAL) {
            wordsReference = firebaseDatabase.getReference("words_general");
        } else if (lang.equals(Const.ENGLISH_LANG) && mode == Const.PREPOSITION) {
            wordsReference = firebaseDatabase.getReference("words_preposition");
        } else if (lang.equals(Const.ENGLISH_LANG) && mode == Const.PHRASAL_VERBS) {
            wordsReference = firebaseDatabase.getReference("words_phrasal_verbs");
        } else if (lang.equals(Const.ENGLISH_LANG) && mode == Const.IRREGULAR_VERBS) {
            wordsReference = firebaseDatabase.getReference("words_irregular_verbs");
        }
        wordsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Word> wordsFromFB = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Word word = dataSnapshot.getValue(Word.class);
                    if (word == null) {
                        return;
                    } else {
                        wordsFromFB.add(word);
                    }
                }
                Collections.shuffle(wordsFromFB);
                words.setValue(wordsFromFB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        loading.setValue(false);
    }

}
