package com.cb.witfactory.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.cb.witfactory.R;
import com.cb.witfactory.data.classModel.AmplifyCognito;
import com.cb.witfactory.data.model.DialogResetPassword;
import com.cb.witfactory.databinding.ActivityResetPassWordBinding;
import com.cb.witfactory.model.Callfun;

public class ResetPassWordActivity extends AppCompatActivity implements Callfun {
    private ActivityResetPassWordBinding binding;
    AmplifyCognito amplifyCognito = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_pass_word);

        amplifyCognito = new AmplifyCognito(getApplicationContext());
        amplifyCognito.setListener(ResetPassWordActivity.this);


        binding.txtEmail.addTextChangedListener(new TextWatcher() {
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

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Patterns.EMAIL_ADDRESS.matcher(binding.txtEmail.getHint().toString()).matches()) {
                    String email  = binding.txtEmail.getText().toString();
                    amplifyCognito.resetPassword(email);
                }
            }
        });

    }

    private void validateData() {

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.txtEmail.getHint().toString()).matches()) {
            binding.btnSignUp.setClickable(true);
            binding.btnSignUp.setEnabled(true);
            binding.btnSignUp.setBackgroundResource(R.drawable.btn_rounded_bacgraund);
            binding.btnSignUp.setTextColor(Color.WHITE);


        } else {
            binding.btnSignUp.setBackgroundResource(R.drawable.btn_rounded_gray);
            binding.btnSignUp.setTextColor(Color.BLACK);
            binding.btnSignUp.setClickable(false);
            binding.btnSignUp.setEnabled(false);

        }
    }

    @Override
    public void onSuccess(String s) {
      //  FragmentManager fragmentManager = getSupportFragmentManager();

        DialogResetPassword dialogFragment = new DialogResetPassword();
        dialogFragment.setStyle(DialogFragment.STYLE_NO_FRAME, 0);
        dialogFragment.show(getSupportFragmentManager(),"dialogo_reset_password");
    }

    @Override
    public void onError(String s) {

    }
}