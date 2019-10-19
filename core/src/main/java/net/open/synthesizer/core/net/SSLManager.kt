package net.open.synthesizer.core.net

import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

class CustomizedTrustManager:X509TrustManager{
    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}

    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return emptyArray()
    }
}

class SSLManager{
    companion object{
       private var tManager = CustomizedTrustManager()
       fun getTrustManager():X509TrustManager = tManager
       fun getSSLFactory():SSLSocketFactory{
            try {
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, arrayOf(tManager), SecureRandom())
                return sslContext.socketFactory
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }

}