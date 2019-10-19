package net.open.synthesizer.contract

import io.reactivex.Observable
import net.open.synthesizer.entities.TemplateEntity

class TemplateContract{
    /**
     * Open View Interface
     */
    interface View{
        /***Here goes your interfaces for view*/
    }
    /**
     * Open Model Interface
     */
    interface Model{
        /***Here goes your interfaces for model*/
        fun getTemplateData(): Observable<TemplateEntity>
    }
}