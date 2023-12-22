package com.cb.witfactory.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cb.witfactory.R;
import com.cb.witfactory.data.classModel.AmplifyCognito;
import com.cb.witfactory.data.classModel.Utils;
import com.cb.witfactory.databinding.ActivityLoginBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.LocaleHelper;
import com.cb.witfactory.model.PreferencesHelper;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements Callfun {

    private ActivityLoginBinding binding;
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    Context context;
    Resources resources;
    String locale = "";
    AmplifyCognito amplifyCognito = null;
    String mail = "";
    String password = "";
    private PreferencesHelper preferencesHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        preferencesHelper = new PreferencesHelper(getApplicationContext());

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        locale = getPersistedLocale(getApplicationContext());
        amplifyCognito = new AmplifyCognito(getApplicationContext());
        amplifyCognito.setListener(LoginActivity.this);


        getToken();
        loadData();


        // Obtener los datos enviados a través de putExtra
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String titulo = extras.getString("titulo");
            String detalle = extras.getString("detalle");

            // Utilizar el valor recibido
            if (titulo != null && detalle != null) {

                Log.d("DestinoActivity", "titulo recibido: " + titulo);
                Toast.makeText(getApplicationContext(), titulo + " : " + detalle, Toast.LENGTH_SHORT).show();
            }
        }

        binding.btnEs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spanish();
            }
        });

        binding.txtPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    validardata();

                }
            }
        });

        binding.txtNameUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    validardata();

                }
            }
        });

        binding.btnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                english();
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = binding.txtNameUser.getText().toString();
                password = binding.txtPin.getText().toString();

                if (mail.isEmpty() || password.isEmpty() || !validarEmail(mail)) {
                    //String cadena = getString(R.string.txt_invalide_email_password);
                    Toast.makeText(getApplicationContext(), "Incorrect email or password", Toast.LENGTH_SHORT).show();
                } else {

                    amplifyCognito.signIn(mail, password);
                }

            }
        });


        binding.resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResetPassWordActivity.class);
                startActivity(intent);
            }
        });


        binding.savesession.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean flag) {
                if (flag) {
                    preferencesHelper.setSaveSession("savesession", binding.txtNameUser.getText().toString());

                } else {
                    preferencesHelper.setSaveSession("savesession", "");

                }
            }
        });

    }


    public void spanish() {
        context = LocaleHelper.setLocale(
                getApplicationContext(), "es");
        resources = context.getResources();
        storeLanguageInPref("es");
        startIntent();
    }

    public void english() {
        context = LocaleHelper.setLocale(getApplicationContext(), "en");
        resources = context.getResources();
        storeLanguageInPref("en");
        startIntent();

    }

    public static String getPersistedLocale(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("language", "");

    }

    private void storeLanguageInPref(String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("language", language);
        editor.apply();
    }

    private void startIntent() {
        /*NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_login_menu);
        navController.navigateUp();
        navController.navigate(R.id.menu_home);*/

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    @Override
    public void onSuccess(String s) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.v("succes", s.toString());
                Utils.goToHome(getApplicationContext());
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
                Log.v("error", s.toString());
                Toast.makeText(getApplicationContext(), getString(R.string.invalid_email_or_password).toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void validardata() {
        if (mail.isEmpty() || password.isEmpty() || !validarEmail(mail)) {
            binding.btnSignUp.setBackgroundResource(R.drawable.ic_btn_activo);
        } else {
            binding.btnSignUp.setBackgroundResource(R.drawable.ic_btn_inactivo);
        }
    }

    public void getToken() {

        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
            if (!TextUtils.isEmpty(token)) {
                Log.d("TAG", "retrieve token successful : " + token);
                Toast.makeText(getApplicationContext(), token.toString(), Toast.LENGTH_SHORT).show();
                Log.w("TOKEN", "token : " + token.toString());
            } else {
                Log.w("TAG", "token should not be null...");
            }
        }).addOnFailureListener(e -> {
            //handle e
        }).addOnCanceledListener(() -> {
            //handle cancel
        }).addOnCompleteListener(task -> Log.v("TAG", "This is the token : " + task.getResult()));
    }


    public void loadData() {


        String correo = PreferencesHelper.getSaveSession("savesession", "");

        if (!correo.isEmpty()) {
            binding.savesession.setChecked(true);
            binding.txtNameUser.setText(correo);
        }
    }
}