package com.cb.witfactory.ui.support;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.google.firebase.database.ValueEventListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SupportFragment extends Fragment {

    private SupportViewModel mViewModel;
    private FragmentSupportBinding binding;

    FirebaseDatabase database;
    DatabaseReference chatReference;

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


        binding.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!binding.txtMensaje.getText().toString().isEmpty()){
                    String userEmail = PreferencesHelper.getUserId("email", "");
                    String userid = PreferencesHelper.getUserId("userId", "");
                    String currentDateTime = DateTimeHelper.getCurrentDateTime();
                    Log.d("Fecha y Hora Actual", currentDateTime);

                    Message message = new Message(binding.txtMensaje.getText().toString(), userEmail,currentDateTime);
                    //chatReference.push().setValue(message);
                    String messageId = chatReference.push().getKey(); // Generar una clave única

                    chatReference.child(userid+"/"+messageId).setValue(message);

                    binding.txtMensaje.setText("");
                }else{
                    validateData();
                }


            }
        });


        // Dentro de tu actividad o fragmento
        chatReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Itera sobre los mensajes y actualiza tu interfaz de usuario
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //Message message = snapshot.getValue(Message.class);


                   Log.v("mensaje recivido", snapshot.toString());
                }
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