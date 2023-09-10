package ch05.test2

import kotlinx.coroutines.*

val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("exception handler : ${throwable.message}")
}

suspend fun something(): Nothing = coroutineScope {
    println("thread name : ${Thread.currentThread().name}, ${coroutineContext[CoroutineName]}")
    delay(200)
    throw Exception("exception")
}
fun main() = runBlocking {
    val scope = CoroutineScope(newSingleThreadContext("myThread") + CoroutineName("myCoroutine") + exceptionHandler)
    scope.launch {
        something()
    }.join()
}