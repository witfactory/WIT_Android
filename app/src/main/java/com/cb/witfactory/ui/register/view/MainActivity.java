package com.cb.witfactory.ui.register.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import com.cb.witfactory.R;
import com.cb.witfactory.data.classModel.AmplifyCognito;
import com.cb.witfactory.data.classModel.Utils;
import com.cb.witfactory.databinding.ActivityMainBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.LocaleHelper;

public class MainActivity extends Activity implements Callfun {

    private ActivityMainBinding binding;
    private boolean isFirstAnimation = false;
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    Context context;
    Resources resources;
    String locale = "";
    AmplifyCognito amplifyCognito = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        darkBlackEnable();

        locale = getPersistedLocale(getApplicationContext());

        //amplify

        amplifyCognito = new AmplifyCognito(getApplicationContext());
        amplifyCognito.setListener(MainActivity.this);

        //lenguage
        if (locale.equals(null) || locale.equals("")) {
            locale = "en";
        }

        context = LocaleHelper.setLocale(MainActivity.this, locale);
        resources = context.getResources();
        Animation hold = AnimationUtils.loadAnimation(this, R.anim.hold);
        final Animation translateScale = AnimationUtils.loadAnimation(this, R.anim.translate_scale);

        translateScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!isFirstAnimation) {
                    binding.imgLogo.clearAnimation();


                }

                isFirstAnimation = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        hold.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.imgLogo.clearAnimation();
                binding.imgLogo.startAnimation(translateScale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        binding.imgLogo.startAnimation(hold);


        validateInternet();


    }

    public static String getPersistedLocale(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("language", "");
    }

    public void darkBlackEnable() {
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                /* si esta activo el modo oscuro lo desactiva */
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);
                break;
           /* case Configuration.UI_MODE_NIGHT_NO:
                /* si esta desactivado el modo oscuro lo activa */
               /* AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
                break;*/
        }
    }

    @Override
    public void onSuccess(String s) {
        if (s.equals("true")) {
            Utils.goToHome(getApplicationContext());
        } else {
            Utils.goToLoginRegister(getApplicationContext());
        }
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        finish();

    }

    @Override
    public void onError(String s) {

        String error = getString(R.string.something_went);
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    public void validateInternet(){
        if(!Utils.internetstaus(getApplicationContext())){

           Utils.aletSinInternet(this);
        }else{
            amplifyCognito.validarAuth();
        }
    }
}