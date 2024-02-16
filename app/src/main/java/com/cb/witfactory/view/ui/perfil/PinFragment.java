package com.cb.witfactory.view.ui.perfil;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.cb.witfactory.R;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.cb.witfactory.data.retrofit.user.ObjectResponseUser;
import com.cb.witfactory.data.retrofit.user.UpdateUserResponse;
import com.cb.witfactory.data.retrofit.user.keyUserResponse;
import com.cb.witfactory.databinding.FragmentPinBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.view.ui.home.HomeViewModel;
import com.cb.witfactory.view.ui.home.UserIdHolder;
import com.google.android.material.textfield.TextInputLayout;

public class PinFragment extends Fragment implements Callfun {
    private FragmentPinBinding binding;
    private boolean isEyeIconLongPressed = false;
    private final Handler handler = new Handler();
    private static final int LONG_PRESS_TIMEOUT = 300;
    private EditProfileViewModel mViewModel;
    private HomeViewModel homeViewModel;
    private String serverPin;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pin, container, false);
        binding = FragmentPinBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TextInputLayout textInputLayout1 = root.findViewById(R.id.txtCurrentPinLayout);
        TextView txtError2 = root.findViewById(R.id.txtError2);
        TextView txtError3 = root.findViewById(R.id.txtError3);

        TextInputLayout textInputLayout2 = root.findViewById(R.id.txtNewPinLayout);
        EditText editText2 = root.findViewById(R.id.new_pin);

        TextInputLayout textInputLayout3 = root.findViewById(R.id.txtRepeatPinLayout);
        EditText editText3 = root.findViewById(R.id.repeat_new_pin);
        EditText editText1 = root.findViewById(R.id.txtCurrentPin);
        TextView txtError = root.findViewById(R.id.txtError);
        setupEyeIconLogic(textInputLayout1, editText1);
        setupEyeIconLogic(textInputLayout2, editText2);
        setupEyeIconLogic(textInputLayout3, editText3);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.setListener(this);
        String userId = UserIdHolder.getInstance().getUserId();
        homeViewModel.getDataUSer(userId);

        mViewModel = new ViewModelProvider(this).get(EditProfileViewModel.class);
        AppCompatButton btnSave = root.findViewById(R.id.btnSave);
        binding.btnSave.setOnClickListener(view1 -> updateUserProfile());
        btnSave.setEnabled(false);
        btnSave.setBackgroundResource(R.drawable.ic_btn_inactivo);
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String enteredPin = editable.toString();
                // Comparar con el pin actual del usuario
                if (enteredPin.equals(serverPin)) {
                    txtError.setText("");
                    editText2.setEnabled(true);
                    editText3.setEnabled(true);
                    int colorStroke = ContextCompat.getColor(requireContext(), R.color.dark_greenish_blue);
                    textInputLayout1.setBoxStrokeColor(colorStroke);
                    int colorHint = ContextCompat.getColor(requireContext(), R.color.text_login);
                    textInputLayout1.setDefaultHintTextColor(ColorStateList.valueOf(colorHint));
                } else {
                    txtError.setText(getString(R.string.does_not_match_current_pin));
                    editText2.setEnabled(false);
                    editText3.setEnabled(false);
                    // Cambia el boxStrokeColor
                    int colorStroke = ContextCompat.getColor(requireContext(), R.color.red);
                    textInputLayout1.setBoxStrokeColor(colorStroke);
                    int colorHint = ContextCompat.getColor(requireContext(), R.color.red);
                    textInputLayout1.setDefaultHintTextColor(ColorStateList.valueOf(colorHint));
                }
                boolean isError1Empty = txtError != null && !txtError.getText().toString().isEmpty();
                boolean isError2Empty = txtError2 != null && !txtError2.getText().toString().isEmpty();
                boolean isError3Empty = txtError3 != null && !txtError3.getText().toString().isEmpty();

                btnSave.setEnabled(!isError1Empty && !isError2Empty && !isError3Empty);
                if (btnSave.isEnabled()) {
                    btnSave.setBackgroundResource(R.drawable.ic_btn_activo);
                } else {
                    btnSave.setBackgroundResource(R.drawable.ic_btn_inactivo);
                }
            }
        });

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String enteredPin = editable.toString();
                // Comparar con el pin actual del usuario
                if (enteredPin.equals(editText3.getText().toString())) {
                    txtError2.setText("");
                    txtError3.setText("");
                    int colorStroke = ContextCompat.getColor(requireContext(), R.color.dark_greenish_blue);
                    textInputLayout2.setBoxStrokeColor(colorStroke);
                    textInputLayout3.setBoxStrokeColor(colorStroke);
                    int colorHint = ContextCompat.getColor(requireContext(), R.color.text_login);
                    textInputLayout2.setDefaultHintTextColor(ColorStateList.valueOf(colorHint));
                    textInputLayout3.setDefaultHintTextColor(ColorStateList.valueOf(colorHint));
                } else {
                    txtError2.setText(getString(R.string.does_not_match_current_pin));
                    int colorStroke = ContextCompat.getColor(requireContext(), R.color.red);
                    textInputLayout2.setBoxStrokeColor(colorStroke);
                    int colorHint = ContextCompat.getColor(requireContext(), R.color.red);
                    textInputLayout2.setDefaultHintTextColor(ColorStateList.valueOf(colorHint));
                }
                boolean isError1Empty = txtError != null && !txtError.getText().toString().isEmpty();
                boolean isError2Empty = txtError2 != null && !txtError2.getText().toString().isEmpty();
                boolean isError3Empty = txtError3 != null && !txtError3.getText().toString().isEmpty();

                btnSave.setEnabled(!isError1Empty && !isError2Empty && !isError3Empty);
                if (btnSave.isEnabled()) {
                    btnSave.setBackgroundResource(R.drawable.ic_btn_activo);
                } else {
                    btnSave.setBackgroundResource(R.drawable.ic_btn_inactivo);
                }
            }
        });

        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String enteredPin = editable.toString();
                // Comparar con el pin actual del usuario
                if (enteredPin.equals(editText2.getText().toString())) {
                    txtError3.setText("");
                    txtError2.setText("");
                    int colorStroke = ContextCompat.getColor(requireContext(), R.color.dark_greenish_blue);
                    textInputLayout2.setBoxStrokeColor(colorStroke);
                    textInputLayout3.setBoxStrokeColor(colorStroke);
                    int colorHint = ContextCompat.getColor(requireContext(), R.color.text_login);
                    textInputLayout3.setDefaultHintTextColor(ColorStateList.valueOf(colorHint));
                    textInputLayout2.setDefaultHintTextColor(ColorStateList.valueOf(colorHint));
                } else {
                    txtError3.setText(getString(R.string.does_not_match_current_pin));
                    int colorStroke = ContextCompat.getColor(requireContext(), R.color.red);
                    textInputLayout3.setBoxStrokeColor(colorStroke);
                    int colorHint = ContextCompat.getColor(requireContext(), R.color.red);
                    textInputLayout3.setDefaultHintTextColor(ColorStateList.valueOf(colorHint));
                }
                boolean isError1Empty = txtError != null && !txtError.getText().toString().isEmpty();
                boolean isError2Empty = txtError2 != null && !txtError2.getText().toString().isEmpty();
                boolean isError3Empty = txtError3 != null && !txtError3.getText().toString().isEmpty();

                btnSave.setEnabled(!isError1Empty && !isError2Empty && !isError3Empty);
                if (btnSave.isEnabled()) {
                    btnSave.setBackgroundResource(R.drawable.ic_btn_activo);
                } else {
                    btnSave.setBackgroundResource(R.drawable.ic_btn_inactivo);
                }
            }
        });

        view.findViewById(R.id.btnSave).setOnClickListener(v -> {
            String newPin = editText2.getText().toString();
            String repeatPin = editText3.getText().toString();

            if (newPin.equals(repeatPin)) {
                txtError.setText("");
            } else {
                txtError.setText(getString(R.string.error_pins_do_not_match));
            }
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
        updateUserRequest.setPin(binding.newPin.getText().toString());
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

    @Override
    public void onError(String s) {}
}