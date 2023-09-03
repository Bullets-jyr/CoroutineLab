package ch03.test2

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MyScope2 : CoroutineScope {
    val scopeJob = Job()
    val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        println("coroutine exception (${context[CoroutineName.Key]} : $exception)")
    }
    override val coroutineContext: CoroutineContext
        get() = CoroutineName("my-scope") + Dispatchers.Default + scopeJob + exceptionHandler
}

fun main() = runBlocking {
    val scope = MyScope2()
    scope.launch {
        println("${coroutineContext[CoroutineName.Key]} is executing on thread : ${Thread.currentThread().name}")
        delay(300)
        throw Exception("error.. coroutine 1..")
    }
    scope.launch(CoroutineName("my-coroutine") + newSingleThreadContext("one thread")) {
        println("${coroutineContext[CoroutineName.Key]} is executing on thread : ${Thread.currentThread().name}")
    }
    delay(1000)
}
