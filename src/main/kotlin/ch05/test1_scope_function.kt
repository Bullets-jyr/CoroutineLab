package ch05

import kotlinx.coroutines.*

suspend fun subJob1(): String {
    delay(1000)
    println("subJob1...")
    return "hello"
}
suspend fun subJob2(): String {
    delay(1000)
    println("subJob2...")
    return "world"
}
//suspend fun something() : String {
//    val result1 = subJob1()
//    val result2 = subJob2()
//    return "$result1 $result2"
//}

//suspend fun something() : String {
//    val result1 = GlobalScope.async { subJob1() }
//    val result2 = GlobalScope.async { subJob2() }
//    return "${result1.await()} ${result2.await()}"
//}

//suspend fun something() : String {
//    val scope = CoroutineScope(Dispatchers.Default)
//    val result1 = scope.async { subJob1() }
//    val result2 = scope.async { subJob2() }
//    return "${result1.await()} ${result2.await()}"
//}

//suspend fun something(scope: CoroutineScope) : String {
//    val result1 = scope.async { subJob1() }
//    val result2 = scope.async { subJob2() }
//    return "${result1.await()} ${result2.await()}"
//}

suspend fun something() : String = coroutineScope {
    val result1 = async { subJob1() }
    val result2 = async { subJob2() }
    "${result1.await()} ${result2.await()}"
}

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)
    val job = scope.launch {
//        val result = something(scope)
        val result = something()
        println("result : $result")
    }
//    job.join()
    scope.cancel()
    delay(2000)
}