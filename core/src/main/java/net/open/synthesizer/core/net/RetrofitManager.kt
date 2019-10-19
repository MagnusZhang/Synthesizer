package net.open.synthesizer.core.net

import android.util.Log
import net.open.synthesizer.core.logs.Logger.Companion.loggers
import net.open.synthesizer.core.net.BaseNetConfigureAdapter.Companion.getConfigure
import net.open.synthesizer.core.net.SSLManager.Companion.getSSLFactory
import net.open.synthesizer.core.net.SSLManager.Companion.getTrustManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  Basic Request Logger
 */
class RequestLogger:HttpLoggingInterceptor.Logger{
    override fun log(message: String) {
        // You may add other logger here
        if(loggers["Retrofit"]!=null){
            loggers["Retrofit"]?.log(message)
        }
    }
}

/**
 * Tools for depriving Retrofit Apis from our project
 */
class RetrofitManager(val tag:String){
    /**
     * Retrofit instance
     */
    private var retrofit:Retrofit
    /**
     * OkHttp client instance
     */
    private var httpClient:OkHttpClient

    init {
        var networkConfigure = getConfigure(tag)
        var httpClientBuilder  = OkHttpClient.Builder()
        var loggerInterceptor = HttpLoggingInterceptor(RequestLogger())//logger
        loggerInterceptor.level = HttpLoggingInterceptor.Level.BASIC // print url and body
        // Use reactHeader to add the headers supervised:
        // loggerInterceptor.redactHeader("Accept")
        httpClientBuilder.sslSocketFactory(getSSLFactory(),getTrustManager())
        httpClientBuilder.addInterceptor(loggerInterceptor)
        httpClientBuilder.addInterceptor(BaseInterceptor(networkConfigure.preProcessors))
        httpClient = httpClientBuilder.build()
        var retrofitBuilder = Retrofit.Builder()
        retrofitBuilder
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .baseUrl(networkConfigure.baseUrl)
        retrofit = retrofitBuilder.build()
    }
    fun <T> getService(service:Class<T>):T{
       return retrofit.create(service)
    }
}