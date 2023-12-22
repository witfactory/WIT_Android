package com.cb.witfactory.ui.perfil;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.cb.witfactory.data.retrofit.user.UpdateUserResponse;
import com.cb.witfactory.data.retrofit.user.keyUserResponse;
import com.cb.witfactory.databinding.FragmentEditProfileBinding;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class EditProfileFragment extends Fragment {

    private FragmentEditProfileBinding binding;
    private EditProfileViewModel mViewModel;

    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SwitchMaterial switchMaterial = root.findViewById(R.id.control_parental);
        Button btnPing = root.findViewById(R.id.btn_ping);

        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Cambia la visibilidad del botón según el estado del SwitchMaterial
                btnPing.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });

        btnPing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abrir el nuevo Fragmento (PingFragment)
                openPingFragment();
            }
        });
        mViewModel = new ViewModelProvider(this).get(EditProfileViewModel.class);

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
            }
        });
        binding.lblChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PasswordFragment.class);
                startActivity(intent);
            }
        });
        binding.lblChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_edit, new PasswordFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

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
        updateUserRequest.setLast_name(binding.txtEmail.getText().toString());
        updateUserRequest.setTelephone(binding.txtCellPhone.getText().toString());
        updateUserRequest.setZip_code(binding.txtZipCode.getText().toString());
        updateUserRequest.setCountry(binding.txtCountry.getText().toString());
        updateUserRequest.setSuite("test2");
        updateUserRequest.setAppos("mod");

        mViewModel.updateUser(updateUserRequest);
    }

    private void openPingFragment() {
        // Crear una instancia del nuevo fragmento
        PingFragment pingFragment = PingFragment.newInstance();

        // Obtener el FragmentManager
        FragmentManager fragmentManager = getParentFragmentManager();

        // Iniciar la transacción del fragmento
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Reemplazar el contenido actual con el nuevo fragmento
        fragmentTransaction.replace(R.id.container_edit, pingFragment);

        // Agregar la transacción al back stack
        fragmentTransaction.addToBackStack(null);

        // Confirmar la transacción
        fragmentTransaction.commit();
    }
}

