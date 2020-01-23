package com.example.applicationtest.ui.challenge;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class tenSecondsViewModel extends ViewModel {


    private MutableLiveData<String> mText;

    public tenSecondsViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}
