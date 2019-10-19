package net.open.synthesizer.networkservices

import io.reactivex.Observable
import net.open.synthesizer.entities.TemplateEntity
import net.open.synthesizer.networkservices.ServiceConstant.Companion.DEFAULTHEAD
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HEAD
import retrofit2.http.Headers
import retrofit2.http.POST

const val TEMPLATE_REQUESTPATH = "api/template"

interface TemplateService{
    @Headers(DEFAULTHEAD)
    @POST(TEMPLATE_REQUESTPATH)
    fun templateRequest(@Body body: RequestBody):Observable<TemplateEntity>
}