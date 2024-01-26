package com.cb.witfactory.ui.perfil;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.cb.witfactory.adapter.CountryAdapter;
import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;
import com.cb.witfactory.data.retrofit.user.UpdateUserResponse;
import com.cb.witfactory.data.retrofit.user.keyUserResponse;
import com.cb.witfactory.databinding.FragmentEditProfileBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.CityResponse;
import com.cb.witfactory.model.CityService;
import com.cb.witfactory.model.CountryCapitals;
import com.cb.witfactory.model.CountryResponse;
import com.cb.witfactory.model.CountryService;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.R;
import com.cb.witfactory.ui.home.HomeViewModel;
import com.cb.witfactory.ui.home.UserIdHolder;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileFragment extends Fragment implements Callfun {

    private FragmentEditProfileBinding binding;
    private EditProfileViewModel mViewModel;
    Retrofit retrofit;
    private HomeViewModel homeViewModel;
    PerfilViewModel perfilViewModel;
    private PreferencesHelper preferencesHelper;
    TextView userName;
    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.setListener(this);
        String userId = UserIdHolder.getInstance().getUserId();
        homeViewModel.getDataUSer(userId);
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        preferencesHelper = new PreferencesHelper(getContext());
        SwitchMaterial switchMaterial = root.findViewById(R.id.control_parental);
        Button btnPing = root.findViewById(R.id.btn_ping);
        switchMaterial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Cambia la visibilidad del botón según el estado del SwitchMaterial
            btnPing.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        btnPing.setOnClickListener(view -> openPinFragment());
        mViewModel = new ViewModelProvider(this).get(EditProfileViewModel.class);

        binding.btnEdit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
            }
        });

        binding.lblChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    navigateToPasswordFragment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        AutoCompleteTextView autoCompleteTextView = root.findViewById(R.id.txt_country);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://countriesnow.space/api/v0.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountryService countryService = retrofit.create(CountryService.class);
        Call<CountryResponse> call = countryService.getCountries();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    List<CountryCapitals> countries = response.body().getData();
                    List<String> countryNames = new ArrayList<>();
                    for (CountryCapitals country : countries) {
                        countryNames.add(country.getName());
                    }

                    // Crear un CountryAdapter y configurarlo en el AutoCompleteTextView
                    CountryAdapter countryAdapter = new CountryAdapter(requireContext(), countries);
                    binding.txtCountry.setAdapter(countryAdapter);

                    // Crear un ArrayAdapter y configurarlo en el AutoCompleteTextView
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                            android.R.layout.simple_dropdown_item_1line, countryNames);

                    autoCompleteTextView.setAdapter(adapter);
                } else {
                    // errores
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                // errores de la solicitud
            }
        });
        AutoCompleteTextView autoTxtCity = root.findViewById(R.id.txt_city);

        binding.txtCountry.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCountry = (String) parent.getItemAtPosition(position);
            getCitiesForCountry(selectedCountry, autoTxtCity);
        });
        userName = root.findViewById(R.id.userName);
        return root;
    }

    private void updateUserProfile() {
        UpdateUserResponse updateUserRequest = new UpdateUserResponse();
        String user_aws = PreferencesHelper.getUserAws("user_aws","").toString();
        keyUserResponse key = new keyUserResponse(user_aws);
        key.setId(user_aws);
        updateUserRequest.setKey(key);
        updateUserRequest.setAddress(binding.txtAddress.getText().toString());
        updateUserRequest.setCity(binding.txtCity.getText().toString());
        updateUserRequest.setFirst_name(binding.txtUser.getText().toString());
        updateUserRequest.setLast_name(binding.txtLastName.getText().toString());
        updateUserRequest.setTelephone(binding.txtCellPhone.getText().toString());
        updateUserRequest.setZip_code(binding.txtZipCode.getText().toString());
        updateUserRequest.setCountry(binding.txtCountry.getText().toString());
        updateUserRequest.setSuite("test2");
        updateUserRequest.setAppos("mod");
        mViewModel.updateUser(updateUserRequest);
        navigateToEditProfileDetailsFragment();
    }

    private void openPinFragment() {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_login_menu);
        navController.navigateUp();
        navController.navigate(R.id.fragment_ping);
    }

    private void getCitiesForCountry(String country, AutoCompleteTextView autoTxtCity) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("country", country);

        CityService cityService = retrofit.create(CityService.class);
        Call<CityResponse> call = cityService.getCities(requestBody);
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isError()) {
                    List<String> cities = response.body().getData();
                    // Actualizar AutoCompleteTextView con la lista de ciudades
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                            android.R.layout.simple_dropdown_item_1line, cities);
                    autoTxtCity.setAdapter(adapter);
                } else {
                    // Manejar errores de la respuesta
                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                // Manejar errores de la solicitud
            }
        });
    }

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onSuccess(Object o, String s) {

        if(s.equals("getuser")){
            ObjectResponseUser bodyResponseUser = (ObjectResponseUser) o;
            String useremail = bodyResponseUser.getBody().get(0).getUser_principal().toString();
            String adddress = bodyResponseUser.getBody().get(0).getAddress().toString();
            String[] arrOfStr = useremail.split("@");
            String dataUser = arrOfStr[0];
            String firstName = bodyResponseUser.getBody().get(0).getFirstName().toString();
            String lastName = bodyResponseUser.getBody().get(0).getLastName().toString();
            userName.setText(firstName + " " + lastName);
        }

    }

    private void navigateToEditProfileDetailsFragment() {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_login_menu);
        navController.navigateUp();
        navController.navigate(R.id.fragment_edit_profile_detail);
    }

    private void navigateToPasswordFragment() {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_login_menu);
        navController.navigateUp();
        navController.navigate(R.id.fragment_pass);
    }

    @Override
    public void onError(String s) {

    }
}

