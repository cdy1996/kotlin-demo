package com.cdy.kotlin.demo

//运算符重载

fun main(args: Array<String>) {

    val list: List<Int> = listOf(1, 2, 3, 4, 5)
    list.convert { it + 1 } //自定义操作符
            .forEach {
                print(it)
            }


    "3123".test("Ewqe")

    //运算符重载 和 中缀表达式
    for (i in 1..100 step 20) {
        print("$i ")
    }
    for (i in 1.rangeTo(100) step 20) {
        print("$i ")
    }


    println(5 vs 6) //中缀表达式


}

infix fun Int.vs(num: Int): CompareResult =
        if (this - num > 0) {
            CompareResult.MORE
        } else if (this - num < 0) {
            CompareResult.LESS
        } else {
            CompareResult.EQUAL
        }

sealed class CompareResult {
    object LESS : CompareResult() {
        override fun toString(): String {
            return "小于"
        }
    }

    object MORE : CompareResult() {
        override fun toString(): String {
            return "大于"
        }
    }

    object EQUAL : CompareResult() {
        override fun toString(): String {
            return "等于"
        }
    }
}

fun String.test(string: String): String {
    return string + ","

}


inline fun <T, E> Iterable<T>.convert(action: (T) -> E): Iterable<E> {
    val list: MutableList<E> = mutableListOf()
    for (item in this)
        list.add(action(item))
    return list

}


data class User1(var name: String)


fun domainTest() {
    //let 和run 会返回闭包的结果
    val user = User1("cdy")
    // let有this参数
    val let = user.let { user -> "let::${user.javaClass}" }
    println(let)
    // run 没有this参数, 直接可以调用this
    val run = user.run { "run::${this.javaClass}" }
    println(run)

    // also 和apply继续返回user对象
    user.also {
        println("also::${it.javaClass}")
    }.apply {
        println("apply${this.javaClass}")
    }

    //1.2新增的作用域函数
    user.takeIf {
        it.name.length > 0 }
            ?.also {
                println("姓名${it.name}") }
            ?: println("姓名为空")
    user.takeUnless {  it.name.length > 0 }?.also { println("姓名为空") } ?: println("姓名${user.name}")


    with(user) {
        this.name = "with"
    }

    `1231`()
    ` `()

    var a:ArrayList<String>
}

//这样 java就不可以调用了
fun `1231`(){
    println("1234")
}
fun ` `(){
    println(" ")
}

// 类型连接  别称
public typealias ArrayList<E> = java.util.ArrayList<E>