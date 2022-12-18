package designpatterns.behavioral

import designpatterns.behavioral.HtmlElement.*

/**
 * The visitor pattern is used to apply on components to extend feature without modifying their actual behaviour.
 */

fun main() {
    val page = Page(
        Container(
            Image,
            Link,
            Image // 3 links
        ),
        Image, // 1 link
        Table,
        Tile,
        Container(
            Image,
            Link,
            Image, // 3 links
            Container(
                Image // 1 link
            )
        )
    )

    println(collectLinks(page))
}

fun collectLinks(page: Page): List<String> {
    val c = LinkCrawler()
    c.visit(page)
    return c.links
}

/**
 * The visitor
 */
class LinkCrawler {
    val _links = mutableListOf<String>()
    val links
        get() = _links.toList()

    fun visit(page: Page) {
        visit(page.elements)
    }

    fun visit(container: Container) {
        visit(container.elements)
    }

    private fun visit(elements: List<HtmlElement>) {
        for (e in elements) {
            when (e) {
                is Container -> visit(e)
                is Image -> _links.add(e.src)
                is Link -> _links.add(e.href)
                else -> {}
            }
        }
    }
}

class Page(val elements: MutableList<HtmlElement> = mutableListOf()) {
    constructor(vararg elements: HtmlElement) : this(mutableListOf()) {
        for (e in elements) {
            this.elements.add(e)
        }
    }
}



sealed class HtmlElement {
    class Container(val elements: MutableList<HtmlElement> = mutableListOf()) : HtmlElement() {
        constructor(vararg elements: HtmlElement) : this(mutableListOf()) {
            for (e in elements) {
                this.elements.add(e)
            }
        }
    }

    object Image : HtmlElement() {
        val src: String
            get() = "http://test.cpom"
    }

    object Link : HtmlElement() {
        val href: String
            get() = "www.google.com"
    }

    object Table : HtmlElement()
    object Tile : HtmlElement()

}