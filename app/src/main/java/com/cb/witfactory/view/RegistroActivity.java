package com.cb.witfactory.view;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.cb.witfactory.R;
import com.cb.witfactory.databinding.ActivityRegistroBinding;
import com.cb.witfactory.ui.register.RegisterFragment;

public class RegistroActivity extends AppCompatActivity {

    private ActivityRegistroBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_registro);


        NavController navController = Navigation.findNavController(this, R.id.nav_registro);
        navController.navigateUp();
        navController.navigate(R.id.nenu_register);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}