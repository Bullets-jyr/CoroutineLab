package ch01

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val count = 10_000
    var time = measureTimeMillis {
        val threadJobs = List<Thread>(count) {
            thread {
                Thread.sleep(1000)
                print(".")
            }
        }
        threadJobs.forEach { it.join() }
    }
    println()
    println("thread $count, total time : $time")

    time = measureTimeMillis {
        val coroutineJobs = List(count) {
            launch {
                delay(1000)
                print(".")
            }
        }
        coroutineJobs.forEach { it.join() }
    }
    println()
    println("coroutine $count, total time : $time")
}