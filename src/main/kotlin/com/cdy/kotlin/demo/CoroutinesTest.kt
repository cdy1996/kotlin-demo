package com.cdy.kotlin.demo

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) = runBlocking<Unit> {
    val job = launch {
        repeat(1000) {i->
            println("挂起 $i")
            delay(500L)
        }
    }


    val job2 = async {
        delay(500L)
        return@async "hello"
    }

    println("job2 "+job2.await())
    println("主线程开始等待")
    delay(1300L)
    println("主线程等待")
    job.cancel()
    job.join()
    println("main: 即将完成退出")

}