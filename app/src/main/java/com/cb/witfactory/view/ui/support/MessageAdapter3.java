package com.cb.witfactory.view.ui.support;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cb.witfactory.R;
import com.cb.witfactory.model.PreferencesHelper;

import java.util.List;

public class MessageAdapter3 extends RecyclerView.Adapter<MessageAdapter3.ViewHolder> {

    private List<Message> messageList;

    public MessageAdapter3(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_support_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messageList.get(position);

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
        return messageList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fechaTextView,fechaTextView2;
        TextView mensajeTextView, mensajeTextView2;

        LinearLayout layout1,layout2;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
            fechaTextView2 = itemView.findViewById(R.id.fechaTextView2);
            mensajeTextView = itemView.findViewById(R.id.mensajeTextView);
            mensajeTextView2 = itemView.findViewById(R.id.mensajeTextView2);
            layout1 = itemView.findViewById(R.id.layout1);
            layout2 = itemView.findViewById(R.id.layout2);
        }
    }
}
