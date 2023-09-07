package ch04

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    //Job
    val job1 = launch {
        repeat(2){
            delay(200)
            println("job1.. $it")
        }
    }
    job1.join()
    println("main step1...")

    val job2 = Job()
    launch(job2) {
        repeat(2){
            delay(200)
            println("job2.. $it")
        }
    }
//    job2.join()
//    job2.complete()
    job2.cancel()
    println("main step2..")
}