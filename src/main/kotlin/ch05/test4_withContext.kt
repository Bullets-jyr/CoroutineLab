package ch05.test4

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("exception handler : ${throwable.message}")
}

suspend fun something() {
    println("thread name : ${Thread.currentThread().name}, ${coroutineContext[CoroutineName]}")
    withContext(Dispatchers.IO){
        println("thread name : ${Thread.currentThread().name}, ${kotlin.coroutines.coroutineContext[CoroutineName]}")
        repeat(3){
            delay(300)
            println("job $it")
        }
    }
}

fun main() = runBlocking {
    val scope = CoroutineScope(newSingleThreadContext("myThread") + CoroutineName("myCoroutine") + exceptionHandler)
    scope.launch {
        something()
    }.join()
}