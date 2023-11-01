package ch07.test1

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun something(): Flow<Int> = flow {
    repeat(2) {
        delay(100)
        println("emit : $it")
        // 발행
        emit(it)
    }
}
fun main() = runBlocking {
    // Cold Stream
    val myFlow = something()
    println("main step 1")
    // launch {} 비동기로 만들기 위해서 사용
    launch {
        // Flow 실행
        // blocking
        // 수신
        myFlow.collect {
            println("collect 1 : $it")
        }
    }
    println("main step 2")
    // launch {} 비동기로 만들기 위해서 사용
    launch {
        // Flow 실행
        // blocking
        // 수신
        // 수신자가 또 나타나면 Flow는 다시 실행됩니다.
        myFlow.collect {
            println("collect 2 : $it")
        }
    }
    println("main step3")
    delay(2000)
    println("main end")
}