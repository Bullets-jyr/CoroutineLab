package ch03

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.system.measureTimeMillis

private suspend fun networkRequest(requestUrl: String): String {

    val url = URL(requestUrl)
    (url.openConnection() as? HttpURLConnection)?.run {
        requestMethod = "GET"
        setRequestProperty("Content-Type", "application/json;utf-8")
        setRequestProperty("Accept","application/json")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val buffer = StringBuffer()
        reader.lines().forEach { buffer.append(it) }
        return buffer.toString()
    }
    return ""
}

fun main() = runBlocking{
    val jobs = mutableListOf<Job>()
    repeat(50){
//        jobs.add(launch(Dispatchers.Default) {//time : 936
        jobs.add(launch(Dispatchers.IO) {//time : 498
            val result = networkRequest("https://reqres.in/api/users/1")
            println(result)
        })
    }
    val time = measureTimeMillis {
        jobs.forEach { it.join() }
    }
    println("time : $time")
}