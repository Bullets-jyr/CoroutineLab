package ch04

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    val job = Job()
    launch(job) {
        repeat(5){
            delay(200)
            println("job1.. $it")
        }
    }
    delay(500)
    var isCompleted = job.complete()
    println("main step 1 ... $isCompleted")

    launch(job) {
        repeat(5){
            delay(200)
            println("job1.. $it")
        }
    }
    isCompleted = job.complete()
    println("main step 2 ... $isCompleted")
}