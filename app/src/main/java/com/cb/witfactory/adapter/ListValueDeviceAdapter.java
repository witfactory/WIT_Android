package com.cb.witfactory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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
    private boolean isImage1 = true; // Para alternar entre las dos imágenes


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_icon,img_exelent,img_down;
        public TextView title_device, txt_description,color_estado;

        public MyViewHolder(View view) {
            super(view);


            img_icon = view.findViewById(R.id.img_icon);
            img_exelent = view.findViewById(R.id.img_exelent);
            img_down = view.findViewById(R.id.img_down);
            title_device = view.findViewById(R.id.title_device);
            txt_description = view.findViewById(R.id.txt_description);
            color_estado = view.findViewById(R.id.color_estado);

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

        holder.img_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Define la animación de desvanecimiento
                final Animation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                fadeOut.setDuration(700); // Duración de la animación en milisegundos

                // Define un oyente de animación para cambiar la imagen después de desvanecer
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (isImage1) {
                            holder.img_down.setBackgroundResource(R.drawable.arrow_up);
                            isImage1 = false;

                        } else {
                            holder.img_down.setBackgroundResource(R.drawable.arrow_down);
                            isImage1 = true;

                        }
                      //  isImage1 = !isImage1;
                      //  holder.img_down.startAnimation(fadeOut); // Iniciar animación de desvanecimiento nuevamente
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                // Inicia la animación de desvanecimiento
                holder.img_down.startAnimation(fadeOut);

            }
        });



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
