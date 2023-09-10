package ch05.test3

import kotlinx.coroutines.*

val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("exception handler : ${throwable.message}")
}

suspend fun something() = supervisorScope {
    println("thread name : ${Thread.currentThread().name}, ${coroutineContext[CoroutineName]}")

    launch {
        repeat(3) {
            delay(300)
            println("coroutine1.. $it")
        }
    }
    launch {
        repeat(3) {
            delay(200)
            println("coroutine2.. $it")
            if (it == 1) throw Exception("coroutine exception")
        }
    }
}
fun main() = runBlocking {
    val scope = CoroutineScope(newSingleThreadContext("myThread") + CoroutineName("myCoroutine") + exceptionHandler)
    scope.launch {
        something()
    }.join()
}