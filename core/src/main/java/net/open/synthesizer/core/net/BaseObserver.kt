package net.open.synthesizer.core.net

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class BaseObserver<T>(
            var tag:String="",
            var error:(Throwable)->Unit = {},
            var complete:()->Unit = {},
            var subscribe:(Disposable)->Unit = {},
            var next:(T)->Unit): Observer<T> {
    var disposable:Disposable? = null
    override fun onComplete() {
        complete.invoke()
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
        subscribe.invoke(d)
    }

    override fun onNext(t: T) {
        next.invoke(t)
    }

    override fun onError(e: Throwable) {
        error.invoke(e)
    }


}