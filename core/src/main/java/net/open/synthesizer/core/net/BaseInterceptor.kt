package net.open.synthesizer.core.net

import okhttp3.Interceptor
import okhttp3.Response

typealias PreProcessor = (chain: Interceptor.Chain)-> Response

class BaseInterceptor(val processor: PreProcessor):Interceptor{
    companion object{
        val interceptors:MutableList<PreProcessor> = mutableListOf()
        fun addNetworkPreprocessor(processor:PreProcessor){
            interceptors.add(processor)
        }
    }
    override fun intercept(chain: Interceptor.Chain): Response =
        processor.invoke(chain)

}