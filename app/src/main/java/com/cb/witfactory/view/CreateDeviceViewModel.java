package com.cb.witfactory.view;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.cb.witfactory.data.retrofit.connection.ApiConecxion;
import com.cb.witfactory.data.retrofit.device.CreateDevice;
import com.cb.witfactory.data.retrofit.device.CreateDeviceResponse;
import com.cb.witfactory.model.Callfun;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDeviceViewModel extends ViewModel {


    private static Callfun listener;
    public void createDataDevice(CreateDevice createDeviceResponse) {
        try {
            final Call<CreateDeviceResponse> obj = ApiConecxion.getApiService().createDevice(createDeviceResponse);
            obj.enqueue(new Callback<CreateDeviceResponse>() {
                @Override
                public void onResponse(Call<CreateDeviceResponse> call, Response<CreateDeviceResponse> response) {
                    //
                    CreateDeviceResponse bodyResponseDevice = new CreateDeviceResponse();
                    bodyResponseDevice = response.body();



                    listener.onSuccess(bodyResponseDevice,"device");
                }

                @Override
                public void onFailure(Call<CreateDeviceResponse> call, Throwable t) {
                    // listMutableLiveDataUser.postValue(null);
                    Log.v("Error", "");

                    listener.onError("createDeviceError");
                }
            });

        } catch (Exception exception) {
            Log.v("Error", exception.getMessage());
            listener.onError("createDeviceError");

        }
    }


    public void setListener(Callfun listener) {
        this.listener = listener;
    }


}