package net.open.synthesizer.core.logs

import java.text.SimpleDateFormat
import java.util.*

/**
 * Log Items
 * @property date Log date
 * @property message Log content
 */
data class LogItem(val date:String,val message:String)

/**
 *  Log tool
 *  Logger is used for maintain a buffer of logs.
 *  These logs will not be stored on disk. And it could only be used for
 *  logs replaying and trouble shooting.
 */
class Logger(var tag:String, var loggerBufferSize:Int =  255){
        companion object{
            private val loggerMap = mutableMapOf<String,Logger>()
            private val simpleDateFormat = SimpleDateFormat.getDateInstance()
            var loggers:MutableMap<String,Logger>
                get()=loggerMap
                set(_){}

        }
        private var logBuffer:Array<LogItem?> = Array(loggerBufferSize){
            null
        }
        init {
            loggers[tag] = this // Add logger into map
        }

        /**
         * Index of current log
         */
        private var currentIndex = -1

        /**
         *  Record a message
         */
        fun log(msg:String){
            currentIndex = (currentIndex+1)%loggerBufferSize // move the index
            logBuffer[currentIndex] = LogItem(simpleDateFormat.format(Date()),msg)
        }

        /**
         * Print all messages in the buffer
         */
        fun flashBack(){
            if(currentIndex==-1)
                return
            var pivotIndex = currentIndex
            var msgList = mutableListOf<String>()
            do {
                if(logBuffer[pivotIndex]!=null)
                    msgList.add(0,"${logBuffer[pivotIndex]!!.date} ${logBuffer[pivotIndex]!!.message}")
                pivotIndex = (pivotIndex+loggerBufferSize-1)%loggerBufferSize
            }while (pivotIndex!=pivotIndex&&logBuffer[pivotIndex]!=null)
            msgList.forEach {
                print(it)
            }
        }
}