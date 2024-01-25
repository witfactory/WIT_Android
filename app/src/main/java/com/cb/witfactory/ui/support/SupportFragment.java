package com.cb.witfactory.ui.support;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cb.witfactory.databinding.FragmentSupportBinding;
import com.cb.witfactory.model.PreferencesHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SupportFragment extends Fragment {

    private SupportViewModel mViewModel;
    private FragmentSupportBinding binding;

    FirebaseDatabase database;
    DatabaseReference chatReference;

    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    public static SupportFragment newInstance() {
        return new SupportFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSupportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        database = FirebaseDatabase.getInstance();
        chatReference = database.getReference("chat");


        String userEmail = PreferencesHelper.getUserId("email", "");
        String userid = PreferencesHelper.getUserId("userId", "");

        // Obtener referencia a la base de datos
        chatReference = FirebaseDatabase.getInstance().getReference().child("chat")
                .child(userid);

        // Configurar RecyclerView
        binding.rvMensajes.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageList = new ArrayList<>();


        // Leer datos desde Firebase

        binding.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!binding.txtMensaje.getText().toString().isEmpty()){
                    String currentDateTime = DateTimeHelper.getCurrentDateTime();
                    Log.d("Fecha y Hora Actual", currentDateTime);

                    Message message = new Message(binding.txtMensaje.getText().toString(), userEmail,currentDateTime);
                    //chatReference.push().setValue(message);
                    String messageId = chatReference.push().getKey(); // Generar una clave única

                    chatReference.child(messageId).setValue(message);

                    binding.txtMensaje.setText("");
                }else{
                    validateData();
                }


            }
        });


        chatReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {




                // Itera sobre los mensajes y actualiza tu interfaz de usuario
                for (DataSnapshot chatSnapshot : snapshot.getChildren()){
                    var fecha =  chatSnapshot.child("fecha").getValue().toString();
                    var mensaje =  chatSnapshot.child("mensaje").getValue().toString();
                    var user =  chatSnapshot.child("user").getValue().toString();
                    Message message = new Message(mensaje,user,fecha);
                    if (message != null) {
                        messageList.add(message);
                    }
                }

                messageAdapter = new MessageAdapter(getActivity(),messageList);
                binding.rvMensajes.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Maneja errores aquí
            }
        });


        return root;
    }

    public void validateData(){
        new SweetAlertDialog(getActivity())
                .setTitleText("Required fields")
                .show();
    }



}