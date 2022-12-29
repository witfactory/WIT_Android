package com.cb.witfactory.view;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cb.witfactory.R;
import com.cb.witfactory.databinding.ActivityRegistroBinding;
import com.cb.witfactory.ui.register.RegisterFragment;

public class RegistroActivity extends AppCompatActivity {

    private ActivityRegistroBinding binding;

    RegisterFragment registerFragment;
    FragmentManager fragmentManager = null;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_registro);


        registerFragment = new RegisterFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.registro, registerFragment).commit();


    }
}