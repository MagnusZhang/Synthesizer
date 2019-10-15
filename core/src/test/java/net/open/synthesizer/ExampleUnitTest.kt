package net.open.synthesizer

import net.open.synthesizer.core.os.Coroutine.Companion.future
import net.open.synthesizer.core.os.Scope
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        while (true ){
            future{
                print("enter room!")
            }.observe(Scope.CACULATIONSCOPE)
            .delay(1000L) {
                print("finished")
            }
        }
    }
}
