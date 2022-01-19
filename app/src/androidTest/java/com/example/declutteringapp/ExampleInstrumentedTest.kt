package com.example.declutteringapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.declutteringapp", appContext.packageName)
    }
/*    fun lastPageCheck(){

        val questionsViewpager= view.findViewById(R.id.questionsViewpager)
        val result = if (questionsViewpager.adapter == null) {
        // Adapter did not set
        false
    } else {
        // Check if "current position" equal "number of elements
            questionsViewpager.currentItem == questionsViewpager.adapter?.count?.minus(1)
    }}*/
}