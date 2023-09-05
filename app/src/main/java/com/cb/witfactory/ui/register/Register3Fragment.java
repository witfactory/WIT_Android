package com.cb.witfactory.ui.register;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;

import static org.chromium.base.ContextUtils.getApplicationContext;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.cb.witfactory.R;
import com.cb.witfactory.data.classModel.AmplifyCognito;
import com.cb.witfactory.data.classModel.Utils;
import com.cb.witfactory.databinding.Register3FragmentBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.EnumVaribles;
import com.cb.witfactory.model.PreferencesHelper;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Register3Fragment extends Fragment implements Callfun {

    private Register3ViewModel mViewModel;
    private Register3FragmentBinding binding;

    AmplifyCognito amplifyCognito = null;
    Boolean resulRegiter = false;

    Animation animation = null;
    String txt_pin;
    Boolean controlParental = false, terminosCondiciones = false, politicaPrivacidad = false;
    private PreferencesHelper preferencesHelper;


    public static Register3Fragment newInstance() {
        return new Register3Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = Register3FragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        preferencesHelper = new PreferencesHelper(getContext());
        animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.bottom_in);

        amplifyCognito = new AmplifyCognito(getContext());
        amplifyCognito.setListener(Register3Fragment.this);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_registro);
                navController.navigateUp();
                navController.navigate(R.id.nenu_register2);

            }
        });


        butttonStyle();


        // data form
        binding.controlParental.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean flag) {
                if (flag) {

                    binding.txtPin.setFocusable(true);
                    controlParental = true;
                } else {
                    binding.txtPin.setText("");
                    binding.txtPin.setFocusable(false);
                    controlParental = false;
                }
                validateDataForm();
            }
        });

        binding.checkTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean flag) {

                terminosCondiciones = flag;
                validateDataForm();
            }
        });

        binding.checkPolitict.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean flag) {

                politicaPrivacidad = flag;
                validateDataForm();
            }
        });

        binding.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (controlParental) {
                    if (txt_pin.isEmpty()) {
                        return;
                    }

                }
                if (terminosCondiciones && politicaPrivacidad) {

                    String device_id = Settings.Secure.getString(getActivity().getContentResolver(),
                            Settings.Secure.ANDROID_ID);

                    String email = PreferencesHelper.getEmail("email", "");
                    String first_name = PreferencesHelper.getFirstName("first_name", "");
                    String user = PreferencesHelper.getUser("user", "");
                    String last_name = PreferencesHelper.getLastName("last_name", "demos");
                    String country = PreferencesHelper.getCountry("country", "");
                    String city = PreferencesHelper.getCity("city", "");
                    String zip_code = PreferencesHelper.getZipCode("zip_code", "");
                    String address = PreferencesHelper.getAddress("address", "");
                    String account_type = PreferencesHelper.getAccountType("account_type", "P");
                    String telephone = PreferencesHelper.getTelephone("telephone", "");
                    String password = PreferencesHelper.getPassword("password", "");
                    Log.v("", password);
                    amplifyCognito.sinUp(email, user, last_name, country, city, zip_code, address, account_type, telephone, password, device_id);
                }
            }
        });


        // data otp

        binding.includeSheep.customLongOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    validateData();
                    binding.includeSheep.customLongOtp2.hasFocus();
                    binding.includeSheep.customLongOtp2.setFocusableInTouchMode(true);
                    binding.includeSheep.customLongOtp2.setFocusable(true);
                    binding.includeSheep.customLongOtp2.requestFocus();

                }
            }
        });

        binding.includeSheep.customLongOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    validateData();
                    binding.includeSheep.customLongOtp3.hasFocus();
                    binding.includeSheep.customLongOtp3.setFocusableInTouchMode(true);
                    binding.includeSheep.customLongOtp3.setFocusable(true);
                    binding.includeSheep.customLongOtp3.requestFocus();

                }
            }
        });

        binding.includeSheep.customLongOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    validateData();
                    binding.includeSheep.customLongOtp4.hasFocus();
                    binding.includeSheep.customLongOtp4.setFocusableInTouchMode(true);
                    binding.includeSheep.customLongOtp4.setFocusable(true);
                    binding.includeSheep.customLongOtp4.requestFocus();

                }
            }
        });

        binding.includeSheep.customLongOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    validateData();
                    binding.includeSheep.customLongOtp5.hasFocus();
                    binding.includeSheep.customLongOtp5.setFocusableInTouchMode(true);
                    binding.includeSheep.customLongOtp5.setFocusable(true);
                    binding.includeSheep.customLongOtp5.requestFocus();

                }
            }
        });

        binding.includeSheep.customLongOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    validateData();
                    binding.includeSheep.customLongOtp6.hasFocus();
                    binding.includeSheep.customLongOtp6.setFocusableInTouchMode(true);
                    binding.includeSheep.customLongOtp6.setFocusable(true);
                    binding.includeSheep.customLongOtp6.requestFocus();

                }
            }
        });

        binding.includeSheep.customLongOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    validateData();
                }
            }
        });

        binding.includeSheep.customLongOtp6.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == KeyEvent.ACTION_DOWN || i == EditorInfo.IME_ACTION_GO) {
                    validateData();
                    View view = getActivity().getCurrentFocus();
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    return true;

                }
                return false;
            }
        });

        binding.includeSheep.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String otp1 = binding.includeSheep.customLongOtp1.getText().toString();
                String otp2 = binding.includeSheep.customLongOtp2.getText().toString();
                String otp3 = binding.includeSheep.customLongOtp3.getText().toString();
                String otp4 = binding.includeSheep.customLongOtp4.getText().toString();
                String otp5 = binding.includeSheep.customLongOtp5.getText().toString();
                String otp6 = binding.includeSheep.customLongOtp6.getText().toString();
                String otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;

                String userEmail = PreferencesHelper.getFirstName("user", "");

                amplifyCognito.confirmSigUp(otp, userEmail);


            }
        });


        binding.includeSheep.txtResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = PreferencesHelper.getFirstName("user", "");
                resulRegiter = amplifyCognito.resendCodeEmail(userEmail);

            }
        });
        dataSelect();


        return root;

    }


    private void dataSelect() {


        binding.includeSheep.customLongOtp1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    validateData();
            }
        });

        binding.includeSheep.customLongOtp2.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    validateData();
            }
        });

        binding.includeSheep.customLongOtp3.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    validateData();
            }
        });

        binding.includeSheep.customLongOtp4.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    validateData();
            }
        });

        binding.includeSheep.customLongOtp5.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    validateData();
            }
        });

        binding.includeSheep.customLongOtp6.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    validateData();
            }
        });


    }

    public void validateData() {
        String otp1 = binding.includeSheep.customLongOtp1.getText().toString();
        String otp2 = binding.includeSheep.customLongOtp2.getText().toString();
        String otp3 = binding.includeSheep.customLongOtp3.getText().toString();
        String otp4 = binding.includeSheep.customLongOtp4.getText().toString();
        String otp5 = binding.includeSheep.customLongOtp5.getText().toString();
        String otp6 = binding.includeSheep.customLongOtp6.getText().toString();

        if (!otp1.isEmpty() && !otp2.isEmpty() && !otp3.isEmpty() && !otp4.isEmpty() && !otp5.isEmpty() && !otp6.isEmpty()) {
            binding.includeSheep.btnSignUp.setClickable(true);
            binding.includeSheep.btnSignUp.setEnabled(true);
            binding.includeSheep.btnSignUp.setBackgroundResource(R.drawable.btn_rounded_bacgraund);
            binding.includeSheep.btnSignUp.setTextColor(Color.WHITE);


        } else {

            binding.includeSheep.btnSignUp.setBackgroundResource(R.drawable.btn_rounded_gray);
            binding.includeSheep.btnSignUp.setTextColor(Color.BLACK);
            binding.includeSheep.btnSignUp.setClickable(false);
            binding.includeSheep.btnSignUp.setEnabled(false);

        }
    }


    private void butttonStyle() {
        binding.includeSheep.btnSignUp.setBackgroundResource(R.drawable.btn_rounded_gray);
        binding.includeSheep.btnSignUp.setTextColor(Color.BLACK);
        binding.includeSheep.btnSignUp.setClickable(false);
        binding.includeSheep.btnSignUp.setEnabled(false);
    }

    @Override
    public void onSuccess(String s) {
        try {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (s.equals(EnumVaribles.sinUp.toString())) {
                        modalOtp();
                    }

                    if (s.equals(EnumVaribles.confirmCode.toString())) {
                        Toast.makeText(getContext(), "Valida tu correo", Toast.LENGTH_SHORT).show();
                    }
                    //    Toast.makeText(getContext(), s.toString(), Toast.LENGTH_LONG).show();


                    if (s.equals("resendCodeEmail")) {
                        Toast.makeText(getContext(), "resendCodeEmail", Toast.LENGTH_SHORT).show();
                    }

                    if (s.equals(EnumVaribles.confirmSigUp.toString())) {
                        Toast.makeText(getContext(), "s+" + s, Toast.LENGTH_SHORT).show();
                    }
                    if (s.equals(EnumVaribles.confirmCode.toString())) {
                        Utils.goToHome(getApplicationContext());
                    }


                }
            });


        } catch (Exception e) {

            Log.v("error callback", e.getMessage().toString());
        }
    }

    @Override
    public void onError(String s) {

        ///' modalOtp();
        //  Toast.makeText(getContext(), "resendCodeEmail", Toast.LENGTH_SHORT).show();
        try {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (s.equals(EnumVaribles.sinUp.toString())) {
                        alerta("Correo registrado", "Valida los datos");
                    }

                    if (s.equals(EnumVaribles.confirmCode.toString())) {
                        alerta("Código erroneo", "Valida los datos");
                    }

                    if (s.equals("resendCodeEmail")) {
                        alerta("Código erroneo", "Valida los datos");
                    }
                }
            });

        } catch (Exception e) {
            Log.v("error callback", e.getMessage().toString());
            alerta("Correo ya registrado o invalido", "Valida los datos");
        }
    }


    public void alerta(String titulo, String mensaje) {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText(titulo)
                .setContentText(mensaje)
                .setConfirmText("OK")
                .showCancelButton(true)
                .show();
    }

    private void modalOtp() {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_in);
                binding.lyoutSheep.setVisibility(View.VISIBLE);
                binding.lyoutSheep.setAnimation(animation);
            }
        });


    }

    public void validateDataForm() {
        txt_pin = binding.txtPin.getText().toString();
        //txtFirstName = binding.txtFirstName.getText().toString();

        if (controlParental) {
            if (txt_pin.isEmpty()) {
                binding.btnFinish.setBackgroundResource(R.drawable.ic_btn_inactivo);
                Toast.makeText(getContext(), getString(R.string.all_fields_are_required), Toast.LENGTH_SHORT).show();
                return;
            }

        }
        if (terminosCondiciones && politicaPrivacidad) {
            binding.btnFinish.setClickable(true);
            binding.btnFinish.setBackgroundResource(R.drawable.ic_btn_activo);
        }

    }
}