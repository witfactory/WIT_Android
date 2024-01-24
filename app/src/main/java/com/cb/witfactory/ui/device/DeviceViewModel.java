package com.cb.witfactory.ui.device;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cb.witfactory.adapter.Event;
import com.cb.witfactory.data.retrofit.alarms.Alarm;
import com.cb.witfactory.data.retrofit.connection.ApiConecxion;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.device.ObjectResponseDevice;
import com.cb.witfactory.data.retrofit.events.Data;
import com.cb.witfactory.data.retrofit.events.DeviceMetrics;
import com.cb.witfactory.data.retrofit.events.EventsRealtime;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.data.retrofit.events.ObjectResponseAlarm;
import com.cb.witfactory.data.retrofit.events.ObjectResponseEvents;
import com.cb.witfactory.data.retrofit.events.ObjectResponseEventsRealtime;
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
                    Map<String, DeviceMetrics> deviceMetricsMap = objectResponseEvents.response;

                    // Convertir el Map a ArrayList
                    List<Metric> metrics = new LinkedList<>();
                    List<Data> data = new LinkedList<>();
                    List<DeviceMetrics> deviceMetricsList = new ArrayList<>(deviceMetricsMap.values());
                    for (DeviceMetrics deviceMetrics : deviceMetricsList) {
                        metrics = deviceMetrics.getMetrics();
                        data = deviceMetrics.getData();
                        // Realiza las acciones necesarias con los datos
                    }
                    listener.onSuccess(metrics,"getevents");
                    List<Event> events = new ArrayList<>();
                    for (DeviceMetrics deviceMetrics1 : deviceMetricsList) {
                        Data firstData = deviceMetrics1.getData().get(0);
                        String deviceId = firstData.getDevice_id();
                        String timestamp = firstData.getTimestamp();

                        // Itera sobre las métricas
                        for (Metric metric : deviceMetrics1.getMetrics()) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                            LocalDateTime localDateTime = LocalDateTime.parse(timestamp, formatter);
                            Event event = new Event(
                                    metric.getTitle(),
                                    metric.getValue(),
                                    metric.getColor(),
                                    timestamp,
                                    deviceId
                            );
                            event.setTimestamp(localDateTime);
                            events.add(event);
                        }
                    }
                    listener.onSuccess(events,"gettotalevents");
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

    public void getMetricsRealtime(String device_id,String user) {
        try {

            EventsRealtime eventsRealtime = new EventsRealtime(device_id,user);
            final Call<ObjectResponseEventsRealtime> obj = ApiConecxion.getApiService().getEventsRealtime(eventsRealtime);
            obj.enqueue(new Callback<ObjectResponseEventsRealtime>() {
                @Override
                public void onResponse(Call<ObjectResponseEventsRealtime> call, Response<ObjectResponseEventsRealtime> response) {
                    //
                    ObjectResponseEventsRealtime objectResponseEventsRealtime = new ObjectResponseEventsRealtime();
                    objectResponseEventsRealtime = response.body();

                    if(objectResponseEventsRealtime.response != null){
                        listener.onSuccess(objectResponseEventsRealtime.response,"getevents");

                    }else{
                        listener.onError("getEventsError");
                    }
                  }

                @Override
                public void onFailure(Call<ObjectResponseEventsRealtime> call, Throwable t) {
                    listener.onError("getEventsError");
                }
            });

        } catch (Exception exception) {
            Log.v("Error", exception.getMessage());
        }
    }


    public void setAlarm(ArrayList<Alarm> alarms) {
        try {

            final Call<ObjectResponseAlarm> obj = ApiConecxion.getApiService().setAlarm(alarms);
            obj.enqueue(new Callback<ObjectResponseAlarm>() {

                @Override
                public void onResponse(Call<ObjectResponseAlarm> call, Response<ObjectResponseAlarm> response) {
                    listener.onSuccess("alarmaok");

                }

                @Override
                public void onFailure(Call<ObjectResponseAlarm> call, Throwable t) {
                    listener.onError("alarmaerror");
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