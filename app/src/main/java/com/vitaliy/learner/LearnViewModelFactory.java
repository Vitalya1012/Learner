package com.vitaliy.learner;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LearnViewModelFactory implements ViewModelProvider.Factory {

    private String lang;
    private int mode;

    public LearnViewModelFactory(String lang, int mode) {
        this.lang = lang;
        this.mode = mode;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LearnViewModel(lang, mode);
    }

}
