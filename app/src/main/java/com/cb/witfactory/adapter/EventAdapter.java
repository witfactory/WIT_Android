package com.cb.witfactory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cb.witfactory.R;
import com.cb.witfactory.view.ui.activity.ActivityFragment;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Context context;
    private List<Event> deviceMetricsList;
    private EventAdapterListener listener;

    public EventAdapter(Context context, List<Event> deviceMetricsList, ActivityFragment activityFragment) {
        this.context = context;
        this.deviceMetricsList = deviceMetricsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = deviceMetricsList.get(position);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  // Elige el formato deseado
        String formattedDateTime = event.getTimestamp().format(formatter);

        if (Objects.equals(event.getColor(), "red")) {
            holder.titleTextView.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.alertIcon.setAlpha(1.0f);
            holder.titleTextView.setText("Events");
        } else {
            holder.titleTextView.setTextColor(ContextCompat.getColor(context, R.color.green));
            holder.alertIcon.setAlpha(0.0f);
            holder.titleTextView.setText("No events");
        }

        // Utiliza un formato diferente para mostrar solo la hora
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = event.getTimestamp().format(timeFormatter);

        holder.date.setText(formattedTime);

        holder.itemView.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION && listener != null) {
                listener.onEventItemClicked(deviceMetricsList.get(adapterPosition), adapterPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return deviceMetricsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView titleTextView;
        ImageView alertIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            alertIcon = itemView.findViewById(R.id.alertIcon);

            // Agregar el OnClickListener al itemView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Obtener la posición del elemento clickeado
                    int position = getAdapterPosition();
                }
            });
        }
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    private boolean checkForRedMetrics(List<Event> events) {
        for (Event event : events) {
            if ("red".equalsIgnoreCase(event.getColor())) {
                return true; // Se encontró al menos una métrica roja
            }
        }
        return false; // Ninguna métrica es roja
    }

    public interface EventAdapterListener {
        void onEventItemClicked(Event clickedEvent, int position);
    }

    public EventAdapter(Context context, List<Event> deviceMetricsList, EventAdapterListener listener) {
        this.context = context;
        this.deviceMetricsList = deviceMetricsList;
        this.listener = listener;
    }
}

