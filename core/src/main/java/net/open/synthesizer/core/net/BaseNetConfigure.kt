package net.open.synthesizer.core.net

data class BaseNetConfigure(val baseUrl:String,var preProcessors:PreProcessor? = null)

interface BaseNetConfigureAdapter{
    fun getNetConfigure(tag:String):BaseNetConfigure
    companion object{
        private var adapterInstance:BaseNetConfigureAdapter = DefaultNetConfigureAdapter("http://www")
        fun setBaseUrl(baseUrl: String){
            adapterInstance = DefaultNetConfigureAdapter(baseUrl)
        }
        fun setNetConfigureAdapter(adapter:BaseNetConfigureAdapter){
            adapterInstance =  adapter
        }
        fun getConfigure(tag:String):BaseNetConfigure = adapterInstance.getNetConfigure(tag)
    }
}

internal class DefaultNetConfigureAdapter(val baseUrl: String):BaseNetConfigureAdapter{
    override fun getNetConfigure(tag: String): BaseNetConfigure =
        BaseNetConfigure(baseUrl)


}