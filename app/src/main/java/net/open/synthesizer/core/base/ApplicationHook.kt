package net.open.synthesizer.core.base

import net.open.synthesizer.core.net.BaseNetConfigure
import net.open.synthesizer.core.net.BaseNetConfigureAdapter

/**
 * Application Hook should only define this way. And you do not change the package name as it will
 * be referred by reflection in BaseApplication.
 * You may complete you initial logic in "initComponents" method
 */
class ApplicationHook{
    /**
     * The init logic
     */
    fun initComponents(){
        // Here is how you initialize an network adapter.
        // And you can also initialize by simply define the base URL:
        // BaseNetConfigureAdapter.setBaseUrl("http://localhost:8080/")
        BaseNetConfigureAdapter.setNetConfigureAdapter(object :BaseNetConfigureAdapter{
            override fun getNetConfigure(tag: String): BaseNetConfigure {
                return BaseNetConfigure("http://localhost:8080/")
            }
        })
        // Add your code here
    }
}