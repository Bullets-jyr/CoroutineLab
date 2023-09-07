package ch04.test8

import kotlinx.coroutines.*
import java.lang.Exception

val exceptionHandler = CoroutineExceptionHandler {_, throwable: Throwable ->
    println("job exception .. ${throwable.message}")
}
suspend fun something() = GlobalScope.launch(exceptionHandler) {
    println("coroutine start...")
    delay(200)
    throw Exception("coroutine Exception..")
}
fun main() = runBlocking{
    val job = something()
    job.join()
    println("main end : isActive : ${job.isActive}, isCancelled : ${job.isCancelled}, isCompleted : ${job.isCompleted}")
}