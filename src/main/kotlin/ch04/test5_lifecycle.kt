package ch04

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    val job = launch(start = CoroutineStart.LAZY) {
        println("coroutine 1..")
        delay(500)
        println("coroutine 2..")
    }
    println("main step 1 : isActive : ${job.isActive}, isCancelled : ${job.isCancelled}, isCompleted : ${job.isCompleted}")
    delay(200)
//    job.start()대신 job.join()가능
    job.join()
    println("main step 2 : isActive : ${job.isActive}, isCancelled : ${job.isCancelled}, isCompleted : ${job.isCompleted}")
}