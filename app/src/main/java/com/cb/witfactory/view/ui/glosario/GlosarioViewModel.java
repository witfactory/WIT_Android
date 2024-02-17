package com.cb.witfactory.view.ui.glosario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GlosarioViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GlosarioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}