package com.cb.witfactory.model;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cb.witfactory.R;
import com.cb.witfactory.data.classModel.AmplifyCognito;
import com.cb.witfactory.data.classModel.Utils;

public interface Callfun {

     void onSuccess(String s);
     void onSuccess(Object o, String s);
     void onError(String s);

    class Device {
        Boolean state;
        String imageDevice;
        String imageWifi;
        String title;
        String location;

        public Device(){}

        public Device(Boolean state, String imageDevice, String imageWifi, String title, String location) {
            this.state = state;
            this.imageDevice = imageDevice;
            this.imageWifi = imageWifi;
            this.title = title;
            this.location = location;
        }

        public Boolean getState() {
            return state;
        }

        public void setState(Boolean state) {
            this.state = state;
        }

        public String getImageDevice() {
            return imageDevice;
        }

        public void setImageDevice(String imageDevice) {
            this.imageDevice = imageDevice;
        }

        public String getImageWifi() {
            return imageWifi;
        }

        public void setImageWifi(String imageWifi) {
            this.imageWifi = imageWifi;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String titler) {
            this.title = title;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    class DialogResetPassword extends DialogFragment implements Callfun {

        AmplifyCognito amplifyCognito = null;

        Button btn_sign_up;
        EditText ed1,ed2,ed3,ed4,ed5,ed6,new_password;
        TextView txt_close;
        private PreferencesHelper preferencesHelper;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);

            preferencesHelper = new PreferencesHelper(getContext());
            amplifyCognito = new AmplifyCognito(getContext());
            amplifyCognito.setListener(DialogResetPassword.this);

            //View v = inflater.inflate(R.layout.persistent_bottom_sheet, container, false);
            View v = inflater.inflate(R.layout.reset_password, container, false);
            btn_sign_up = (Button)v.findViewById(R.id.btn_sign_up);

            ed1 = (EditText)v.findViewById(R.id.custom_long_otp_1);
            ed2 = (EditText)v.findViewById(R.id.custom_long_otp_2);
            ed3 = (EditText)v.findViewById(R.id.custom_long_otp_3);
            ed4 = (EditText)v.findViewById(R.id.custom_long_otp_4);
            ed5 = (EditText)v.findViewById(R.id.custom_long_otp_5);
            ed6 = (EditText)v.findViewById(R.id.custom_long_otp_6);
            new_password = (EditText)v.findViewById(R.id.new_password);
            txt_close = (TextView) v.findViewById(R.id.txt_close);


            ed1.addTextChangedListener(new TextWatcher() {
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
                        ed2.hasFocus();
                        ed2.setFocusableInTouchMode(true);
                        ed2.setFocusable(true);
                        ed2.requestFocus();

                    }
                }
            });

            ed2.addTextChangedListener(new TextWatcher() {
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
                        ed3.hasFocus();
                        ed3.setFocusableInTouchMode(true);
                        ed3.setFocusable(true);
                        ed3.requestFocus();
                    }
                }
            });

            ed3.addTextChangedListener(new TextWatcher() {
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
                        ed4.hasFocus();
                        ed4.setFocusableInTouchMode(true);
                        ed4.setFocusable(true);
                        ed4.requestFocus();
                    }
                }
            });

            ed4.addTextChangedListener(new TextWatcher() {
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
                        ed5.hasFocus();
                        ed5.setFocusableInTouchMode(true);
                        ed5.setFocusable(true);
                        ed5.requestFocus();
                    }
                }
            });

            ed5.addTextChangedListener(new TextWatcher() {
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
                        ed6.hasFocus();
                        ed6.setFocusableInTouchMode(true);
                        ed6.setFocusable(true);
                        ed6.requestFocus();
                    }
                }
            });

            ed5.addTextChangedListener(new TextWatcher() {
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
                        ed6.hasFocus();
                        ed6.setFocusableInTouchMode(true);
                        ed6.setFocusable(true);
                        ed6.requestFocus();
                    }
                }
            });

            ed6.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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



            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            getDialog().getWindow().setLayout(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getDialog().setCancelable(false);

            txt_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDialog().dismiss();
                }
            });

            btn_sign_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String password = new_password.getText().toString();
                    String otp1 = ed1.getText().toString();
                    String otp2 = ed2.getText().toString();
                    String otp3 = ed3.getText().toString();
                    String otp4 = ed4.getText().toString();
                    String otp5 = ed5.getText().toString();
                    String otp6 = ed6.getText().toString();
                    String otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;


                    String userEmail = PreferencesHelper.getFirstName("email", "");

                    amplifyCognito.confirmResetPassword(userEmail,password, otp);

                }
            });



            return v;
        }

        @Override
        public void onStart() {
            super.onStart();
            getDialog().getWindow()
                    .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        }

        public void validateData() {
            String password =  new_password.getText().toString();
            String otp1 =  ed1.getText().toString();
            String otp2 = ed2.getText().toString();
            String otp3 = ed3.getText().toString();
            String otp4 = ed4.getText().toString();
            String otp5 = ed5.getText().toString();
            String otp6 = ed6.getText().toString();

            if(!Utils.isValidPassword(password)){
                Toast.makeText(getContext(), getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
            }
            if (!otp1.isEmpty() && !otp2.isEmpty() && !otp3.isEmpty() && !otp4.isEmpty() && !otp5.isEmpty() && !otp6.isEmpty() && !password.isEmpty()) {
                btn_sign_up.setClickable(true);
                btn_sign_up.setEnabled(true);
                btn_sign_up.setBackgroundResource(R.drawable.btn_rounded_bacgraund);
                btn_sign_up.setTextColor(Color.WHITE);


            } else {

                btn_sign_up.setBackgroundResource(R.drawable.ic_btn_inactivo);
                btn_sign_up.setTextColor(Color.BLACK);
                btn_sign_up.setClickable(false);
                btn_sign_up.setEnabled(false);

            }
        }

        @Override
        public void onSuccess(String s) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utils.goToLogin(getContext());
                }
            });
        }

        @Override
        public void onSuccess(Object o, String s) {

        }

        @Override
        public void onError(String s) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(getContext(), getString(R.string.text_invalid_code), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    class ValueDevice {
        String imageDevice;
        String title;
        Boolean state;
        String description;

        public ValueDevice(){}

        public ValueDevice(String imageDevice, String title, Boolean state, String description) {
            this.imageDevice = imageDevice;
            this.title = title;
            this.state = state;
            this.description = description;
        }

        public String getImageDevice() {
            return imageDevice;
        }

        public void setImageDevice(String imageDevice) {
            this.imageDevice = imageDevice;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Boolean getState() {
            return state;
        }

        public void setState(Boolean state) {
            this.state = state;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    class VarType {

        public static final int Seis = 6;
    }
}
