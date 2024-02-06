package com.cb.witfactory.ui.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cb.witfactory.R;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.ui.device.DeviceViewModel;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>
        implements Filterable {

    private Context context;
    private List<Message> messageList;
    private List<Message> messageListFiltered;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView fechaTextView,fechaTextView2;
        TextView mensajeTextView, mensajeTextView2;

        LinearLayout layout1,layout2;
        public MyViewHolder(View view) {
            super(view);


            fechaTextView = itemView.findViewById(R.id.fechaTextView);
            fechaTextView2 = itemView.findViewById(R.id.fechaTextView2);
            mensajeTextView = itemView.findViewById(R.id.mensajeTextView);
            mensajeTextView2 = itemView.findViewById(R.id.mensajeTextView2);
            layout1 = itemView.findViewById(R.id.layout1);
            layout2 = itemView.findViewById(R.id.layout2);
        }
    }

    public MessageAdapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
        this.messageListFiltered = messageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_support_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Message message = messageListFiltered.get(position);

        if (message != null) {
            String userEmail = PreferencesHelper.getUserId("email", "");

            if(userEmail.equals(message.getUser())){
                holder.layout1.setVisibility(View.VISIBLE);
                holder.fechaTextView.setText(message.getFecha());
                holder.mensajeTextView.setText(message.getMensaje());
            }
            if(!userEmail.equals(message.getUser())){
                holder.layout2.setVisibility(View.VISIBLE);
                holder.fechaTextView2.setText(message.getFecha());
                holder.mensajeTextView2.setText(message.getMensaje());
            }

        }


    }

    @Override
    public int getItemCount() {
        return messageListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    messageListFiltered = messageList;
                } else {
                    List<Message> filteredList = new ArrayList<>();
                    for (Message row : messageList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMensaje().toLowerCase().contains(charString.toLowerCase()) || row.getMensaje().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    messageListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = messageListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                messageListFiltered = (ArrayList<Message>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void filterList(ArrayList<Message> filteredList) {
        messageListFiltered = filteredList;
        notifyDataSetChanged();
    }


}
