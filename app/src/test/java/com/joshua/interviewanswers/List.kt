package com.joshua.interviewanswers

import junit.framework.Assert.assertEquals
import org.junit.Test
import kotlin.collections.List

class List {

    @Test
    fun testSomething() {
        val set = (1..1000).toList()
        assertEquals(-1, binarySearch(11500, set))
        assertEquals(-1, binarySearch(-10, set))
        assertEquals(0, binarySearch(1, set))
        assertEquals(999, binarySearch(1000, set))
        assertEquals(499, binarySearch(500, set))
        assertEquals(498, binarySearch(499, set))
        assertEquals(500, binarySearch(501, set))
    }

    private fun binarySearch(target: Int, list: List<Int>, min:Int=0, max:Int=list.size-1):Int {
        if (min > max) return -1
        val middle = (max - min) / 2 + min
        val v = list.get(middle)

        if (v == target) return middle
        else if (target > v) {
            return binarySearch(target, list, middle+1, max)
        } else {
            return binarySearch(target, list, min, middle-1)
        }
    }

}