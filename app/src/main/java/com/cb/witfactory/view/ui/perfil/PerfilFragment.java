package com.cb.witfactory.view.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.cb.witfactory.R;
import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;
import com.cb.witfactory.databinding.FragmentPerfilBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.PreferencesHelper;
import com.google.android.material.navigation.NavigationView;

public class PerfilFragment extends Fragment implements Callfun {

    private FragmentPerfilBinding binding;

    private PreferencesHelper preferencesHelper;
    PerfilViewModel perfilViewModel;
    private NavigationView navigationView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        preferencesHelper = new PreferencesHelper(getContext());
        perfilViewModel.getDataUSer(PreferencesHelper.getUserAws("user_aws","").toString());


        loaduser();
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


    public  void loaduser(){
        String user_aws = PreferencesHelper.getUserAws("user_aws","").toString();

        perfilViewModel.setListener(PerfilFragment.this);
        perfilViewModel.getDataUSer(user_aws);

    }


    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onSuccess(Object o, String s) {

        if(s.equals("getuser")){
            ObjectResponseUser bodyResponseUser = (ObjectResponseUser) o;
            String useremail =bodyResponseUser.getBody().get(0).getUser_principal().toString();
            String adddress =bodyResponseUser.getBody().get(0).getAddress().toString();
            String[] arrOfStr = useremail.split("@");

            String dataUser = arrOfStr[0];

            binding.txtUser.setText(dataUser);
            binding.txtUserEmail.setText(useremail);
            binding.txtAddres.setText(adddress);
            binding.txtPhone.setText("+57 310-000-0000");
            binding.txtPassword.setText("********");

        }

    }

    @Override
    public void onError(String s) {

    }
}