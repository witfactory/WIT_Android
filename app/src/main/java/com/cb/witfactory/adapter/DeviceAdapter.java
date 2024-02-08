package com.cb.witfactory.adapter;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cb.witfactory.R;
import com.cb.witfactory.data.retrofit.alarms.Alarm;
import com.cb.witfactory.data.retrofit.alarms.ClosedOpenValve;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.ui.device.DeviceViewModel;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.MyViewHolder>
        implements Filterable, Callfun {

    private Context context;
    private List<DeviceResponse> deviceList;
    private List<DeviceResponse> deviceListFiltered;
    private DeviceAdapterListener listener;

    private DeviceViewModel deviceViewModel;

    @Override
    public void onSuccess(String s) {
        Log.v("success alarm", s);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onSuccess(Object o, String s) {

    }

    @Override
    public void onError(String s) {
        Log.v("error alarm", s);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            }
        });

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public SwitchMaterial check_state;
        public ImageView img_hadeare;
        public ImageView img_wifi;
        public TextView txt_title, txt_sub_title;
        public RelativeLayout relativecontent;

        public MyViewHolder(View view) {
            super(view);


            check_state = view.findViewById(R.id.check_state);
            img_hadeare = view.findViewById(R.id.img_hadeare);
            img_wifi = view.findViewById(R.id.img_wifi);
            txt_title = view.findViewById(R.id.txt_title);
            txt_sub_title = view.findViewById(R.id.txt_sub_title);
            relativecontent = view.findViewById(R.id.relativecontent);

            relativecontent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onDeviceSelected(deviceListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public DeviceAdapter(Context context, List<DeviceResponse> deviceList, DeviceAdapterListener listener, DeviceViewModel _deviceViewModel) {
        this.context = context;
        this.listener = listener;
        this.deviceList = deviceList;
        this.deviceListFiltered = deviceList;
        this.deviceViewModel = _deviceViewModel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_dispositivo, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DeviceResponse device = deviceListFiltered.get(position);
        holder.txt_title.setText(device.getDevice_name());
        holder.txt_sub_title.setText(device.getDevice_location());

        String typeDevice = device.getDevice_type();
        if (typeDevice.equals("S")) {
            holder.img_hadeare.setBackgroundResource(R.drawable.hadware_dispositivo);
        } else {
            holder.img_hadeare.setBackgroundResource(R.drawable.img_valvula);
        }


        if (device.getBuzzer() != null) {
            if (device.getBuzzer()) {
                holder.check_state.setChecked(true);
            } else {
                holder.check_state.setChecked(true);
            }

        }

        if (device.getOnline() != null) {
            if (device.getOnline()) {
                holder.img_wifi.setBackgroundResource(R.drawable.ic_wifi_on);
            } else {
                holder.img_wifi.setBackgroundResource(R.drawable.ic_wifi_of);
            }
        }

        holder.check_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String device_type = device.getDevice_type().toString();
                if (device_type.equals("S")) {

                    Toast.makeText(context.getApplicationContext(), device.getDevice_id(), Toast.LENGTH_LONG).show();
                    ArrayList<Alarm> alarms = new ArrayList<>();
                    String userEmail = PreferencesHelper.getEmail("email", "");
                    Alarm alarm = new Alarm(device.getDevice_id(), userEmail);
                    alarms.add(alarm);

                    deviceViewModel.setAlarm(alarms);
                }

                if (device_type.equals("V")) {

                    int estado = 0;
                    if (device.getBuzzer() != null) {
                        if (device.getBuzzer()) {
                            estado = 1;
                        } else {
                            estado = 0;
                        }
                    }


                    Toast.makeText(context.getApplicationContext(), device.getDevice_id(), Toast.LENGTH_LONG).show();
                    String userEmail = PreferencesHelper.getEmail("email", "");
                    ClosedOpenValve alarm = new ClosedOpenValve(userEmail, device.getDevice_id(), estado);


                    deviceViewModel.setValvula(alarm);

                }
            }
        });

       /* Glide.with(context)
                .load(device.getImageDevice())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.img_hadeare);

        Glide.with(context)
                .load(device.getImageWifi())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.img_wifi);

        if(device.getState()){
            holder.check_state.setEnabled(true);
        }else{
            holder.check_state.setEnabled(false);
        }*/
    }

    @Override
    public int getItemCount() {
        return deviceListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    deviceListFiltered = deviceList;
                } else {
                    List<DeviceResponse> filteredList = new ArrayList<>();
                    for (DeviceResponse row : deviceList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getDevice_name().toLowerCase().contains(charString.toLowerCase()) || row.getDevice_location().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    deviceListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = deviceListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                deviceListFiltered = (ArrayList<DeviceResponse>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void filterList(ArrayList<DeviceResponse> filteredList) {
        deviceListFiltered = filteredList;
        notifyDataSetChanged();
    }

    public interface DeviceAdapterListener {
        void onDeviceSelected(DeviceResponse contact);

        void onListValueDeviceSelected(Metric device);
    }
}
