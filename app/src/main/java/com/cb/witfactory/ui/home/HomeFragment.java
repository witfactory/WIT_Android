package com.cb.witfactory.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.amplifyframework.auth.AuthSession;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.cb.witfactory.R;
import com.cb.witfactory.data.classModel.AmplifyCognito;
import com.cb.witfactory.data.classModel.Utils;
import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;
import com.cb.witfactory.databinding.FragmentHomeBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.PreferencesHelper;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class HomeFragment extends Fragment implements Callfun {

    AmplifyCognito amplifyCognito = null;
    private FragmentHomeBinding binding;
    private PreferencesHelper preferencesHelper;
    final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;
    SweetAlertDialog pDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        preferencesHelper = new PreferencesHelper(getContext());

        amplifyCognito = new AmplifyCognito(getActivity());
        amplifyCognito.setListener(HomeFragment.this);


        validateInternet();


        binding.txtUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_login_menu);
                navController.navigateUp();
                navController.navigate(R.id.menu_device);
            }
        });


        return root;
    }

    public void validateInternet(){
        if(!Utils.internetstaus(getActivity())){

            Utils.aletSinInternet(getActivity());
        }else{
            amplifyCognito.validarAuth();
        }
    }

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onSuccess(Object o,String options) {

        if(options.equals("idUser")){
            AuthSession auths = (AuthSession) o;
            String user_aws = ((AWSCognitoAuthSession) auths).getUserSubResult().getValue().toString();
            Log.v("session",auths.toString());
            PreferencesHelper.setUserAws("user_aws", user_aws.toString());
            loaduser();
        }

        if(options.equals("getuser")){
            ObjectResponseUser bodyResponseUser = (ObjectResponseUser) o;
            String user =bodyResponseUser.getBody().get(0).getUser_principal().toString();
            String[] arrOfStr = user.split("@");

            String dataUser = arrOfStr[0];

            binding.txtUser.setText(getString(R.string.text_welcome_user) + "\n" + dataUser);
        }


    }

    @Override
    public void onError(String s) {
    }

    public  void loaduser(){

        String user_aws = PreferencesHelper.getUserAws("user_aws","").toString();

        homeViewModel.setListener(HomeFragment.this);
        homeViewModel.getDataUSer(user_aws);

    }


}