package net.open.synthesizer.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

open class BaseActivity<P:Any>:AppCompatActivity(){
    @JvmField
    @Inject
    var  presenter:P? = null

    private var layoutId:Int = -1

    var layoutResId:Int
        get() = layoutId
        set(value) {
            layoutId = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(layoutId!=-1)
            setContentView(layoutId)
    }
}