package com.joshua.interviewanswers

import org.junit.Test

import org.junit.Assert.*
import kotlin.collections.List

class Graph {

    @Test
    fun searchGraphs() {
        assertEquals(4, 2 + 2)

        val n5 = Node("5", listOf(Node("9"), Node("10")))
        val n6 = Node("6", listOf())
        val n2 = Node("2", listOf(n5, n6))

        val n3 = Node("3", listOf())

        val n7 = Node("7", listOf(Node("11"), Node("12")))
        val n4 = Node("4", listOf(n7, Node("8")))

        val graph = Node("1", listOf(n2, n3, n4))

        breadthFirstSearch(listOf(graph))
        depthFirstSearch(graph)
    }

    private fun breadthFirstSearch(nodes: List<Node>) {
        val children = mutableListOf<Node>()
        nodes.forEach { node ->
            println(node.v)
            node.children?.let { it ->
                children.addAll(it)
            }
        }
        if (children.isNotEmpty()) breadthFirstSearch(children)
    }

    private fun depthFirstSearch(node: Node) {
        println(node.v)
        node.children?.forEach {
            depthFirstSearch(it)
        }
    }

    data class Node(val v: String, val children: List<Node>?=null)

}