package ch02

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main step 1...")
    val job: Job = launch {
        println("coroutine step 1...")
        delay(300)
        println("coroutine step 2...")
    }
//    job.join()
    job.cancel()
    println("main step 2...")
}