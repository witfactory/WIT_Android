package com.cb.witfactory.ui.register;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.cb.witfactory.R;
import com.cb.witfactory.databinding.Register2FragmentBinding;
import com.cb.witfactory.model.Country;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.model.State;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Register2Fragment extends Fragment {

    private Register2ViewModel mViewModel;
    private Register2FragmentBinding binding;


    List<String> listCountry = new ArrayList<String>();
    List<Country> listObjCountry = new ArrayList<Country>();
    List<String> listCity = new ArrayList<String>();
    List<State> listObjState = new ArrayList<State>();



    String txtFirstName ="", txtCountry="", txtCity="", txtAddress="", txtZipCode="", txtCellPhone="";
    private PreferencesHelper preferencesHelper;

    public static Register2Fragment newInstance() {
        return new Register2Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = Register2FragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        preferencesHelper = new PreferencesHelper(getContext());
        listCountry();
        listCity();
        loadPreference();

        binding.txtCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.txtLayoutCity.setHint("");
            }
        });

        binding.txtCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && binding.txtCity.getText().toString().isEmpty()) {
                    binding.txtLayoutCity.setHint(getString(R.string.ciudad));
                }
            }
        });

        binding.txtCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), listObjCountry.get(i).getName(), Toast.LENGTH_SHORT).show();
                binding.txtCity.setText("");
                listCity.clear();
                binding.txtLayoutCountry.setHint("");
                Integer idCountry = listObjCountry.get(i).getId();
                for (int j = 0; j < listObjState.size(); j++) {
                    Integer IdCountryState = listObjState.get(j).getIdCountry();
                    if (idCountry.equals(IdCountryState)) {
                        listCity.add(listObjState.get(j).getName());
                    }
                }

                ArrayAdapter aCity = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listCity);
                aCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.txtCity.setAdapter(aCity);
                aCity.notifyDataSetChanged();

            }
        });

        binding.txtCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && binding.txtCity.getText().toString().isEmpty()) {
                    binding.txtLayoutCountry.setHint(getString(R.string.ciudad));
                }
            }
        });

        ArrayAdapter aCountry = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listCountry);
        aCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.txtCountry.setAdapter(aCountry);


        binding.txtFirstName.addTextChangedListener(new TextWatcher() {
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
        binding.txtCountry.addTextChangedListener(new TextWatcher() {
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
        binding.txtCity.addTextChangedListener(new TextWatcher() {
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
        binding.txtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 3) {
                    validateData();

                }
            }
        });
        binding.txtZipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 3) {
                    validateData();

                }
            }
        });
        binding.txtCellPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 9) {
                    validateData();

                }
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!txtFirstName.isEmpty() && !txtCountry.isEmpty() && !txtCity.isEmpty() && !txtAddress.isEmpty() && !txtZipCode.isEmpty() && !txtCellPhone.isEmpty()) {

                    PreferencesHelper.setFirstName("first_name", txtFirstName.toString());
                    PreferencesHelper.setCountry("country", txtCountry.toString());
                    PreferencesHelper.setCity("city", txtCity.toString());
                    PreferencesHelper.setAddress("address", txtAddress.toString());
                    PreferencesHelper.setZipCode("zip_code", txtZipCode.toString());
                    PreferencesHelper.setTelephone("telephone", txtCellPhone.toString());

                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_registro);
                    navController.navigateUp();
                    navController.navigate(R.id.nenu_register3);

                } else {
                    Toast.makeText(getContext(), getString(R.string.all_fields_are_required), Toast.LENGTH_SHORT).show();
                }


            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_registro);
                navController.navigateUp();
                navController.navigate(R.id.nenu_register);
            }
        });

        binding.btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_registro);
                navController.navigateUp();
                navController.navigate(R.id.nenu_register);
            }
        });

        return root;
    }

    private void listCountry() {
        Reader reader = new InputStreamReader(getResources().openRawResource(R.raw.countries));
        JsonElement jelement = new Gson().fromJson(reader, JsonElement.class);


        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray jarray = jobject.getAsJsonArray("countries");
        for (int i = 0; i < jarray.size(); i++) {
            JsonObject jsonObject = jarray.get(i).getAsJsonObject();
            Integer id = Integer.parseInt(jsonObject.get("id").toString());
            System.out.println(id);
            String name = jsonObject.get("name").toString().replace('"', ' ').trim();
            System.out.println(name);
            listCountry.add(name);

            Country country = new Country(id, name);
            listObjCountry.add(country);


        }
    }

    private void listCity() {
        Reader reader = new InputStreamReader(getResources().openRawResource(R.raw.states));
        JsonElement jelement = new Gson().fromJson(reader, JsonElement.class);


        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray jarray = jobject.getAsJsonArray("states");
        for (int i = 0; i < jarray.size(); i++) {
            JsonObject jsonObject = jarray.get(i).getAsJsonObject();
            Integer id = Integer.parseInt(jsonObject.get("id").toString());
            Integer id_country = Integer.parseInt(jsonObject.get("id_country").toString());
            System.out.println(id);
            String name = jsonObject.get("name").toString().replace('"', ' ').trim();
            System.out.println(name);
            // listCity.add(name);

            State state = new State(id, id_country, name);
            listObjState.add(state);


        }
    }


    public void validateData() {
        txtFirstName = binding.txtFirstName.getText().toString();
        txtCountry = binding.txtCountry.getText().toString();
        txtCity = binding.txtCity.getText().toString();
        txtAddress = binding.txtAddress.getText().toString();
        txtZipCode = binding.txtZipCode.getText().toString();
        txtCellPhone = binding.txtCellPhone.getText().toString();

        if (!txtFirstName.isEmpty() && !txtCountry.isEmpty() && !txtCity.isEmpty() && !txtAddress.isEmpty() && !txtZipCode.isEmpty() && !txtCellPhone.isEmpty()) {
           binding.btnNext.setClickable(true);
            binding.btnNext.setBackgroundResource(R.drawable.ic_btn_activo);
        } else {
            binding.btnNext.setBackgroundResource(R.drawable.ic_btn_inactivo);
        }
    }

    public void loadPreference(){

        String first_name = PreferencesHelper.getFirstName("first_name", "");
        String country = PreferencesHelper.getEmail("country", "");
        String city = PreferencesHelper.getUser("city", "");
        String address = PreferencesHelper.getUser("address", "");
        String zip_code = PreferencesHelper.getUser("zip_code", "");
        String telephone = PreferencesHelper.getUser("telephone", "");


        binding.txtFirstName.setText(first_name);
        binding.txtCountry.setText(country);
        binding.txtCity.setText(city);
        binding.txtAddress.setText(address);
        binding.txtZipCode.setText(zip_code);
        binding.txtCellPhone.setText(telephone);
        validateData();


    }

}