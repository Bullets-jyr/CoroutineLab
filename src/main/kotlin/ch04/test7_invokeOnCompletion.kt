package ch04

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

suspend fun something() = GlobalScope.launch {
    println("coroutine start...")
    delay(200)
//    println("coroutine end..")
    throw Exception("coroutine Exception..")
}
fun main() = runBlocking{
    val job = something()
    job.invokeOnCompletion { e ->
        e?.let {
            println("exception : ${e.message}")
        } ?: let {
            println("coroutine completed...")
        }
    }
    job.join()
    println("main end : isActive : ${job.isActive}, isCancelled : ${job.isCancelled}, isCompleted : ${job.isCompleted}")
}