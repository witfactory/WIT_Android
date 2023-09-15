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

import com.cb.witfactory.R;
import com.cb.witfactory.data.retrofit.events.PayloadResponse;
import com.cb.witfactory.model.Callfun;

import java.util.ArrayList;
import java.util.List;

public class ListValueDeviceAdapter extends RecyclerView.Adapter<ListValueDeviceAdapter.MyViewHolder>
        implements Filterable {

    private Context context;
    private List<PayloadResponse> deviceList;
    private List<PayloadResponse> deviceListFiltered;
    private ValueDeviceAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_icon,img_exelent;
        public TextView title_device, txt_description;

        public MyViewHolder(View view) {
            super(view);


            img_icon = view.findViewById(R.id.img_icon);
            img_exelent = view.findViewById(R.id.img_exelent);
            title_device = view.findViewById(R.id.title_device);
            txt_description = view.findViewById(R.id.txt_description);

        }
    }

    public ListValueDeviceAdapter(Context context, List<PayloadResponse> deviceList, ValueDeviceAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.deviceList = deviceList;
        this.deviceListFiltered = deviceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_adapter_dispositivo2, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PayloadResponse events = deviceListFiltered.get(0);
        holder.title_device.setText(events.getTemp().toString());
        holder.txt_description.setText("Valor:");



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
                    List<PayloadResponse> filteredList = new ArrayList<>();
                    for (PayloadResponse row : deviceList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTemp().toString().toLowerCase().contains(charString.toLowerCase()) || row.getTemp().toString().contains(charSequence)) {
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
                deviceListFiltered = (ArrayList<PayloadResponse>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ValueDeviceAdapterListener {
        void onListValueDeviceSelected(PayloadResponse device);
    }
}
