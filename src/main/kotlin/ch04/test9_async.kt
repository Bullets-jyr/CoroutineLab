package ch04

import kotlinx.coroutines.*

fun someOne() = GlobalScope.async(Dispatchers.IO) {
    println("coroutine one with async... ")
    delay(300)
    "hello world"
}

fun someTwo() = GlobalScope.async(Dispatchers.Default) {
    println("coroutine two with async... ")
    delay(200)
    10
}

fun main() = runBlocking {
    println("main step 1..")
    val oneDeferred = someOne()
    println("main step 2..")
    val twoDeferred = someTwo()
    println("main step 3..")
    println("result : ${oneDeferred.await()}, ${twoDeferred.await()}")
}