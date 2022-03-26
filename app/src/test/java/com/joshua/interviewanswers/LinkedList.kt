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
        val reversed = reverseLinkedList(toReverse)
        assertEquals(4, reversed.value)
        assertEquals(3, reversed.next!!.value)
        assertEquals(2, reversed.next!!.next!!.value)
        assertEquals(1, reversed.next!!.next!!.next!!.value)


        // swap 2 items in a linked list
        // 1, 2, 3, 4, 5
        // 1, 4, 3, 2, 5
        val toSwap = DoubleNode.build(5)
        val first = toSwap.first
        val last = toSwap.second
        assertEquals(1, first.value)
        assertEquals(2, first.next!!.value)
        assertEquals(3, first.next!!.next!!.value)
        assertEquals(4, first.next!!.next!!.next!!.value)
        assertEquals(5, first.next!!.next!!.next!!.next!!.value)

        assertEquals(5, last.value)
        assertEquals(4, last.previous!!.value)
        assertEquals(3, last.previous!!.previous!!.value)
        assertEquals(2, last.previous!!.previous!!.previous!!.value)
        assertEquals(1, last.previous!!.previous!!.previous!!.previous!!.value)


        swap(first.next!!, first.next!!.next!!.next!!)

        assertEquals(1, first.value)
        assertEquals(4, first.next!!.value)
        assertEquals(3, first.next!!.next!!.value)
        assertEquals(2, first.next!!.next!!.next!!.value)
        assertEquals(5, first.next!!.next!!.next!!.next!!.value)

        assertEquals(5, last.value)
        assertEquals(2, last.previous!!.value)
        assertEquals(3, last.previous!!.previous!!.value)
        assertEquals(4, last.previous!!.previous!!.previous!!.value)
        assertEquals(1, last.previous!!.previous!!.previous!!.previous!!.value)
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


    private fun linearRecursiveSearch(target: Int, node: Node): Int {
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

    private fun swap(a /*2*/: DoubleNode, b/*4*/: DoubleNode) {
        val oldAPrevious = a.previous
        val oldANext = a.next

        a.previous?.next = b
        a.next?.previous = b
        a.previous = b.previous
        a.next = b.next

        b.previous?.next = a
        b.next?.previous = a
        b.previous = oldAPrevious
        b.next = oldANext
    }

    data class Node(var value: Int, var next: Node? = null)
    data class DoubleNode(val value: Int, var previous: DoubleNode? = null, var next: DoubleNode? = null) {

        companion object {
            /**
             * @return returns the first and last nodes in the list
             */
            fun build(num: Int): Pair<DoubleNode, DoubleNode> {
                var left = DoubleNode(1)
                val first = left
                (2..num).forEach {
                    left.next = DoubleNode(it).apply {
                        previous = left
                    }
                    left = left.next!!
                }
                return Pair(first, left)
            }
        }
    }

}