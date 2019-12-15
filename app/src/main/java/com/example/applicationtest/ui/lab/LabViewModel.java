package com.example.applicationtest.ui.lab;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LabViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LabViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is lab fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}