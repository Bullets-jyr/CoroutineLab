package ch04

import kotlinx.coroutines.*

val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("exceptionHandler ${throwable.message}")
}

suspend fun something1() = GlobalScope.launch(exceptionHandler) {
    println("job start")
    delay(500)
    val no = (0..10).random()
    println("no : $no")
    if(no % 2 == 0) {
        throw Exception("Job Even Exception")
    } else {
        throw Exception("Job Odd Exception")
    }
}

suspend fun something2() = GlobalScope.async(exceptionHandler) {
    println("deferred start")
    delay(500)
    val no = (0..10).random()
    println("no : $no")
    if(no % 2 == 0) {
        throw Exception("Deferred Even Exception")
    } else {
        throw Exception("Deferred Odd Exception")
    }
}

fun main() = runBlocking{
    val job = something1()
//    delay(1000)
    job.join()
    val deferred = something2()
//    val result = deferred.await()
//    println("result : $result")
//    delay(1000)
    GlobalScope.launch(exceptionHandler) {
        deferred.await()
    }.join()
}