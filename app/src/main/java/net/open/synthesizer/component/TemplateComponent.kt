package net.open.synthesizer.component

import dagger.Component
import net.open.synthesizer.module.TemplateModule
import net.open.synthesizer.view.activity.TemplateActivity

@Component(modules = [TemplateModule::class])
interface TemplateComponent {
    fun inject(activity: TemplateActivity)
}