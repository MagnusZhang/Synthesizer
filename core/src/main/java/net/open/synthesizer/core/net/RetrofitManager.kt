package net.open.synthesizer.core.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class RetrofitManager(val tag:String,val baseUrl:String){
    private var retrofit:Retrofit
    private var httpClient:OkHttpClient
    init {
        var httpClientBuilder  = OkHttpClient.Builder()
        BaseInterceptor.interceptors.forEach {
            httpClientBuilder.addInterceptor(BaseInterceptor(it))
        }
        httpClient = httpClientBuilder.build()
        var retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.client(httpClient)
        retrofitBuilder.baseUrl(baseUrl)
        retrofit = retrofitBuilder.build()
    }
    fun <T> getService(service:Class<T>):T{
       return retrofit.create(service)
    }
}