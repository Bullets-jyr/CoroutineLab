package ch03

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MyScope : CoroutineScope {
    val scopeJob: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + scopeJob
}

fun main() = runBlocking {
    val myScope = MyScope()
    val job1 = myScope.launch {
        repeat(3) {
            delay(300)
            println("first coroutine $it")
        }
    }
    val job2 = myScope.launch {
        repeat(3) {
            delay(200)
            println("second coroutine $it")
        }
    }

    delay(500)
//    job1.cancel()
//    job2.cancel()
    myScope.scopeJob.cancel()
}