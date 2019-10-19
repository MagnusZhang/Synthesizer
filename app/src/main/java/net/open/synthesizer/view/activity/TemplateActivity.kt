package net.open.synthesizer.view.activity

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_template.*
import net.open.synthesizer.R
import net.open.synthesizer.component.DaggerTemplateComponent
import net.open.synthesizer.core.base.BaseActivity
import net.open.synthesizer.contract.TemplateContract
import net.open.synthesizer.module.TemplateModule
import net.open.synthesizer.presenter.TemplatePresenter

class TemplateActivity:BaseActivity<TemplatePresenter>(),TemplateContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerTemplateComponent.builder().templateModule(TemplateModule(this)).build().inject(this)
        layoutResId = R.layout.activity_template
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        test.setOnClickListener {
            Toast.makeText(this@TemplateActivity,"Test",Toast.LENGTH_LONG).show()
            presenter?.loadTemplateData()//request data
        }
    }
}