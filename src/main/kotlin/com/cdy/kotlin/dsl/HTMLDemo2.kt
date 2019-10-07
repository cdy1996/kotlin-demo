package com.cdy.kotlin.dsl


/**
 * This is an example of a Type-Safe Groovy-style Builder
 *
 * Builders are good for declaratively describing data in your code.
 * In this example we show how to describe an HTML page in Kotlin.
 *
 * See this page for details:
 *
 * http://kotlinlang.org/docs/reference/type-safe-builders.html
 *
 * https://try.kotlinlang.org/#/Examples/Longer%20examples/Life/Life.kt
 */

fun main(args: Array<String>) {
    val result =
            HTML {
                head {
                    title {
                        "HTML encoding with Kotlin"{}
                    }
                }
                body {
                    h1 {
                        "HTML encoding with Kotlin"{}
                    }
                    p {
                        "this format can be used as an alternative markup to HTML" {}
                    }

                    // an element with attributes and text content
                    a(href = "http://jetbrains.com/kotlin") {
                        "Kotlin"{}
                    }

                    // mixed content
                    p {
                        "This is some"{}
                        B {
                            "mixed"{}
                        }
                        "text. For more see the"{}
                        a(href = "http://jetbrains.com/kotlin") {
                            "Kotlin"{}
                        }
                        "project"{}
                    }
                    p {
                        "some text"{}
                    }

                    // content generated from command-line arguments
                    p {
                        "Command line arguments were:"{}
                        ul { ->
                            for (arg in args)
                                li { arg {} }
                        }
                    }
                }
            }
    println(result)
}


interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

abstract class Tag(val name: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): T {
        tag.init() //等价于  init.invoke(tag)
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, indent + "  ")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String? {
        val builder = StringBuilder()
        for (a in attributes.keys) {
            builder.append(" $a=\"${attributes[a]}\"")
        }
        return builder.toString()
    }


    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

abstract class TagWithText(name: String) : Tag(name) {
//    operator fun String.unaryPlus() {
//        children.add(TextElement(this))
//    }

    val textAttributes = hashMapOf<String, String>()

    operator fun String.invoke() {
        children.add(TextElement(this))
    }

    operator fun String.invoke(block: () -> Unit) {
        block.invoke()
        if (textAttributes.isEmpty()) {
            children.add(TextElement(this))
        } else {
            children.add(FONT(this, textAttributes))
        }
    }

    infix fun String.`=`(value: String) {
        textAttributes.put(this, value)
    }

}

class HTML(init: HTML.() -> Unit) : TagWithText("html") {

    init {
        this.init()
    }

    fun head(init: Head.() -> Unit) {
        initTag(Head(), init)
    }

    // 单行函数表达
    fun body(init: Body.() -> Unit) = initTag(Body(), init)
}

// 直接通过构造函数执行闭包 也可以像原来一样通过函数来启动闭包
//fun html(init: HTML.() -> Unit): HTML {
//    val html = HTML()
//    html.init()
//    return html
//}

class Head : TagWithText("head") {
    fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

class Title : TagWithText("title")

abstract class BodyTag(name: String) : TagWithText(name) {
//    fun b(init: B.() -> Unit) = initTag(B(), init)
    fun p(init: P.() -> Unit) = initTag(P(), init)
    fun h1(init: H1.() -> Unit) = initTag(H1(), init)
    fun ul(init: UL.() -> Unit) = initTag(UL(), init)
    fun a(href: String, init: A.() -> Unit) {
        val a = initTag(A(), init)
        a.href = href
    }
}

class Body : BodyTag("body")
class UL : BodyTag("ul") {
    fun li(init: LI.() -> Unit) = initTag(LI(), init)
}

//class B : BodyTag("b")
class B(init: B.() -> Unit) : TagWithText("b"){
    init {
        this.init()
    }
}
class LI : BodyTag("li")
class P : BodyTag("p")
class H1 : BodyTag("h1")

class A : BodyTag("a") {
    var href: String
        get() = attributes["href"]!!
        set(value) {
            attributes["href"] = value
        }
}


class FONT(text: String, textAttributes: HashMap<String, String>) : BodyTag("front") {
    init {
        attributes.putAll(textAttributes)
        children.add(TextElement(text))
    }
}