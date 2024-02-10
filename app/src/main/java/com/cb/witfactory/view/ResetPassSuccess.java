package com.cb.witfactory.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cb.witfactory.R;
import com.cb.witfactory.databinding.ActivityResetPassSuccessBinding;

public class ResetPassSuccess extends AppCompatActivity {
    private ActivityResetPassSuccessBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass_success);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_pass_success);
        binding.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin(getApplicationContext());
            }
        });
    }

    public static void goToLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}