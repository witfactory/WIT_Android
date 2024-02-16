package com.cb.witfactory.view.ui.perfil;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cb.witfactory.adapter.Event;
import com.cb.witfactory.data.retrofit.connection.ApiConecxion;
import com.cb.witfactory.data.retrofit.events.Data;
import com.cb.witfactory.data.retrofit.events.DeviceMetrics;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.data.retrofit.events.ObjectResponseEvents;
import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;
import com.cb.witfactory.data.retrofit.user.UpdateUserResponse;
import com.cb.witfactory.model.Callfun;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileViewModel extends ViewModel {

    private MutableLiveData<Boolean> updateSuccess = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();


    public void updateUser(UpdateUserResponse userUpdateRequest) {
        try {
            Call<UpdateUserResponse> call = ApiConecxion.getApiService().updateUser(userUpdateRequest);
            call.enqueue(new Callback<UpdateUserResponse>() {
                @Override
                public void onResponse(@NonNull Call<UpdateUserResponse> call, @NonNull Response<UpdateUserResponse> response) {
                    if (response.isSuccessful()) {
                        UpdateUserResponse updateUserResponse = response.body();
                        updateSuccess.postValue(true);
                    } else {
                        errorMessage.postValue("Error en la actualización: " + response.message());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UpdateUserResponse> call, @NonNull Throwable t) {
                    errorMessage.postValue("Error en la actualización: " + t.getMessage());
                }
            });
        } catch (Exception exception) {
            errorMessage.postValue("Error en la actualización: " + exception.getMessage());
        }
    }

    // ...
}
