package ch02

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main step 1")
    // coroutine builder... 만들자 마자 코루틴 실행...
    launch {
        println("coroutine start...")
        delay(300)
        println("coroutine end...")
    }
    println("main step 2")
    delay(1000)
    println("main end...")
}