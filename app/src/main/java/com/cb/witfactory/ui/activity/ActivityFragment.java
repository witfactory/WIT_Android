package com.cb.witfactory.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cb.witfactory.databinding.FragmentActivityBinding;


public class ActivityFragment extends Fragment {

    private FragmentActivityBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActivityViewModel loginViewModel =
                new ViewModelProvider(this).get(ActivityViewModel.class);

        binding = FragmentActivityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //https://aws.amazon.com/es/blogs/mobile/building-an-android-app-with-aws-amplify-part-1/

        return root;
    }


}