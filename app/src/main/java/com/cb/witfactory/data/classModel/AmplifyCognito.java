package com.cb.witfactory.data.classModel;

import android.content.Context;

import com.amplifyframework.auth.AuthSession;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.EnumVaribles;
import com.cb.witfactory.model.PreferencesHelper;

import org.chromium.base.Log;

public class AmplifyCognito {

    private Context mContext;
    private Boolean authSignUpResult = false;
    private Boolean comfirm = false;
    private static Callfun listener;


    private PreferencesHelper preferencesHelper;

    public AmplifyCognito(Context context) {
        this.mContext = context;

    }


    //registro
    public Boolean sinUp(String first_name, String user, String last_name, String country, String city,
                         String zip_code, String address, String account_type, String telephone, String user_principal, String password) {
        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.custom("custom:first_name"), first_name)
                .userAttribute(AuthUserAttributeKey.custom("custom:user"), user)
                .userAttribute(AuthUserAttributeKey.custom("custom:last_name"), last_name)
                .userAttribute(AuthUserAttributeKey.custom("custom:country"), country)
                .userAttribute(AuthUserAttributeKey.custom("custom:city"), city)
                .userAttribute(AuthUserAttributeKey.custom("custom:zip_code"), zip_code)
                .userAttribute(AuthUserAttributeKey.custom("custom:address"), address)
                .userAttribute(AuthUserAttributeKey.custom("custom:account_type"), "P")
                .userAttribute(AuthUserAttributeKey.custom("custom:telephone"), "telephone")
                .userAttribute(AuthUserAttributeKey.custom("custom:user_principal"), "")
                .build();
        Amplify.Auth.signUp(
                user,
                password,
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

    public Boolean resendCodeEmail(String emailUserName) {

        Amplify.Auth.resendSignUpCode(emailUserName,
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

    public Boolean confirmCode(String code, String emailUserName) {
        Amplify.Auth.confirmSignUp(
                emailUserName,
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
    public Boolean signIn(String email, String password) {
        Amplify.Auth.signIn(
                email,
                password,
                result -> {
                    Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                    String token = result.getClass().getName();
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

    public void validarAuth() {
        Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i("AmplifyQuickstart", result.toString());
                    String sinUp = EnumVaribles.signAuth.toString();
                    Boolean estateSession = result.isSignedIn();


                    if(estateSession){

                        String name = Amplify.Auth.getCurrentUser().getUsername();

                        preferencesHelper = new PreferencesHelper(mContext);
                        PreferencesHelper.setUser("user", name.toString());
                        PreferencesHelper.setEmail("email", name.toString());


                        AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                        String token = cognitoAuthSession.getUserPoolTokens().getValue().getIdToken();
                        String accesToken = cognitoAuthSession.getUserPoolTokens().getValue().getAccessToken();

                        Log.v("token con session activa--> ${session.userPoolTokensResult.value?.idToken}",cognitoAuthSession.getUserPoolTokens().getValue().getIdToken());
                        Log.v("token con session activa acces-2.1--> ${session.userPoolTokensResult.value?.accessToken}",cognitoAuthSession.getUserPoolTokens().getValue().getAccessToken());

                    }



                    listener.onSuccess(estateSession.toString());
                },
                error -> {
                    Log.e("AmplifyQuickstart", error.toString());
                    listener.onSuccess("fetchAuthSessionError");
                }
        );
    }


    public void logOut(Context context) {

        Amplify.Auth.signOut(new Action() {
                                 @Override
                                 public void call() {
                                   Utils.goToLoginRegister(context);
                                 }
                             },
                error -> {
                    Log.e("", "Error");
                });
    }

    //https://www.youtube.com/watch?v=B4HjPRA4A8k&list=PLrAF24Xspn3WsVBl4AwmpdMl5g-kHw2Zu

    public void setListener(Callfun listener) {
        this.listener = listener;
    }

}
