package com.joshua.interviewanswers

import org.junit.Assert.assertEquals
import org.junit.Test

class BinarySearchTree {

    data class Node(val value:Int, var lessThan: Node?=null, var greaterThan: Node?=null)

    @Test
    fun flattenTree() {
        val list = mutableListOf<Int>()
        val tree = Node(8)
        tree.lessThan = Node(3).apply {
            lessThan = Node(1)
            greaterThan = Node(6, Node(4), Node(7))
        }
        tree.greaterThan = Node(10).apply {
            greaterThan = Node(14, Node(13))
        }

        go(tree, list)

        assertEquals(1, list.get(0))
        assertEquals(3, list.get(1))
        assertEquals(4, list.get(2))
        assertEquals(6, list.get(3))
        assertEquals(7, list.get(4))
        assertEquals(8, list.get(5))
        assertEquals(10, list.get(6))
        assertEquals(13, list.get(7))
        assertEquals(14, list.get(8))
    }

    /**
     * This call:
     *   1. adds the left side,
     *   2. then adds the current value,
     *   3. and then adds the right side
     * all of which can recursively call itself back down.
     */
    private fun go(node: Node, list: MutableList<Int>) {
        // search all the way down to the left most node possible
        node.lessThan?.let {
            go(it, list)
        }

        // add the value of the current node to the end of the list
        list.add(node.value)

        // search the right side of the left most side possible
        node.greaterThan?.let {
            go(it, list)
        }
    }
}