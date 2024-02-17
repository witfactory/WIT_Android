package com.cb.witfactory.view.ui.verificar_cuenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cb.witfactory.databinding.FragmentVerificaTuCuentaBinding;
import com.cb.witfactory.view.HomeActivity;

public class VerificaTuCuentaFragment extends Fragment {

    private FragmentVerificaTuCuentaBinding binding;
    private VerificaTuCuentaViewModel mViewModel;

    public static VerificaTuCuentaFragment newInstance() {
        return new VerificaTuCuentaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVerificaTuCuentaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }



}