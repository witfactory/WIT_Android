package com.cb.witfactory.mqtt;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class MqttHandler {

    private MqttClient client;

    public void connect(String brokerUrl, String clientId, InputStream calInputStream, InputStream certInputStream, InputStream keyInputStream) {
        try {
            // Set up the persistence layer
            MemoryPersistence persistence = new MemoryPersistence();

            // Initialize the MQTT client
            client = new MqttClient(brokerUrl, clientId, persistence);

            // Set up SSL/TLS options
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);

            try {
                // Load the key store
                InputStream caInputStream = getClass().getResourceAsStream("/assets/AmazonRootCA1.pem");
                InputStream clientCertInputStream = getClass().getResourceAsStream("/assets/1-certificate.pem.crt");
                InputStream clientKeyInputStream = getClass().getResourceAsStream("/assets/2-private_pk.key");
                KeyStore keyStore = ClientLocalKeyStore.loadKeyStore(caInputStream, clientCertInputStream, clientKeyInputStream, "");

                // Set up the SSL context
                SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
                KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmf.init(keyStore, "".toCharArray());
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                tmf.init(keyStore);
                sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

                // Set the socket factory on the connection options
                connectOptions.setSocketFactory(sslContext.getSocketFactory());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Connect to the broker
            client.connect(connectOptions);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic7, String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            client.publish(topic7, mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
            Log.v("mqttt   publish  error2",e.toString());
        }
    }

    public void subscribe(String topic8, int[] qos) {
        try {
        //    client.subscribe(topic8);
            client.subscribe(topic8, 0, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String payload = new String(message.getPayload());
                    Log.d("TAG", "Received message: " + payload);
                }
            });

        Log.v("---","--");
        } catch (MqttException e) {
            e.printStackTrace();

            Log.v("mqttt  error",e.toString());
        }
    }




}
