package com.cb.witfactory.data.classModel;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;

import android.content.Context;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.results.SignUpResult;
import com.amazonaws.mobile.client.results.UserCodeDeliveryDetails;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.EnumVaribles;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.ui.login.LoginFragment;

import org.chromium.base.Log;

import java.util.HashMap;
import java.util.Map;

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
        final String username = "xitatak493@webonoid.com";
        final String _password = "T@pi@231290.";
        final Map<String, String> attributes = new HashMap<>();
        attributes.put("first_name", "maria luisa");
        attributes.put("user", "xitatak493@webonoid.com");
        attributes.put("last_name", "Lopez Perez");
        attributes.put("country", "Colombia");
        attributes.put("city", "Manizales");
        attributes.put("zip_code", "170007");
        attributes.put("address", "Cr 19 # 25-28");
        attributes.put("account_type", "P");
        attributes.put("telephone", "3196381721");
        attributes.put("user_principal", "");
        attributes.put("suite", "test");
        attributes.put("device_id", "new");
        attributes.put("appos", "new");
        AWSMobileClient.getInstance().signUp(username, _password, attributes, null, new Callback<SignUpResult>() {
            @Override
            public void onResult(final SignUpResult signUpResult) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("ie", "Sign-up callback state: " + signUpResult.getConfirmationState());
                        if (!signUpResult.getConfirmationState()) {
                            final UserCodeDeliveryDetails details = signUpResult.getUserCodeDeliveryDetails();
                           // makeToast("Confirm sign-up with: " + details.getDestination());
                            Log.v("daTA", details.getDestination().toString());
                        } else {
                          //  makeToast("Sign-up done.");
                            Log.v("daTA","Sign-up done.");
                        }
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                Log.e("TAG", "Sign-up error", e);
            }
        });
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

        Amplify.Auth.resendUserAttributeConfirmationCode(email,
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



    public void confirmSigUp(String code, String emailUserName){
        try {
         //   AuthUserAttributeKey email = AuthUserAttributeKey.email();
            Amplify.Auth.confirmSignUp(
                    "woteme7765@tohup.com",
                    code,
                    result ->
                    {
                        Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                        String sinUp = EnumVaribles.confirmSigUp.toString();
                        listener.onSuccess(sinUp);
                    },
                    error -> {
                        Log.e("AuthQuickstart", error.toString());
                        String sinUp = EnumVaribles.confirmCode.toString();
                        listener.onError(sinUp);


                    }
            );
        } catch (Exception error) {
            Log.e("AuthQuickstart", "unexpected error: " + error);
        }
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
                        String idAmplify = Amplify.Auth.getCurrentUser().getUserId();

                        preferencesHelper = new PreferencesHelper(mContext);
                        PreferencesHelper.setUser("user", name.toString());
                        PreferencesHelper.setEmail("email", name.toString());


                        AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                        String token = cognitoAuthSession.getUserPoolTokens().getValue().getIdToken();
                        String accesToken = cognitoAuthSession.getUserPoolTokens().getValue().getAccessToken();
                        String refreshToken = cognitoAuthSession.getUserPoolTokens().getValue().getRefreshToken();

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
