package com.cb.witfactory.view.ui.perfil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.cb.witfactory.R;
import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;
import com.cb.witfactory.data.retrofit.user.UpdateUserResponse;
import com.cb.witfactory.data.retrofit.user.keyUserResponse;
import com.cb.witfactory.databinding.FragmentPasswordBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.view.ui.home.HomeViewModel;
import com.cb.witfactory.view.ui.home.UserIdHolder;
import com.cb.witfactory.view.ResetPassWordActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PasswordFragment extends Fragment implements Callfun {
    private @NonNull FragmentPasswordBinding binding;
    private boolean isEyeIconLongPressed = false;
    private final Handler handler = new Handler();
    private static final int LONG_PRESS_TIMEOUT = 300;
    private EditProfileViewModel mViewModel;
    private HomeViewModel homeViewModel;
    private String serverPin;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);
        binding = FragmentPasswordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TextInputLayout textInputLayout1 = view.findViewById(R.id.txtCurrentPassLayout);
        TextView txtError2 = view.findViewById(R.id.txtError2);
        TextView txtError3 = view.findViewById(R.id.txtError3);

        TextInputLayout textInputLayout2 = view.findViewById(R.id.txtNewPassLayout);
        EditText editText2 = view.findViewById(R.id.new_pass);

        TextInputLayout textInputLayout3 = view.findViewById(R.id.txtRepeatPassLayout);
        EditText editText3 = view.findViewById(R.id.repeat_new_pass);
        TextInputEditText editText1 = view.findViewById(R.id.txtCurrentPass);
        TextView txtError = view.findViewById(R.id.txtError);
        setupEyeIconLogic(textInputLayout1, editText1);
        setupEyeIconLogic(textInputLayout2, editText2);
        setupEyeIconLogic(textInputLayout3, editText3);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.setListener(this);
        String userId = UserIdHolder.getInstance().getUserId();
        homeViewModel.getDataUSer(userId);

        mViewModel = new ViewModelProvider(this).get(EditProfileViewModel.class);
        binding.btnSave.setOnClickListener(view1 -> updateUserProfile());

        view.findViewById(R.id.btnSave).setOnClickListener(v -> {
            String newPin = editText2.getText().toString();
            String repeatPin = editText3.getText().toString();

            if (newPin.equals(repeatPin)) {
                txtError.setText("");
            } else {
                txtError.setText(getString(R.string.error_pins_do_not_match));
            }
        });

        binding.lblChangePass.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), ResetPassWordActivity.class);
            startActivity(intent);
        });

        view.findViewById(R.id.btnSave).setOnClickListener(v -> {
            String newPin = editText2.getText().toString();
            String repeatPin = editText3.getText().toString();

            if (newPin.equals(repeatPin)) {
                txtError2.setText("");
                txtError3.setText("");
            } else {
                txtError3.setText(getString(R.string.error_pins_do_not_match));
            }
        });
        return root;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupEyeIconLogic(final TextInputLayout textInputLayout, final EditText editText) {
        textInputLayout.setEndIconDrawable(R.drawable.ic_eye);

        editText.setOnLongClickListener(v -> {
            isEyeIconLongPressed = true;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isEyeIconLongPressed) {
                        editText.setInputType(isEyeIconLongPressed ? 1 : 129);
                        isEyeIconLongPressed = false;
                    }
                }
            }, LONG_PRESS_TIMEOUT);
            return true;
        });

        editText.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                isEyeIconLongPressed = false;
                handler.removeCallbacksAndMessages(null);
                editText.setInputType(129);
            }
            return false;
        });
    }

    private void updateUserProfile() {
        UpdateUserResponse updateUserRequest = new UpdateUserResponse();
        String user_aws = PreferencesHelper.getUserAws("user_aws","").toString();
        keyUserResponse key = new keyUserResponse(user_aws);
        key.setId(user_aws);
        updateUserRequest.setKey(key);
        updateUserRequest.setPin(binding.newPass.getText().toString());
        mViewModel.updateUser(updateUserRequest);
        navigateToEditProfileDetailsFragment();
    }

    private void navigateToEditProfileDetailsFragment() {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_login_menu);
        navController.navigateUp();
        navController.navigate(R.id.fragment_edit_profile_detail);
    }

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onSuccess(Object o, String s) {
        if(s.equals("getuser")){
            ObjectResponseUser bodyResponseUser = (ObjectResponseUser) o;
            serverPin = bodyResponseUser.getBody().get(0).getPin().toString();
        }
    }


    public void onError(String s) {

    }

}
