package com.cb.witfactory.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cb.witfactory.R;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.ui.activity.ActivityFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Context context;
    private List<Event> deviceMetricsList;

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
            holder.alertIcon.setVisibility(View.VISIBLE);
        } else {
            holder.titleTextView.setTextColor(ContextCompat.getColor(context, R.color.green));
            holder.alertIcon.setVisibility(View.GONE);
        }
        holder.date.setText(formattedDateTime);
        holder.titleTextView.setText(event.getTitle());
    }


    private boolean checkForHighValues(List<Metric> metrics) {
        // Implement your logic to check if there are events with high values
        // For example, you can iterate through the metrics and check the conditions
        // ...

        return false; // Change this to return true if there are high values
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
            // Initialize other views as needed
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
}

