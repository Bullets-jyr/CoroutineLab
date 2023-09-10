package ch04.test10

import kotlinx.coroutines.*

fun something(): Deferred<String> {
    val deferred = CompletableDeferred<String>()

    GlobalScope.launch {
        delay(500)
        deferred.complete("hello world")
    }

    return deferred
}

fun main() = runBlocking{
    println("main step 1")
    val deferred = something()
    println("main step 2")
    val result = deferred.await()
    println("result : $result")
}