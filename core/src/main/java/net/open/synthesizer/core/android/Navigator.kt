package net.open.synthesizer.core.android

import android.app.Activity
import android.content.Intent
import android.net.Uri

class Navigator{
    var mutableMap = mutableMapOf<String,String>()
    var mActivity:Activity? = null
    var mScheme = ""
    var mHost = ""
    fun put(key:String,value:String):Navigator{
        mutableMap[key] = value
        return this
    }
    fun activity(act: Activity):Navigator{
        mActivity = act
        return this
    }
    fun scheme(sch:String):Navigator{
        mScheme = sch
        return this
    }
    fun host(hst:String):Navigator{
        mHost = hst
        return this
    }
    fun launch(){
        var intent = Intent()
        intent.data = getUri()
        intent.action = "android.intent.action.VIEW"
        mActivity?.startActivity(intent)
    }
    fun launchForResult(requestCode:Int){
        var intent = Intent()
        intent.data = getUri()
        mActivity?.startActivityForResult(intent,requestCode)
    }
    private fun getUri():Uri{
        var sbuild = StringBuilder()
        sbuild.append("$mScheme://$mHost")
        if(mutableMap.size>0){
            sbuild.append("?")
            var it = mutableMap.iterator()
            while (it.hasNext()){
                var entry = it.next()
                sbuild.append("${entry.key}=${entry.value}")
                if(it.hasNext()){
                    sbuild.append("&")
                }
            }
        }
        var uri = Uri.parse(sbuild.toString())
        return uri
    }
}