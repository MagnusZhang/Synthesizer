package net.open.synthesizer.model

import io.reactivex.Observable
import net.open.synthesizer.core.base.BaseModel
import net.open.synthesizer.contract.TemplateContract
import net.open.synthesizer.entities.TemplateEntity
import net.open.synthesizer.networkservices.TemplateService
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class TemplateModel(tag:String):BaseModel(tag),TemplateContract.Model{
    fun getTemplateData(): Observable<TemplateEntity> {
        var mediaType: MediaType? = "text/application".toMediaTypeOrNull()
        var requestBody:RequestBody = "{}".toRequestBody(mediaType)
        return retrofitManager!!.getService(TemplateService::class.java).templateRequest(requestBody)
    }
}