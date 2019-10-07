package com.cdy.kotlin.demo


val runable = Runnable { println("runable") }

fun main(args: Array<String>) {
    test1 {
        println("hello")
        return@test1 //crossinline 内联 中断自身,但不中断外部的函数执行
//        return //通过内联中断, 外部函数也会被中断
    }
    println("hello")

    test2({
        println("hello")
    }, runable::run)

    val test1 = fun() { // 匿名函数 只能退出当前的
        println("test1")
        return
    }
    fun test2() { // 嵌套函数
        println("test1")
        return
    }
    test1()

}

inline fun test1(crossinline l: () -> Unit) {
    l.invoke()
    return
}

inline fun test2(l0: () -> Unit, noinline l1: () -> Unit): () -> Unit {
    l0.invoke()
    l1.invoke()
    println("test2")
    return l1
}
