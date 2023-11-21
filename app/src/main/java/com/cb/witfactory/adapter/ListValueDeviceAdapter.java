package com.cb.witfactory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cb.witfactory.R;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.model.Callfun;

import java.util.ArrayList;
import java.util.List;

public class ListValueDeviceAdapter extends RecyclerView.Adapter<ListValueDeviceAdapter.MyViewHolder>
        implements Filterable {

    private Context context;
    private List<Metric> deviceList;
    private List<Metric> deviceListFiltered;
    private ValueDeviceAdapterListener listener;
    private boolean isImage1 = true; // Para alternar entre las dos imágenes


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_icon,img_exelent,img_down;
        public TextView title_device, txt_description,color_estado;

        public LinearLayout semaforo_layout;

        public MyViewHolder(View view) {
            super(view);


            img_icon = view.findViewById(R.id.img_icon);
            img_exelent = view.findViewById(R.id.img_exelent);
            img_down = view.findViewById(R.id.img_down);
            title_device = view.findViewById(R.id.title_device);
            txt_description = view.findViewById(R.id.txt_description);
            color_estado = view.findViewById(R.id.color_estado);
            semaforo_layout = view.findViewById(R.id.semaforo_layout);




        }
    }

    public ListValueDeviceAdapter(Context context, List<Metric> deviceList, ValueDeviceAdapterListener listener) {
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
        final Metric events = deviceListFiltered.get(position);
        holder.title_device.setText(events.getValue()+"");
        holder.txt_description.setText(events.getTitle().toString());
        String color = events.getColor().toString();

        if(color.equals("green")){

            holder.color_estado.setBackgroundResource(R.color.green);
            holder.img_exelent.setBackgroundResource(R.drawable.ic_button_exelent);
        }
        if(color.equals("red")){
            holder.color_estado.setBackgroundResource(R.color.red);
        }

        if(color.equals("orange")){
            holder.color_estado.setBackgroundResource(R.color.orange);

        }

        if(color.equals("blue")){
            holder.color_estado.setBackgroundResource(R.color.blue);

        }


        holder.img_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Define la animación de desvanecimiento
                final Animation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                fadeOut.setDuration(500); // Duración de la animación en milisegundos

                // Define un oyente de animación para cambiar la imagen después de desvanecer
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        if (isImage1) {
                            Animation fadeIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);

                            holder.img_down.setBackgroundResource(R.drawable.arrow_up);
                            holder.semaforo_layout.setVisibility(View.GONE);
                            holder.semaforo_layout.startAnimation(fadeIn);
                            isImage1 = false;

                        } else {
                            Animation fadeOut = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
                            holder.img_down.setBackgroundResource(R.drawable.arrow_down);
                            holder.semaforo_layout.setVisibility(View.VISIBLE);
                            holder.semaforo_layout.startAnimation(fadeOut);
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
                    List<Metric> filteredList = new ArrayList<>();
                    for (Metric row : deviceList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toString().toLowerCase().contains(charString.toLowerCase()) || row.getTitle().toString().contains(charSequence)) {
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
                deviceListFiltered = (ArrayList<Metric>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ValueDeviceAdapterListener {
        void onListValueDeviceSelected(Metric device);
    }
}
