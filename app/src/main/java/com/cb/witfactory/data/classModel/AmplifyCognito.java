package com.cb.witfactory.data.classModel;

import android.content.Context;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.EnumVaribles;

import org.chromium.base.Log;

public class AmplifyCognito {

    private Context mContext;
    private Boolean authSignUpResult = false;
    private Boolean comfirm = false;
    private static Callfun listener;


    public AmplifyCognito(Context context) {
        this.mContext = context;

    }


    //registro
    public Boolean sinUp() {
        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.custom("custom:first_name"), "Adri ")
                .userAttribute(AuthUserAttributeKey.custom("custom:user"), "tapia2390@gmail.com")
                .userAttribute(AuthUserAttributeKey.custom("custom:last_name"), "Tapias G")
                .userAttribute(AuthUserAttributeKey.custom("custom:country"), "Colombia")
                .userAttribute(AuthUserAttributeKey.custom("custom:city"), "Manizales")
                .userAttribute(AuthUserAttributeKey.custom("custom:zip_code"), "170001")
                .userAttribute(AuthUserAttributeKey.custom("custom:address"), "Cr 14 # 25-28")
                .userAttribute(AuthUserAttributeKey.custom("custom:account_type"), "P")
                .userAttribute(AuthUserAttributeKey.custom("custom:telephone"), "3146381721")
                .userAttribute(AuthUserAttributeKey.custom("custom:user_principal"), "")
                .build();
        Amplify.Auth.signUp(
                "tapia2390@gmail.com",
                "Tapia231290.",
                options,
                result ->
                {
                    authSignUpResult = result.isSignUpComplete();
                    Log.i("AuthQuickstart", result.toString());
                    String sinUp = EnumVaribles.sinUp.toString();
                    listener.onSuccess(sinUp);

                },
                error -> {
                    Log.e("AuthQuickstart", error.toString());
                    String sinUp = EnumVaribles.sinUp.toString();
                    listener.onError(sinUp);
                }
        );

        return authSignUpResult;
    }


    public Boolean resendCodeEmail() {

        Amplify.Auth.resendSignUpCode("tapia2390@gmail.com",
                result ->
                {
                    Log.v("AuthQuickstart", result + "");
                    listener.onError("resendCodeEmail");
                },
                result ->
                {
                    Log.v("AuthQuickstart", result + "");
                    listener.onError("resendCodeEmail");
                });
        return comfirm;
    }

    public Boolean resendCode() {


        AuthUserAttributeKey email = AuthUserAttributeKey.email();

        Amplify.Auth.resendUserAttributeConfirmationCode(AuthUserAttributeKey.email(),
                result ->
                {
                    Log.v("AuthQuickstart", result + "");
                    String sinUp = EnumVaribles.resendCode.toString();
                    listener.onSuccess(sinUp);
                },
                error -> {
                    Log.e("AuthQuickstart", error + "");
                    String sinUp = EnumVaribles.resendCode.toString();
                    listener.onError(sinUp);
                }
        );
        return comfirm;
    }

    public Boolean confirmCode(String code) {
        Amplify.Auth.confirmSignUp(
                "tapia2390@gmail.com",
                code,

                result ->
                {
                    Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                    String sinUp = EnumVaribles.confirmCode.toString();
                    listener.onSuccess(sinUp);
                },
                error -> {
                    Log.e("AuthQuickstart", error.toString());
                    String sinUp = EnumVaribles.confirmCode.toString();
                    listener.onError(sinUp);


                }

        );
        return comfirm;
    }


    //Inicia sesión
    public Boolean signIn() {
        Amplify.Auth.signIn(
                "tapia2390@gmail.com",
                "Tapia231290.",
                result -> {
                    Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                    String sinUp = EnumVaribles.signIn.toString();
                    listener.onSuccess(sinUp);
                },
                error -> {
                    Log.e("AuthQuickstart", error.toString());
                    String sinUp = EnumVaribles.signIn.toString();
                    listener.onError(sinUp);

                }
        );
        return false;
    }


    //https://www.youtube.com/watch?v=B4HjPRA4A8k&list=PLrAF24Xspn3WsVBl4AwmpdMl5g-kHw2Zu

    public void setListener(Callfun listener) {
        this.listener = listener;
    }

}
