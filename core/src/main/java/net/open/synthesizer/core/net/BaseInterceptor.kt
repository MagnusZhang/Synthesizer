package net.open.synthesizer.core.net

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

typealias PreProcessor = (Request)-> Request
class BaseInterceptor(var processor: PreProcessor?=null):Interceptor{
    companion object{
        val interceptors:MutableList<PreProcessor> = mutableListOf()
        fun addNetworkPreprocessor(processor:PreProcessor){
            interceptors.add(processor)
        }
    }
    // Here is a sample to use PreProcessor
    var mainProcessor:PreProcessor = {
        var httpUrl:HttpUrl = it.url
        // You may modify httpUrl if you want, but we are keeping the original url.
        var requestBody = it.body
        // You may modify requestBody here, and we are keeping the original requestBody
        // Here is how you modify the request
        it.newBuilder()
            .url(httpUrl)
            .post(requestBody!!)
            .addHeader("ExtraName","Android")
            .build()
    }

    override fun intercept(chain: Interceptor.Chain):Response{
        if(processor!=null)
            mainProcessor = processor!!
        var request =  mainProcessor.invoke(chain.request())
        interceptors.forEach {
            request = it.invoke(request)
        }
        return chain.proceed(request)
    }

}

