package com.cb.witfactory.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.cb.witfactory.R;
import com.cb.witfactory.databinding.FragmentHomeBinding;
import com.cb.witfactory.model.PreferencesHelper;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private PreferencesHelper preferencesHelper;
    final String TAG = "HomeFragment";
    MqttAndroidClient client;



    InputStream mCaCrtFile;
    InputStream mCrtFile;
    InputStream mKeyFile;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        preferencesHelper = new PreferencesHelper(getContext());

        String user = PreferencesHelper.getUser("user", "");
        String[] arrOfStr = user.split("@");

        String dataUser = arrOfStr[0];

        binding.txtUser.setText(getString(R.string.text_welcome_user) + "\n" + dataUser);

        binding.txtUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_login_menu);
                navController.navigateUp();
                navController.navigate(R.id.menu_device);
            }
        });

        initCert();
        conexionMqtt();

        return root;
    }


    public void conexionMqtt(){
        String clientId = MqttClient.generateClientId();
        client =
                new MqttAndroidClient(getContext(), "mqtts://a30yv4dd3vnzo-ats.iot.us-east-1.amazonaws.com:8883/",
                        clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // Si mqtt conectó
                    Toast.makeText(getContext(), "CONECTADO MQTT", Toast.LENGTH_LONG).show();
                    suscripcionTopics();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(getContext(), "ERROR DE CONEXIÓN", Toast.LENGTH_LONG).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }


    private void suscripcionTopics(){

        try{
           // client.subscribe("pepito/wit102/sensor/sala/c7b6c3ae-1fb3-4726-92c2-6e32b095e8b7/alarms",0);
            client.subscribe("mqtts://a30yv4dd3vnzo-ats.iot.us-east-1.amazonaws.com:8883",0);

        }catch (MqttException e){
            e.printStackTrace();
        }

    }


    public void initCert() {
        try {
            mCaCrtFile = this.getResources().openRawResource(R.raw.amazontootca3);
            mCrtFile = this.getResources().openRawResource(R.raw.certificate);
            mKeyFile = this.getResources().openRawResource(R.raw.privatepem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SSLSocketFactory 实现双向TLS认证，因为IoT Core需要双向TLS认证
    public static SSLSocketFactory getSocketFactory(InputStream caCrtFile, InputStream crtFile, InputStream keyFile,
                                                    String password) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // load CA certificate
        X509Certificate caCert = null;

        BufferedInputStream bis = new BufferedInputStream(caCrtFile);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        while (bis.available() > 0) {
            caCert = (X509Certificate) cf.generateCertificate(bis);
        }
        // load client certificate
        bis = new BufferedInputStream(crtFile);
        X509Certificate cert = null;
        while (bis.available() > 0) {
            cert = (X509Certificate) cf.generateCertificate(bis);
        }
        // load client private cert
        PEMParser pemParser = new PEMParser(new InputStreamReader(keyFile));
        Object object = pemParser.readObject();
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        KeyPair key = converter.getKeyPair((PEMKeyPair) object);

        KeyStore caKs = KeyStore.getInstance(KeyStore.getDefaultType());
        caKs.load(null, null);
        caKs.setCertificateEntry("cert-certificate", caCert);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(caKs);

        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null, null);
        ks.setCertificateEntry("certificate", cert);
        ks.setKeyEntry("private-cert", key.getPrivate(), password.toCharArray(),
                new java.security.cert.Certificate[]{cert});
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, password.toCharArray());

        SSLContext context = SSLContext.getInstance("TLSv1.2");
        context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        return context.getSocketFactory();
    }


}