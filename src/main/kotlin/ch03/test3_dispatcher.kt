package ch03.test3

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main start.. : ${Thread.currentThread().name}")
    val job = launch {
        println("coroutine start... ${Thread.currentThread().name}")
        delay(300)
    }
    job.join()
}