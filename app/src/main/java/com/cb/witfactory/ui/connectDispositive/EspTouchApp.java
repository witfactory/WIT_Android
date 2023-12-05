package com.cb.witfactory.ui.connectDispositive;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

import java.util.HashMap;
import java.util.Map;

public class EspTouchApp extends Application {
    private static EspTouchApp app;

    private MutableLiveData<String> mBroadcastData;

    private Map<String, Object> mCacheMap;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }

            switch (action) {
                case WifiManager.NETWORK_STATE_CHANGED_ACTION:
                case LocationManager.PROVIDERS_CHANGED_ACTION:
                    mBroadcastData.setValue(action);
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();


        //EspTouchApp
        app = this;

        mCacheMap = new HashMap<>();

        mBroadcastData = new MutableLiveData<>();
        IntentFilter filter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            filter.addAction(LocationManager.PROVIDERS_CHANGED_ACTION);
        }
        registerReceiver(mReceiver, filter);


        /*validando que ampli cargue con la app esto va en manifest

        <application
        android:name=".ui.connectDispositive.EspTouchApp"


        esta EspTouchApp y amplify por q es un archivo de configuración  se inician dos elementos ene l mismo archivo

        */
        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Log.i("MyCognitoApp", "Initialized Cognito");
        } catch (AmplifyException e) {
            e.printStackTrace();
            Log.e("MyCognitoApp", "Could not initialize Cognito", e);
        }
        try {
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        unregisterReceiver(mReceiver);

    }

    public static EspTouchApp getInstance() {
        return app;
    }

    public void observeBroadcast(LifecycleOwner owner, Observer<String> observer) {
        mBroadcastData.observe(owner, observer);
    }

    public void observeBroadcastForever(Observer<String> observer) {
        mBroadcastData.observeForever(observer);
    }

    public void removeBroadcastObserver(Observer<String> observer) {
        mBroadcastData.removeObserver(observer);
    }

    public void putCache(String key, Object value) {
        mCacheMap.put(key, value);
    }

    public Object takeCache(String key) {
        return mCacheMap.remove(key);
    }
}