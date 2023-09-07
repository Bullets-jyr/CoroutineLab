package ch04

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    println("main step 1...")
    runBlocking {
        repeat(2){
            delay(200)
            println("coroutine $it")
        }
    }
    println("main step 2...")

    runBlocking(Dispatchers.IO) {
        repeat(2){
            delay(200)
            println("coroutine $it : ${Thread.currentThread().name}")
        }
    }

    println("main step 3...")
}