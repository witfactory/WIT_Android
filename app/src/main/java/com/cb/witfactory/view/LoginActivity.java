package com.cb.witfactory.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
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
import com.cb.witfactory.ui.register.Register3Fragment;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity  implements Callfun {

    private ActivityLoginBinding binding;
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    Context context;
    Resources resources;
    String locale = "";
    AmplifyCognito amplifyCognito = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        locale = getPersistedLocale(getApplicationContext());
        amplifyCognito = new AmplifyCognito(getApplicationContext());
        amplifyCognito.setListener(LoginActivity.this);


        binding.btnEs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spanish();
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
                var mail = binding.txtNameUser.getText().toString();
                var password = binding.txtPin.getText().toString();

                if (mail.isEmpty() || password.isEmpty() || !validarEmail(mail)) {
                    //String cadena = getString(R.string.txt_invalide_email_password);
                    Toast.makeText(getApplicationContext(), "Incorrect email or password", Toast.LENGTH_SHORT).show();
                } else {

                    amplifyCognito.signIn(mail,password);
                  //  Intent intent = new Intent(getApplicationContext(), WitMenu.class);
                   // startActivity(intent);
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
        Log.v("succes", s.toString());
        Utils.goToHome(getApplicationContext());
    }

    @Override
    public void onError(String s) {
        Log.v("error", s.toString());
        //amplifyCognito.resendCode();
    }
}