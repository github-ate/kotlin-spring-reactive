package test.kotlin.demo.greeting.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class GreetingControllerUnitTest {
    @InjectMocks
    lateinit var greetingController: GreetingController

    @Test
    fun testGreeting() {
        var inputName = "TestName"
        val greeting = greetingController.greeting(inputName)

        assertNotNull(greeting, "Not null check")
        assertTrue(greeting.id > 0, "Test id")
        assertEquals("Hello, $inputName", greeting.name, "Name test")
    }
}