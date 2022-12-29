
package com.cb.witfactory.ui.register;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.cb.witfactory.R;
import com.cb.witfactory.data.classModel.Utils;
import com.cb.witfactory.databinding.RegisterFragmentBinding;
import com.cb.witfactory.model.PreferencesHelper;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    private RegisterFragmentBinding binding;
    private PreferencesHelper preferencesHelper;

    Register2Fragment registerFragment;
    FragmentManager fragmentManager = null;
    FragmentTransaction fragmentTransaction;

    String txtUser="", txtEmail="", txtPin="", txtComfirmPin="";

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        RegisterViewModel homeViewModel =
                new ViewModelProvider(this).get(RegisterViewModel.class);

        binding = RegisterFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        preferencesHelper = new PreferencesHelper(getContext());


        binding.txtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 2) {
                    validateData();

                }
            }
        });
        binding.txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 5) {
                    validateData();

                }
            }
        });
        binding.txtPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 6) {
                    validateData();

                }
            }
        });
        binding.txtComfirmPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 6) {
                    validateData();

                }
            }
        });

        binding.btnSignUp.setBackgroundResource(R.drawable.ic_btn_inactivo);

        binding.btnSignUp.setClickable(false);
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txtUser.isEmpty() && !txtEmail.isEmpty() && !txtPin.isEmpty() && !txtComfirmPin.isEmpty()) {

                    if (!Utils.isValidPassword(txtPin)){
                        Toast.makeText(getContext(), "Contraseña muy debil", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (txtPin.toString().equals(txtComfirmPin.toString())) {
                        PreferencesHelper.setUser("user", txtEmail.toString());
                        PreferencesHelper.setEmail("email", txtEmail.toString());
                        PreferencesHelper.setPassword("password", txtPin.toString());

                        registerFragment = new Register2Fragment();
                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.registro, registerFragment).commit();
                    }
                }
            }
        });


        return root;
    }

    public void validateData() {
        txtUser = binding.txtUser.getText().toString();
        txtEmail = binding.txtEmail.getText().toString();
        txtPin = binding.txtPin.getText().toString();
        txtComfirmPin = binding.txtComfirmPin.getText().toString();


        if (!txtUser.isEmpty() && !txtEmail.isEmpty() && !txtPin.isEmpty() && !txtComfirmPin.isEmpty()) {

            if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()) {
                binding.txtEmail.setError(getString(R.string.write_your_email_correctly));
                return;
            }
            if (txtPin.toString().equals(txtComfirmPin.toString())) {
                binding.btnSignUp.setClickable(true);
                binding.btnSignUp.setBackgroundResource(R.drawable.ic_btn_activo);
            } else {
                Toast.makeText(getContext(), getString(R.string.passwords_not_mismatch), Toast.LENGTH_SHORT).show();
                binding.btnSignUp.setBackgroundResource(R.drawable.ic_btn_inactivo);
                binding.btnSignUp.setClickable(false);
            }
        }else{
            binding.btnSignUp.setBackgroundResource(R.drawable.ic_btn_inactivo);
        }
    }


}