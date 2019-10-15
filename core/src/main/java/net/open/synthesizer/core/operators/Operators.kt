package net.open.synthesizer.core.operators

class ExceptionHolder{
    private var exception:Throwable? = null
    var throwable:Throwable?
        get() = exception
        set(value) {exception = value}

}

fun safe(holder:ExceptionHolder?=null,block:()->Unit){
    try {
        block.invoke()
    }catch (e:Throwable){
        if(holder!=null)
            holder.throwable = e
    }
}