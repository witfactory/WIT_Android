package com.cb.witfactory.ui.device;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cb.witfactory.data.retrofit.connection.ApiConecxion;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.device.ObjectResponseDevice;
import com.cb.witfactory.model.Callfun;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceViewModel extends ViewModel {


    private static Callfun listener;
    public void getDataDevice(String user_id,String device_type) {
        try {
            final Call<ObjectResponseDevice> obj = ApiConecxion.getApiService().getDevice(user_id, device_type);
            obj.enqueue(new Callback<ObjectResponseDevice>() {
                @Override
                public void onResponse(Call<ObjectResponseDevice> call, Response<ObjectResponseDevice> response) {
                    //
                    ObjectResponseDevice bodyResponseDevice = new ObjectResponseDevice();
                    bodyResponseDevice = response.body();
//                    listMutableLiveDataUser.postValue(response.body().getBody().body);
                    Log.v("bodyResponseDevice", bodyResponseDevice.getBody().toString());

                    listener.onSuccess(bodyResponseDevice,"getdevice");
                }

                @Override
                public void onFailure(Call<ObjectResponseDevice> call, Throwable t) {
                    // listMutableLiveDataUser.postValue(null);
                    Log.v("Error", "");
                 //   listMutableLiveDataUser.postValue(null);

                    listener.onError("getDeviceError");
                }
            });

        } catch (Exception exception) {
            Log.v("Error", exception.getMessage());
        }
    }


    public void setListener(Callfun listener) {
        this.listener = listener;
    }
}