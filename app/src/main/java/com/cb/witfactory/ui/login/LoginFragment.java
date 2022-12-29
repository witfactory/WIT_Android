package com.cb.witfactory.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cb.witfactory.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        com.cb.witfactory.ui.login.LoginViewModel loginViewModel =
                new ViewModelProvider(this).get(com.cb.witfactory.ui.login.LoginViewModel.class);

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //https://aws.amazon.com/es/blogs/mobile/building-an-android-app-with-aws-amplify-part-1/

        return root;
    }


}