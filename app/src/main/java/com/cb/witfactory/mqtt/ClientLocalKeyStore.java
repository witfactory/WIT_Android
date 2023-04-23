package com.cb.witfactory.mqtt;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;



public class ClientLocalKeyStore {

    // Change the certificate names to match your certificate files in the assets folder
    private static final String CA_CERT_NAME = "AmazonRootCA1.pem";
    private static final String CLIENT_CERT_NAME = "1-certificate.pem.crt";
    private static final String CLIENT_KEY_NAME = "2-private_pk.key";

    // Load the certificates from the assets folder
    public static KeyStore loadKeyStore(InputStream caInputStream, InputStream clientCertInputStream, InputStream clientKeyInputStream, String password) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        X509Certificate caCert = (X509Certificate) cf.generateCertificate(caInputStream);
        X509Certificate clientCert = (X509Certificate) cf.generateCertificate(clientCertInputStream);
        PrivateKey clientKey = getPrivateKey(clientKeyInputStream);

        // Load the certificates into a KeyStore
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(null, null);
        keyStore.setCertificateEntry(CA_CERT_NAME, caCert);
        keyStore.setCertificateEntry(CLIENT_CERT_NAME, clientCert);
        keyStore.setKeyEntry(CLIENT_KEY_NAME, clientKey, password.toCharArray(), new java.security.cert.Certificate[]{clientCert});

        return keyStore;
    }

    // Helper method to get the PrivateKey from the client key file
    private static PrivateKey getPrivateKey(InputStream keyInputStream) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(keyInputStream));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (!line.startsWith("-----BEGIN") && !line.startsWith("-----END") && !line.isEmpty()) {
                sb.append(line.trim());
            }
        }
        br.close();

        byte[] encodedKey = Base64.decode(sb.toString(), Base64.DEFAULT);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }
}
