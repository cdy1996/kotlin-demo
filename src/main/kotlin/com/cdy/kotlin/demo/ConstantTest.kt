package com.cdy.kotlin.demo

fun main(args: Array<String>) {
    println("main")
    val bb = BB
    val aa = AA()
    aa.tes()
    val a = AA.a
    a.tes()
}


const val a = 0

//既是类 也是 一个单例对象
object BB  {
    const val b=0
}

class AA{
    companion object {
        const val aa=0
         val a=AA()
    }
    fun tes(){}

}
