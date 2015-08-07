/**
 * Copyright (c) 2008 - 2011 Bhaveshkumar Thaker [http://bhaveshthaker.com]. All
 * rights reserved.
 */
package mbrinstant.rest_client;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 * @author <a href="http://bhaveshthaker.com/">Bhavesh Thaker</a>
 *
 */
public class SecureRestClientTrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public boolean isClientTrusted(X509Certificate[] arg0) {
        return true;
    }

    public boolean isServerTrusted(X509Certificate[] arg0) {
        return true;
    }

}
