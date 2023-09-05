package com.cb.witfactory.data.classModel;

import android.content.Context;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.cognito.result.GlobalSignOutError;
import com.amplifyframework.auth.cognito.result.HostedUIError;
import com.amplifyframework.auth.cognito.result.RevokeTokenError;
import com.amplifyframework.auth.options.AuthConfirmSignUpOptions;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.EnumVaribles;
import com.cb.witfactory.model.PreferencesHelper;

import org.chromium.base.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public Boolean sinUp2(String first_name, String user, String last_name, String country, String city,
                         String zip_code, String address, String account_type, String telephone, String user_principal, String password,String device_id) {

        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), user)
                .userAttribute(AuthUserAttributeKey.givenName(), first_name)
                .userAttribute(AuthUserAttributeKey.familyName(), "Timestamp")
                .userAttribute(AuthUserAttributeKey.custom("custom:country"), country)
                .userAttribute(AuthUserAttributeKey.custom("custom:city"), city)
                .userAttribute(AuthUserAttributeKey.custom("custom:zip_code"), zip_code)
                .userAttribute(AuthUserAttributeKey.custom("custom:address"), address)
                .userAttribute(AuthUserAttributeKey.custom("custom:user_principal"), "")
                .userAttribute(AuthUserAttributeKey.custom("custom:account_type"), "P")
                .userAttribute(AuthUserAttributeKey.phoneNumber(), telephone)
                .userAttribute(AuthUserAttributeKey.custom("custom:suite"), "dasda")
                .userAttribute(AuthUserAttributeKey.custom("custom:appos"), "ANDROID")
                .userAttribute(AuthUserAttributeKey.custom("custom:device_id"), device_id)
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
                    listener.onError("error");
                }
        );

        return authSignUpResult;
    }
public Boolean sinUp(String email, String user, String last_name, String country, String city,
                         String zip_code, String address, String account_type, String telephone, String password,String device_id) {




    List<AuthUserAttribute> userAttributes = new ArrayList<>();
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.email(), user));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.givenName(), last_name));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.familyName(), last_name));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:country"), country));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:city"), city));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:zip_code"), zip_code));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:address"), address));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:user_principal"), user));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:account_type"), account_type));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:country"), account_type));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.phoneNumber(), "+57"+telephone));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:suite"), "rndm"));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:appos"), "ANDROID"));
            userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:device_id"), device_id));

            AuthSignUpOptions options = AuthSignUpOptions.builder().userAttributes(userAttributes).build();

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


    public void confirmSigUp(String code, String emailUserName) {
        try {

            Amplify.Auth.confirmSignUp(
                    emailUserName,
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



    //Inicia sesión
    public Boolean signIn(String email, String password) {
        Amplify.Auth.signIn(
                email,
                password,
                result -> {
                    Log.i("AuthQuickstart", result.isSignedIn() ? "Sign in succeeded" : "Sign in not complete");
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


                    if (estateSession) {


                        preferencesHelper = new PreferencesHelper(mContext);
                        //  PreferencesHelper.setUser("user", name.toString());

                        AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                        String token = cognitoAuthSession.getUserPoolTokensResult().getValue().getIdToken();

                     //   String token = cognitoAuthSession.getUserPoolTokens().getValue().getIdToken();
                       // String accesToken = cognitoAuthSession.getUserPoolTokens().getValue().getAccessToken();
                        //String refreshToken = cognitoAuthSession.getUserPoolTokens().getValue().getRefreshToken();


                        Log.v("token con session activa--> ${session.userPoolTokensResult.value?.idToken}", cognitoAuthSession.getUserPoolTokensResult().getValue().getIdToken());
                        Log.v("token con session activa acces-2.1--> ${session.userPoolTokensResult.value?.accessToken}", cognitoAuthSession.getUserPoolTokensResult().getValue().getAccessToken());

                    }


                    listener.onSuccess(estateSession.toString());
                },
                error -> {
                    Log.e("AmplifyQuickstart", error.toString());
                    listener.onError("fetchAuthSessionError");
                }
        );
    }

    public void resetPassword(String emailUserName){
        Amplify.Auth.resetPassword(emailUserName,
                result->{
                    Log.i("AuthQuickstart", "New password confirmed");
                    listener.onSuccess("ok");
                }, error->{
                    Log.e("AuthQuickstart", error.toString());
                    listener.onError("error");
                });
    }


    public void confirmResetPassword( String emailUserName,String password,String code){
        Amplify.Auth.confirmResetPassword(
                emailUserName,
                password,
                code,
                () ->{
                    Log.i("AuthQuickstart", "New password confirmed");
                    listener.onSuccess("comfirmcode");
                },
                error ->{
                    Log.e("AuthQuickstart", error.toString());
                    listener.onError("errror");
                }
        );
    }


    public void logoutAmplify(Context context){
        Amplify.Auth.signOut( signOutResult -> {
            if (signOutResult instanceof AWSCognitoAuthSignOutResult.CompleteSignOut) {
                // Sign Out completed fully and without errors.
                Log.i("AuthQuickStart", "Signed out successfully");

            } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.PartialSignOut) {
                // Sign Out completed with some errors. User is signed out of the device.
                AWSCognitoAuthSignOutResult.PartialSignOut partialSignOutResult =
                        (AWSCognitoAuthSignOutResult.PartialSignOut) signOutResult;

                HostedUIError hostedUIError = partialSignOutResult.getHostedUIError();
                if (hostedUIError != null) {
                    Log.e("AuthQuickStart", "HostedUI Error", hostedUIError.getException());
                    // Optional: Re-launch hostedUIError.getUrl() in a Custom tab to clear Cognito web session.

                }

                GlobalSignOutError globalSignOutError = partialSignOutResult.getGlobalSignOutError();
                if (globalSignOutError != null) {
                    Log.e("AuthQuickStart", "GlobalSignOut Error", globalSignOutError.getException());
                    // Optional: Use escape hatch to retry revocation of globalSignOutError.getAccessToken().

                }

                RevokeTokenError revokeTokenError = partialSignOutResult.getRevokeTokenError();
                if (revokeTokenError != null) {
                    Log.e("AuthQuickStart", "RevokeToken Error", revokeTokenError.getException());
                    // Optional: Use escape hatch to retry revocation of revokeTokenError.getRefreshToken().
                    
                }
            } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.FailedSignOut) {
                AWSCognitoAuthSignOutResult.FailedSignOut failedSignOutResult =
                        (AWSCognitoAuthSignOutResult.FailedSignOut) signOutResult;
                // Sign Out failed with an exception, leaving the user signed in.
                Log.e("AuthQuickStart", "Sign out Failed", failedSignOutResult.getException());

            }
        });
    }

    //https://www.youtube.com/watch?v=B4HjPRA4A8k&list=PLrAF24Xspn3WsVBl4AwmpdMl5g-kHw2Zu

    public void setListener(Callfun listener) {
        this.listener = listener;
    }

}
