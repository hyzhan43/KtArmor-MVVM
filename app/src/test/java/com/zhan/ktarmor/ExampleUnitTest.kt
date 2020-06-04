package com.zhan.ktarmor

import com.zhan.ktwing.ext.logd
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test

/**
 *  author: HyJame
 *  date:   2020-03-15
 *  desc:   TODO
 */
class ExampleUnitTest {



    @Test
    fun test_concurrent_login_request() {
        // prepare

        val oneTaskDuration = 3000

        // test
        GlobalScope.launch {

            val now = System.currentTimeMillis()
            val one = async { oneTask() }
            val two = async { twoTask() }


            val actualTime = System.currentTimeMillis() - now
            println("$actualTime - result = ${one.await()} + ${two.await()}")
            println("实际时间: $actualTime")
        }

        Thread.sleep(5000)
    }

    private suspend fun oneTask(): Int {
        delay(2000)
        return 1
    }

    private suspend fun twoTask(): Int {
        delay(3000)
        return 2
    }
}