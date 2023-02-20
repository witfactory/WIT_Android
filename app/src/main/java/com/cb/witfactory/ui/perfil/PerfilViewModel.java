package com.cb.witfactory.ui.perfil;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cb.witfactory.data.retrofit.ApiConecxion;
import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;
import com.cb.witfactory.data.retrofit.user.GetUserResponse;

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
            final Call<ObjectResponseUser> obj = ApiConecxion.getApiService().getUser(idUser);
            obj.enqueue(new Callback<ObjectResponseUser>() {
                @Override
                public void onResponse(Call<ObjectResponseUser> call, Response<ObjectResponseUser> response) {
                    //
                    ObjectResponseUser bodyResponseUser = new ObjectResponseUser();
                    bodyResponseUser = response.body();
                    listMutableLiveDataUser.postValue(response.body().getBody().body);
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