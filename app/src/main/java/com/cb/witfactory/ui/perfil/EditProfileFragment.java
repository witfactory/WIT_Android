package com.cb.witfactory.ui.perfil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cb.witfactory.data.retrofit.user.UpdateUserResponse;
import com.cb.witfactory.data.retrofit.user.keyUserResponse;
import com.cb.witfactory.databinding.FragmentEditProfileBinding;
import com.cb.witfactory.model.PreferencesHelper;

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

        mViewModel = new ViewModelProvider(this).get(EditProfileViewModel.class);

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
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
}

