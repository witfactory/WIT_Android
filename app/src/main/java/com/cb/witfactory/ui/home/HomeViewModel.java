package com.cb.witfactory.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cb.witfactory.data.retrofit.connection.ApiConecxion;
import com.cb.witfactory.data.retrofit.user.GetUserResponse;
import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<GetUserResponse>> listMutableLiveDataUser;

    public HomeViewModel() {
        listMutableLiveDataUser = new MutableLiveData<>();
    }

    public MutableLiveData<List<GetUserResponse>> getUserObserver() {
        return listMutableLiveDataUser;
    }

    public GetUserResponse getUserResponse;

    public void getDataUSer(String idUser) {
        try {
            final Call<ObjectResponseUser> obj = ApiConecxion.getApiService().getUser(idUser);
            obj.enqueue(new Callback<ObjectResponseUser>() {
                @Override
                public void onResponse(Call<ObjectResponseUser> call, Response<ObjectResponseUser> response) {
                    //
                    ObjectResponseUser bodyResponseUser = new ObjectResponseUser();
                    bodyResponseUser = response.body();
                    listMutableLiveDataUser.postValue(response.body().getBody());
                    Log.v("bodyResponseUser", bodyResponseUser.getBody().toString());
                }

                @Override
                public void onFailure(Call<ObjectResponseUser> call, Throwable t) {
                    // listMutableLiveDataUser.postValue(null);
                    Log.v("Error", "");
                    listMutableLiveDataUser.postValue(null);
                }
            });

        } catch (Exception exception) {
            Log.v("Error", exception.getMessage());
        }
    }
}