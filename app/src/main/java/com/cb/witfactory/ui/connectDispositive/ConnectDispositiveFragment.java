package com.cb.witfactory.ui.connectDispositive;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cb.witfactory.databinding.FragmentConnectDispositiveBinding;

public class ConnectDispositiveFragment extends Fragment {

    private ConnectDispositiveViewModel mViewModel;
    private FragmentConnectDispositiveBinding mBinding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        mBinding = FragmentConnectDispositiveBinding.inflate(inflater, container, false);
        View root = mBinding.getRoot();



        mBinding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "hola", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(requireContext(), EspTouchActivity.class));
            }
        });
        return root;
    }
}