package com.cb.witfactory.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.cb.witfactory.R;
import com.cb.witfactory.data.retrofit.user.GetUserResponse;
import com.cb.witfactory.databinding.FragmentHomeBinding;
import com.cb.witfactory.databinding.FragmentPerfilBinding;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.ui.home.HomeViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel mViewModel;
    private PreferencesHelper preferencesHelper;
    PerfilViewModel perfilViewModel;
    private NavigationView navigationView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        preferencesHelper = new PreferencesHelper(getContext());



       String user= PreferencesHelper.getUser("user", "");
        String email= PreferencesHelper.getEmail("email", "");

        String[] arrOfStr = user.split("@");

        String dataUser= arrOfStr[0];

        binding.txtUser.setText(dataUser);
        binding.txtUserEmail.setText(email);

        perfilViewModel.getDataUSer("d86a2ef1-7db2-4611-a8b1-9bdf43b1d3e4");
        getDataUser();

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_login_menu);
                navController.navigateUp();
                navController.navigate(R.id.edit_profile);
            }
        });

        return root;
    }

    public void getDataUser(){

        perfilViewModel.getUserObserver().observe(getActivity(), new Observer<List<GetUserResponse>>() {
            @Override
            public void onChanged(List<GetUserResponse> getUserResponses) {
                if(getUserResponses != null){
                    Toast.makeText(getActivity(), getUserResponses.get(0).getUser().toString()+"", Toast.LENGTH_SHORT).show();

                    binding.txtUser.setText("Data");
                    binding.txtUserEmail.setText(getUserResponses.get(0).getUser().toString());
                    binding.txtAddres.setText(getUserResponses.get(0).getAddress().toString());
                    binding.txtPhone.setText(getUserResponses.get(0).getTelephone().toString());
                }
            }
        });
    }

}