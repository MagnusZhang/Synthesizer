package net.open.synthesizer.module

import dagger.Module
import dagger.Provides
import net.open.synthesizer.contract.TemplateContract
import net.open.synthesizer.model.TemplateModel
import net.open.synthesizer.presenter.TemplatePresenter
import net.open.synthesizer.view.activity.TemplateActivity

@Module
class TemplateModule(val activity: TemplateActivity) {
    companion object{
        val TAG = "template"
    }

    /**
     * provider for persenter
     */
    @Provides
    fun provideTemplatePresenter(view:TemplateContract.View,model: TemplateContract.Model) = TemplatePresenter(view,model)

    /**
     * provider for view
     */
    @Provides
    fun provideView():TemplateContract.View  = activity

    /**
     * provider for model
     */
    @Provides
    fun provideModel():TemplateContract.Model = TemplateModel(TAG)
}