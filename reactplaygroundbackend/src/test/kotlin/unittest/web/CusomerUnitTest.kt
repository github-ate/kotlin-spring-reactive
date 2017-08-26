package unittest.web

import entity.Customer
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import repository.CustomerRepository
import test.kotlin.demo.customer.controller.CustomerController


@RunWith(MockitoJUnitRunner::class)
class CustomerUnitTest {
    @Mock
    lateinit var customerRepository: CustomerRepository

    @InjectMocks
    lateinit var customerController: CustomerController

    @Test
    fun `Test find all customers`() {
        val customer = Customer("Test1", "Test1")
        val customer2 = Customer("Test2", "Test2")
        Mockito.doReturn(Flux.just(customer, customer2)).`when`(customerRepository).findAll()

        StepVerifier.create(customerController.findAll())
                .expectNextMatches({ it.firstName == customer.firstName })
                .expectNextMatches({ it.firstName == customer2.firstName })
                .expectComplete()
                .verify()
    }
}