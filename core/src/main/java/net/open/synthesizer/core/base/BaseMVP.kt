package net.open.synthesizer.core.base

import android.app.Application
import net.open.synthesizer.core.net.RetrofitManager
import javax.inject.Inject

interface BaseView{

}

open class BasePresenter<V:Any,M:Any>(var view:V,var model:M)

open class BaseModel{
    var retrofitManager:RetrofitManager? = null
    var tag = ""
    private fun getUrl(tag:String):String = "http://www.baidu.com"
    constructor(tag:String){
        this.tag = tag
        retrofitManager = RetrofitManager(tag)
    }
}