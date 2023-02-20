package com.cb.witfactory.ui.device;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cb.witfactory.data.retrofit.ApiConecxion;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.device.ObjectResponseDevice;
import com.cb.witfactory.data.retrofit.user.GetUserResponse;
import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceViewModel extends ViewModel {
    // TODO: Implement the ViewModel


    private MutableLiveData<List<DeviceResponse>> listMutableLiveDataUser;

    public DeviceViewModel() {
        listMutableLiveDataUser = new MutableLiveData<>();
    }

    public MutableLiveData<List<DeviceResponse>> getDeviceObserver() {
        return listMutableLiveDataUser;
    }

    public DeviceResponse getDeviceResponse;

    public void getDataDevice(String user_id) {
        try {
            final Call<ObjectResponseDevice> obj = ApiConecxion.getApiService().getDevice(user_id);
            obj.enqueue(new Callback<ObjectResponseDevice>() {
                @Override
                public void onResponse(Call<ObjectResponseDevice> call, Response<ObjectResponseDevice> response) {
                    //
                    ObjectResponseDevice bodyResponseDevice = new ObjectResponseDevice();
                    bodyResponseDevice = response.body();
                    listMutableLiveDataUser.postValue(response.body().getBody().body);
                    Log.v("bodyResponseDevice", bodyResponseDevice.getBody().toString());
                }

                @Override
                public void onFailure(Call<ObjectResponseDevice> call, Throwable t) {
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