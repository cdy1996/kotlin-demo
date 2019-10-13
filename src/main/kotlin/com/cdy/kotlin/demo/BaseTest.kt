package com.cdy.kotlin.demo

import com.cdy.kotlin.demo.JavaClass.format
import kotlin.reflect.KClass

//不可变的变量
val age: Int = 18
var name: String = "cdy"
// 可变的且可以为空的变量
var name2: String? = null

fun main(args: Array<String>) {
//    name = name2 // Stirng 和String? 不可转换
//    name = name2!! // !!表示肯定不会为空了
//    name2 = name  // 反过来就是可以赋值的

//    printlen(name)

//    testClass(String::class.java)
//    testKtClass(KotlinSpring5Application::class)

//    testStatic("")

//    val age=18
//    val name = "cdy"
//    println("%S %d", name, age)


//    val file: File = File("sdads")
//    file.readText()

    class sdasd(s: String) :BaseTest1

    val s= sdasd("!23")


    val runable = Runnable {
        s.toString()
        println("runnable::run")
    }


    onlyIf(true) {
        s.toString()
        println("打印日志")
    }
    onlyIf2(true) {
        s.toString()
        println("打印日志")
    }


    val function: () -> Unit //函数对象  函数式一等公民
    function = runable::run
    onlyIf(true, function)

    val f = fun () {
        s.toString()
        println("runnable::run")
    }
    onlyIf(true, f)


    val f1 = fun BaseTest1.(i:Boolean) {
        s.toString()
        println("runnable::run")
    }
    onlyIf(true, f1, s)
    onlyIf22(true, s){
        println()
    }
    onlyIf2(true, s){ b: BaseTest1, b1: Boolean ->
        println()
    }

}

object Utils {
    @JvmStatic
    fun sayMessage(msg: String?) {
        println("$msg")
    }
}

fun printlen(str: String): String {
    println("这个字符串是:　$str") //模板赋值
    return str
}

fun testClass(clazz: Class<String>) {
}

fun testKtClass(clazz: KClass<String>) {
}

fun testStatic(str: String) {
    val fmt1 = format(str) //实际为 String! 类型
//    println(fmt1.length) //空指针
//    val fmt2: String = format(str)  //会报错
    val fmt3: String? = format(str)
    println(fmt3?.length)

}

fun testLambda() {
    // 闭包
    val echo = { name: String ->
        println(name)
    }

    echo.invoke("dsad")
    echo("Dsadas")

    val thread = Thread({ -> ("dsad") })
    thread.start()
}

interface BaseTest1

fun onlyIf(isDebug: Boolean, block: BaseTest1.(int :Boolean) -> Unit, base: BaseTest1) {
    if (isDebug) {
        base.block(isDebug)
    }
}
fun onlyIf22(isDebug: Boolean,b:BaseTest1, block: BaseTest1.() -> Unit) {
    if (isDebug) {
        b.block()
    }
}
fun onlyIf2(isDebug: Boolean,b:BaseTest1, block: (bb:BaseTest1,int :Boolean) -> Unit) {
    if (isDebug) {
        block(b, isDebug)

    }
}

fun onlyIf(isDebug: Boolean, block: () -> Unit) {
    if (isDebug) {
        block()
    }
}
fun onlyIf2(isDebug: Boolean, block: (int :Boolean) -> Unit) {
    if (isDebug) {
        block(isDebug)
    }
}