package org.example

import kotlinx.coroutines.*
import kotlin.concurrent.thread

@OptIn(DelicateCoroutinesApi::class)
fun ex1_code1() {
    GlobalScope.launch { // 전역 스코프
        delay(1000L)
        println("World!")
    }
    println("Hello")
    runBlocking { delay(2000L) }
    // 얘도 launch처럼 빌더인데
    // 자신을 호출한 스레드를 blocking한다는 차이가 있음
    // Thread.sleep(2000L) // main thread block함

    thread {
        Thread.sleep(1000L)
        println("Thread!")
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun ex1_code2() = runBlocking { // runBlocking()
    var job = GlobalScope.launch { // 전역 스코프
        delay(1000L) // 이게 3000이었다면 출력 안 됨
        println("World!")
    }
    println("Hello")
    job.join() // -> job을 통해 해결 -> 여러 개의 작업은 어떻게?
    delay(2000L) // 2초 뒤에 프로그램 끝남
}

fun ex1_code3() = runBlocking { // runBlocking()
    // this.launch
    launch { // 전역 스코프
        delay(1000L) // 이게 3000이었다면 출력 안 됨
        println("World!")
    }
    println("Hello")
}

suspend fun ex1_code4() {
    delay(1000L)
    println("World!")
}

fun main() = runBlocking {
    launch {
        repeat(5) {
            i->
            println("Coroutine A, $i")
            delay(10L)
        }
    }
    launch {
        repeat(5) {
                i->
            println("Coroutine B, $i")
            delay(10L)
        }
    }
    println("Coroutine A and B has started")
}

fun <T>println(msg: T) {
    kotlin.io.println("$msg [${Thread.currentThread().name}]")
}