package com.cb.witfactory.esp32.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.ContentLoadingProgressBar;


import com.cb.witfactory.R;
import com.cb.witfactory.esp32.AppConstants;
import com.cb.witfactory.model.PreferencesHelper;
import com.espressif.provisioning.DeviceConnectionEvent;
import com.espressif.provisioning.ESPConstants;
import com.espressif.provisioning.ESPDevice;
import com.espressif.provisioning.ESPProvisionManager;
import com.espressif.provisioning.listeners.ProvisionListener;
import com.espressif.provisioning.listeners.ResponseListener;
import com.espressif.provisioning.security.Security;
import com.espressif.provisioning.security.Security0;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class ProvisionActivity extends AppCompatActivity {

    private static final String TAG = ProvisionActivity.class.getSimpleName();

    private TextView tvTitle, tvBack, tvCancel;
    private ImageView tick1, tick2, tick3;
    private ContentLoadingProgressBar progress1, progress2, progress3;
    private TextView tvErrAtStep1, tvErrAtStep2, tvErrAtStep3, tvProvError;

    private CardView btnOk;
    private TextView txtOkBtn;

    private String ssidValue, passphraseValue = "";
    private ESPProvisionManager provisionManager;
    private boolean isProvisioningCompleted = false;


    private PreferencesHelper preferencesHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provision);

        Intent intent = getIntent();
        ssidValue = intent.getStringExtra(AppConstants.KEY_WIFI_SSID);
        passphraseValue = intent.getStringExtra(AppConstants.KEY_WIFI_PASSWORD);
        provisionManager = ESPProvisionManager.getInstance(getApplicationContext());
        initViews();
        EventBus.getDefault().register(this);

        Log.d(TAG, "Selected AP -" + ssidValue);
        showLoading();
        sendData();

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doProvisioning();
            }
        }, 5000);
*/

    }

    @Override
    public void onBackPressed() {
        provisionManager.getEspDevice().disconnectDevice();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DeviceConnectionEvent event) {

        Log.d(TAG, "On Device Connection Event RECEIVED : " + event.getEventType());

        switch (event.getEventType()) {

            case ESPConstants.EVENT_DEVICE_DISCONNECTED:
                if (!isFinishing() && !isProvisioningCompleted) {
                    showAlertForDeviceDisconnected();
                }
                break;
        }
    }

    private View.OnClickListener okBtnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            provisionManager.getEspDevice().disconnectDevice();

            //validar redirecion intent
            finish();
        }
    };

    private void initViews() {

        tvTitle = findViewById(R.id.main_toolbar_title);
        tvBack = findViewById(R.id.btn_back);
        tvCancel = findViewById(R.id.btn_cancel);

        tick1 = findViewById(R.id.iv_tick_1);
        tick2 = findViewById(R.id.iv_tick_2);
        tick3 = findViewById(R.id.iv_tick_3);

        progress1 = findViewById(R.id.prov_progress_1);
        progress2 = findViewById(R.id.prov_progress_2);
        progress3 = findViewById(R.id.prov_progress_3);

        tvErrAtStep1 = findViewById(R.id.tv_prov_error_1);
        tvErrAtStep2 = findViewById(R.id.tv_prov_error_2);
        tvErrAtStep3 = findViewById(R.id.tv_prov_error_3);
        tvProvError = findViewById(R.id.tv_prov_error);

        tvTitle.setText(R.string.title_activity_provisioning);
        tvBack.setVisibility(View.GONE);
        tvCancel.setVisibility(View.GONE);

        btnOk = findViewById(R.id.btn_ok);
        txtOkBtn = findViewById(R.id.text_btn);
        btnOk.findViewById(R.id.iv_arrow).setVisibility(View.GONE);

        txtOkBtn.setText(R.string.btn_ok);
        btnOk.setOnClickListener(okBtnClickListener);
    }

    private void doProvisioning() {

        tick1.setVisibility(View.GONE);
        progress1.setVisibility(View.VISIBLE);


        provisionManager.getEspDevice().provision(ssidValue, passphraseValue, new ProvisionListener() {


            @Override
            public void createSessionFailed(Exception e) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        tick1.setImageResource(R.drawable.ic_error);
                        tick1.setVisibility(View.VISIBLE);
                        progress1.setVisibility(View.GONE);
                        tvErrAtStep1.setVisibility(View.VISIBLE);
                        tvErrAtStep1.setText(R.string.error_session_creation);
                        tvProvError.setVisibility(View.VISIBLE);
                        hideLoading();
                    }
                });
            }

            @Override
            public void wifiConfigSent() {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        tick1.setImageResource(R.drawable.ic_checkbox_on);
                        tick1.setVisibility(View.VISIBLE);
                        progress1.setVisibility(View.GONE);
                        tick2.setVisibility(View.GONE);
                        progress2.setVisibility(View.VISIBLE);



                    }
                });
            }

            @Override
            public void wifiConfigFailed(Exception e) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        tick1.setImageResource(R.drawable.ic_error);
                        tick1.setVisibility(View.VISIBLE);
                        progress1.setVisibility(View.GONE);
                        tvErrAtStep1.setVisibility(View.VISIBLE);
                        tvErrAtStep1.setText(R.string.error_prov_step_1);
                        tvProvError.setVisibility(View.VISIBLE);
                        hideLoading();
                    }
                });
            }

            @Override
            public void wifiConfigApplied() {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        tick2.setImageResource(R.drawable.ic_checkbox_on);
                        tick2.setVisibility(View.VISIBLE);
                        progress2.setVisibility(View.GONE);
                        tick3.setVisibility(View.GONE);
                        progress3.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void wifiConfigApplyFailed(Exception e) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        tick2.setImageResource(R.drawable.ic_error);
                        tick2.setVisibility(View.VISIBLE);
                        progress2.setVisibility(View.GONE);
                        tvErrAtStep2.setVisibility(View.VISIBLE);
                        tvErrAtStep2.setText(R.string.error_prov_step_2);
                        tvProvError.setVisibility(View.VISIBLE);
                        hideLoading();
                    }
                });
            }

            @Override
            public void provisioningFailedFromDevice(final ESPConstants.ProvisionFailureReason failureReason) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        switch (failureReason) {
                            case AUTH_FAILED:
                                tvErrAtStep3.setText(R.string.error_authentication_failed);
                                break;
                            case NETWORK_NOT_FOUND:
                                tvErrAtStep3.setText(R.string.error_network_not_found);
                                break;
                            case DEVICE_DISCONNECTED:
                            case UNKNOWN:
                                tvErrAtStep3.setText(R.string.error_prov_step_3);
                                break;
                        }



                        tick3.setImageResource(R.drawable.ic_error);
                        tick3.setVisibility(View.VISIBLE);
                        progress3.setVisibility(View.GONE);
                        tvErrAtStep3.setVisibility(View.VISIBLE);
                        tvProvError.setVisibility(View.VISIBLE);
                        hideLoading();



                    }
                });
            }

            @Override
            public void deviceProvisioningSuccess() {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        String datosWifi = ssidValue + "#"+passphraseValue;



                        isProvisioningCompleted = true;
                        tick3.setImageResource(R.drawable.ic_checkbox_on);
                        tick3.setVisibility(View.VISIBLE);
                        progress3.setVisibility(View.GONE);
                        hideLoading();
                    }
                });
            }

            @Override
            public void onProvisioningFailed(Exception e) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        tick3.setImageResource(R.drawable.ic_error);
                        tick3.setVisibility(View.VISIBLE);
                        progress3.setVisibility(View.GONE);
                        tvErrAtStep3.setVisibility(View.VISIBLE);
                        tvErrAtStep3.setText(R.string.error_prov_step_3);
                        tvProvError.setVisibility(View.VISIBLE);
                        hideLoading();
                    }
                });
            }
        });



    }

    private void sendData() {

        String bytesString = "";
        String email = PreferencesHelper.getEmail("email", "");
        String userId = PreferencesHelper.getEmail("userId", "");

        bytesString = email+"$"+userId;
        Log.v("bytesString",bytesString);
        byte[] arrayDeBytesUTF8 = bytesString.getBytes();


        //Toast.makeText(getApplicationContext(), bytesString , Toast.LENGTH_SHORT).show();
        provisionManager.getEspDevice().sendDataToCustomEndPoint("custom-data", arrayDeBytesUTF8, new ResponseListener() {
            @Override
            public void onSuccess(byte[] returnData) {
                byte[] decryptedData2 = returnData;
                Log.v("exitoso: ", decryptedData2.toString());


                // Decodificar el array de bytes a String utilizando UTF-8
                String textoDecodificado = new String(returnData);

                String[] arrOfStr = textoDecodificado.split(",");
                String deviceId = arrOfStr[0];
                String devicetype = arrOfStr[1];
                String serial = arrOfStr[2];
                String mac = arrOfStr[3];
                // Imprimir el resultado
                Log.v("Texto Decodificado: " , deviceId);
                PreferencesHelper.setDeviceId("deviceId",deviceId);
                PreferencesHelper.setdevicetype("devicetype",devicetype);
                PreferencesHelper.setSerial("serial",serial);
                PreferencesHelper.setMac("mac",mac);
                doProvisioning();
            }

            @Override
            public void onFailure(Exception e) {
                Log.v("error device: ",e.getMessage().toString());
                doProvisioning();
            }
        });
    }

    private void showLoading() {

        btnOk.setEnabled(false);
        btnOk.setAlpha(0.5f);
    }

    public void hideLoading() {

        btnOk.setEnabled(true);
        btnOk.setAlpha(1f);
    }

    private void showAlertForDeviceDisconnected() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.error_title);
        builder.setMessage(R.string.dialog_msg_ble_device_disconnection);

        // Set up the buttons
        builder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.show();
    }
}
