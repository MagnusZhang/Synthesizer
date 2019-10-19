package net.open.synthesizer.presenter

import net.open.synthesizer.core.base.BasePresenter
import net.open.synthesizer.contract.TemplateContract
import net.open.synthesizer.core.net.BaseObserver
import net.open.synthesizer.entities.TemplateEntity

class TemplatePresenter(view:TemplateContract.View,model:TemplateContract.Model):
    BasePresenter<TemplateContract.View,TemplateContract.Model>(view,model){

    fun loadTemplateData(){
        model.getTemplateData().subscribe(BaseObserver<TemplateEntity>{
            // Do your data processing
        })
    }

}