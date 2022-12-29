package com.cb.witfactory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cb.witfactory.R;
import com.cb.witfactory.data.model.Device;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;
public class DeviceAdapter  extends RecyclerView.Adapter<DeviceAdapter.MyViewHolder>
        implements Filterable {

    private Context context;
    private List<Device> deviceList;
    private List<Device> deviceListFiltered;
    private DeviceAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public SwitchMaterial check_state;
        public ImageView img_hadeare;
        public ImageView img_wifi;
        public TextView txt_title, txt_sub_title;

        public MyViewHolder(View view) {
            super(view);


            check_state = view.findViewById(R.id.check_state);
            img_hadeare = view.findViewById(R.id.img_hadeare);
            img_wifi = view.findViewById(R.id.img_wifi);
            txt_title = view.findViewById(R.id.txt_title);
            txt_sub_title = view.findViewById(R.id.txt_sub_title);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onDeviceSelected(deviceListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public DeviceAdapter(Context context, List<Device> deviceList, DeviceAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.deviceList = deviceList;
        this.deviceListFiltered = deviceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_dispositivo, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Device device = deviceListFiltered.get(position);
        holder.txt_title.setText(device.getTitle());
        holder.txt_sub_title.setText(device.getLocation());

        Glide.with(context)
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
        }
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
                    List<Device> filteredList = new ArrayList<>();
                    for (Device row : deviceList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getLocation().contains(charSequence)) {
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
                deviceListFiltered = (ArrayList<Device>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface DeviceAdapterListener {
        void onDeviceSelected(Device contact);
    }
}
