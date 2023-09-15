package com.cb.witfactory.ui.device;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cb.witfactory.data.retrofit.connection.ApiConecxion;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.device.ObjectResponseDevice;
import com.cb.witfactory.data.retrofit.events.ObjectResponseEvents;
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

                    listener.onSuccess(bodyResponseDevice.body,"getdevice");
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


    public void getMetrics(String device_id,String from,String to) {
        try {
            final Call<ObjectResponseEvents> obj = ApiConecxion.getApiService().getEvents(device_id, from,to);
            obj.enqueue(new Callback<ObjectResponseEvents>() {
                @Override
                public void onResponse(Call<ObjectResponseEvents> call, Response<ObjectResponseEvents> response) {
                    //
                    ObjectResponseEvents objectResponseEvents = new ObjectResponseEvents();
                    objectResponseEvents = response.body();

                    listener.onSuccess(objectResponseEvents.body,"getevents");
                }

                @Override
                public void onFailure(Call<ObjectResponseEvents> call, Throwable t) {
                    listener.onError("getEventsError");
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