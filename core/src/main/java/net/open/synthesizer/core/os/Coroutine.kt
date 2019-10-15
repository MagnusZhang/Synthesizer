package net.open.synthesizer.core.os

import kotlinx.coroutines.*
import net.open.synthesizer.core.operators.safe
import kotlin.coroutines.EmptyCoroutineContext



/**
 * Decide
 */
enum class Scope(val scope:CoroutineScope){
    /**
     * Main Scope
     */
     MAINSCOPE(MainScope()),
     GLOBALSCOPE(GlobalScope),
     CACULATIONSCOPE(CoroutineScope(EmptyCoroutineContext)),
     IOSCOPE(CoroutineScope(EmptyCoroutineContext))
}

class Coroutine{
    companion object{
        fun future(block:()->Unit): CoroutineFuture = CoroutineFuture(block)
    }
}

interface CoroutineExecutable{
    fun delay(delayTime:Long,onNext: () -> Unit)
    fun next(onNext: () -> Unit)
    fun cancel()
}


class CoroutineFuture(val block:()->Unit):CoroutineExecutable{
    override fun cancel() {
        task?.cancel()
    }

    private var scope:Scope = Scope.GLOBALSCOPE
    var task:Job? = null

    private var coroutineScope:CoroutineScope? = null


    fun observe(scope:Scope):CoroutineExecutable{
        this.scope = scope
        return this
    }

    override fun delay(delayTime:Long,onNext: () -> Unit){
        task =  GlobalScope.launch {
            suspend{
                kotlinx.coroutines.delay(delayTime)
                safe {
                    block.invoke()
                    scope.scope.launch {
                        suspend {
                            onNext.invoke()
                        }
                    }
                }
            }.invoke()
        }
    }

    override fun next(onNext: () -> Unit){
        task = GlobalScope.launch {
            suspend{
                safe {
                    block.invoke()
                    scope.scope.launch{
                        suspend{
                            onNext.invoke()
                        }
                    }
                }
            }.invoke()
        }
    }
}

