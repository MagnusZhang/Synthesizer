package net.open.synthesizer.core.base

import androidx.multidex.MultiDexApplication

class BaseApplication:MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        initComponents()
    }

    fun initComponents(){
        var clz = Class.forName("net.open.synthesizer.core.base.ApplicationHook")
        //Load the initial logic by using reflection
        Class.forName("net.open.synthesizer.core.base.ApplicationHook")
            .getMethod("initComponents")
            .invoke(clz.newInstance())
    }
}
