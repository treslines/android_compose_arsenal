package br.com.progdeelite.compose

import org.junit.Test

import org.junit.Assert.*
import kotlin.math.abs

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        var result: IntArray = intArrayOf()
        val target = 9
        val nums = arrayListOf(2,7,11,15)
        var arrayMap = mutableMapOf<Int,Int>()

        nums.forEachIndexed { i, el ->
            arrayMap[el] = i // 0, 2
            if(i+1 < nums.size -1 ) {
                val lookUpKey = target - nums[i+1]
                if(arrayMap.containsKey(lookUpKey)) {
                    result = intArrayOf(arrayMap.getValue(lookUpKey), i+1)
                }
            }
        }
        assertEquals("0,1", "${result[0]},${result[1]}")
    }
}