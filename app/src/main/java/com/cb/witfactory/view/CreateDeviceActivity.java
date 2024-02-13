package com.cb.witfactory.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;

import com.cb.witfactory.R;
import com.cb.witfactory.data.classModel.AmplifyCognito;
import com.cb.witfactory.data.retrofit.device.CreateDevice;
import com.cb.witfactory.databinding.ActivityCreateDeviceBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.ui.home.HomeFragment;
import com.cb.witfactory.ui.home.HomeViewModel;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CreateDeviceActivity extends AppCompatActivity implements Callfun {

    private ActivityCreateDeviceBinding binding;
    AmplifyCognito amplifyCognito = null;
    private PreferencesHelper preferencesHelper;
    private CreateDeviceViewModel createDeviceViewModel;

    private SweetAlertDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_device);

        createDeviceViewModel =
                new ViewModelProvider(this).get(CreateDeviceViewModel.class);


        amplifyCognito = new AmplifyCognito(getApplicationContext());
        amplifyCognito.setListener(CreateDeviceActivity.this);
        preferencesHelper = new PreferencesHelper(getApplicationContext());

        binding.txtNameDevice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    validateData();

                }
            }
        });
        binding.deviceLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    validateData();

                }
            }
        });
        binding.descriptionLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    validateData();

                }
            }
        });


        binding.btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog();
                String name = binding.txtNameDevice.getText().toString();
                String deviceLocation = binding.descriptionLocation.getText().toString();
                String descriptionLocation = binding.descriptionLocation.getText().toString();


                String email = PreferencesHelper.getEmail("email", "");
                String userId = PreferencesHelper.getEmail("userId", "");
                String deviceId = PreferencesHelper.getDeviceId("deviceId", "");

                String devicetype = PreferencesHelper.getdevicetype("devicetype", "");
                String serial = PreferencesHelper.getSerial("serial", "");
                String mac = PreferencesHelper.getMac("mac", "");


                CreateDevice createDevice = new CreateDevice(name,deviceId,serial,"0.0","0.0",userId,mac,deviceLocation,"V",descriptionLocation,false,true);
                createDeviceViewModel.setListener(CreateDeviceActivity.this);
                createDeviceViewModel.createDataDevice(createDevice);


            }
        });


    }


    private void validateData() {

        String name = binding.txtNameDevice.getHint().toString();
        String locationdevice = binding.deviceLocation.getHint().toString();
        String description = binding.descriptionLocation.getHint().toString();

        if (!name.isEmpty() && !locationdevice.isEmpty() && !description.isEmpty()) {
            binding.btnAddDevice.setClickable(true);
            binding.btnAddDevice.setEnabled(true);
            binding.btnAddDevice.setBackgroundResource(R.drawable.btn_rounded_bacgraund);
            binding.btnAddDevice.setTextColor(Color.WHITE);


        } else {
            binding.btnAddDevice.setBackgroundResource(R.drawable.btn_rounded_gray);
            binding.btnAddDevice.setTextColor(Color.BLACK);
            binding.btnAddDevice.setClickable(false);
            binding.btnAddDevice.setEnabled(false);

        }
    }


    @Override
    public void onSuccess(String s) {

        hidenDialog();
    }

    @Override
    public void onSuccess(Object o, String s) {

        hidenDialog();
        //llega  aca
        Intent intent = new Intent(getApplicationContext(), WitMenu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        finish();
    }

    @Override
    public void onError(String s) {

        hidenDialog();
    }


    public void showDialog(){
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("creating device...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void hidenDialog(){
        try {
            if(pDialog != null){
                pDialog.dismiss();
            }
        }catch (Exception e){
            Log.v("errors", e.getMessage());
        }
    }
}