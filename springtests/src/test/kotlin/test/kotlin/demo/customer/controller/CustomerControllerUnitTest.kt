package test.kotlin.demo.customer.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import test.kotlin.demo.customer.entity.Customer
import test.kotlin.demo.customer.repository.CustomerRepository
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class CustomerControllerUnitTest {
    @Mock
    lateinit var customerRepository: CustomerRepository

    @InjectMocks
    lateinit var customerController: CustomerController

    @Test
    fun testFindAll() {
        val customer = Mockito.mock(Customer::class.java)
        val customer2 = Mockito.mock(Customer::class.java)
        Mockito.doReturn(arrayOf(customer, customer2).asIterable()).`when`(customerRepository).findAll()

        val allCustomers = customerController.findAll()

        assertTrue(allCustomers.count() == 2, "All customers")
    }

    @Test
    fun testFindByLastName() {
        val customer = Mockito.mock(Customer::class.java)
        val customer2 = Mockito.mock(Customer::class.java)
        Mockito.doReturn(listOf(customer, customer2)).`when`(customerRepository).findByLastName(Mockito.anyString())

        val allCustomers = customerController.findByLastName("Test")

        assertTrue(allCustomers.count() == 2, "Customers by last name")
    }
}