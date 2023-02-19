package com.cb.witfactory.ui.perfil;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cb.witfactory.data.retrofit.ApiConecxion;
import com.cb.witfactory.data.retrofit.user.GetUserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<List<GetUserResponse>> listMutableLiveDataUser;

    public PerfilViewModel() {
        listMutableLiveDataUser = new MutableLiveData<>();
    }

    public MutableLiveData<List<GetUserResponse>> getUserObserver() {
        return listMutableLiveDataUser;
    }

    public GetUserResponse getUserResponse;

    public void getDataUSer(String idUser) {
        try {
            final Call<Object> obj = ApiConecxion.getApiService().getUser(idUser);
            obj.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    //listMutableLiveDataUser.postValue(response.body());
                    Log.v("obj", response.body().toString());
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                   // listMutableLiveDataUser.postValue(null);
                }
            });

        } catch (Exception exception) {

            Log.v("Error", exception.getMessage());
        }

    }
}