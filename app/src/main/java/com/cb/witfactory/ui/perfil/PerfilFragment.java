package com.cb.witfactory.ui.perfil;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cb.witfactory.R;
import com.cb.witfactory.databinding.FragmentHomeBinding;
import com.cb.witfactory.databinding.FragmentPerfilBinding;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.ui.home.HomeViewModel;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel mViewModel;
    private PreferencesHelper preferencesHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        preferencesHelper = new PreferencesHelper(getContext());

       String user= PreferencesHelper.getUser("user", "");
        String email= PreferencesHelper.getEmail("email", "");

        String[] arrOfStr = user.split("@");

        String dataUser= arrOfStr[0];

        binding.txtUser.setText(dataUser);
        binding.txtUserEmail.setText(email);



        return root;
    }

}