package net.open.synthesizer.core.utils

import android.app.Application
import javax.inject.Inject

class RuntimeUtils{
    @JvmField
    @Inject
    var application: Application? = null
}