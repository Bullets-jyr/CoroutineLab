package ch05

import kotlinx.coroutines.*

fun main() = runBlocking{
    val job = launch {
        withContext(NonCancellable){
            repeat(3){
                delay(300)
                println("job $it")
            }
        }
    }
    delay(200)
    job.cancelAndJoin()

    val job2 = launch {
        withTimeout(1300){
            repeat(100){
                println("job2.. $it")
                delay(500)
            }
        }
    }
    job2.join()
}