package com.joshua.interviewanswers

import junit.framework.Assert.assertEquals
import org.junit.Test

class LinkedList {

    @Test
    fun testSomething() {
        val list = Node(1, Node(2, Node(3, Node(4))))

        assertEquals(0, linearRecursiveSearch(1, list))
        assertEquals(1, linearRecursiveSearch(2, list))
        assertEquals(2, linearRecursiveSearch(3, list))
        assertEquals(3, linearRecursiveSearch(4, list))
        assertEquals(-1, linearRecursiveSearch(5, list))
        assertEquals(-1, linearRecursiveSearch(-1, list))

        assertEquals(0, linearLoopSearch(1, list))
        assertEquals(1, linearLoopSearch(2, list))
        assertEquals(2, linearLoopSearch(3, list))
        assertEquals(3, linearLoopSearch(4, list))
        assertEquals(-1, linearLoopSearch(5, list))
        assertEquals(-1, linearLoopSearch(-1, list))

        val toReverse = Node(1, Node(2, Node(3, Node(4))))
        val versed = reverseLinkedList(toReverse)
        assertEquals(4, versed.value)
        assertEquals(3, versed.next!!.value)
        assertEquals(2, versed.next!!.next!!.value)
        assertEquals(1, versed.next!!.next!!.next!!.value)
    }

    private fun linearLoopSearch(target: Int, node: Node): Int {
        var next: Node? = node
        var index: Int = 0
        while (next != null) {
            if (target == next.value) {
                return index
            }
            index++
            next = next.next
        }
        return -1
    }


    private fun linearRecursiveSearch(target: Int, node: Node):Int {
        if (target == node.value) return 0
        node.next?.let {
            val v = linearRecursiveSearch(target, it)
            return if (v > -1) 1 + v // 1 + 1
            else v // -1
        }
        return -1
    }

    private fun reverseLinkedList(node: Node): Node {
        var n: Node? = node
        var last: Node? = null

        while (n != null) {
            val next = n.next
            n.next = last
            last = n
            n = next
        }
        return last!!
    }

    data class Node(var value:Int, var next: Node?=null)

}