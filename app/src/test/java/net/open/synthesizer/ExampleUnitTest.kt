package net.open.synthesizer

import io.reactivex.schedulers.Schedulers
import net.open.synthesizer.core.logs.Logger
import net.open.synthesizer.core.logs.Logger.Companion.loggers
import net.open.synthesizer.core.net.BaseNetConfigure
import net.open.synthesizer.core.net.BaseNetConfigureAdapter
import net.open.synthesizer.core.net.BaseObserver
import net.open.synthesizer.core.net.RetrofitManager
import net.open.synthesizer.entities.TemplateEntity
import net.open.synthesizer.networkservices.TemplateService
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.After
import org.junit.Test

import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var retrofitManager: RetrofitManager
    var result = -100
    @Before
    fun initValues(){
        var clz = Class.forName("net.open.synthesizer.core.base.ApplicationHook")
        //Load the initial logic by using reflection
        Class.forName("net.open.synthesizer.core.base.ApplicationHook")
            .getMethod("initComponents")
            .invoke(clz.newInstance())
        retrofitManager = RetrofitManager("default")
        Logger("Retrofit")
    }
    @Test
    fun testFun() {
        var mediaType: MediaType? = "text/application".toMediaTypeOrNull()
        var requestBody: RequestBody = "{}".toRequestBody(mediaType)
        retrofitManager.getService(TemplateService::class.java).templateRequest(requestBody)
            .subscribeOn(Schedulers.io())
            .subscribe(BaseObserver<TemplateEntity>{
                result = it.result
                assert(it.result==1)
            })
        while(result==-100){
            Thread.sleep(3000L)
        }
    }
    @After
    fun flashBack(){
        loggers["Retrofit"]?.flashBack()
    }
}
