package unittest.web

import entity.Customer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import repository.CustomerRepository
import test.kotlin.demo.customer.controller.CustomerController

@RunWith(SpringRunner::class)
class CustomerIntegrationTest {
    lateinit var customerRepository: CustomerRepository
    lateinit var client: WebTestClient
    lateinit var customer: Customer
    lateinit var customer2: Customer

    @Before
    fun setup() {
        customer = Customer("Test1", "Test1", 1)
        customer2 = Customer("Test2", "Test2", 2)
        customerRepository = Mockito.mock(CustomerRepository::class.java)
        client = WebTestClient
                .bindToController(CustomerController(customerRepository))
                .build()

        Mockito.doReturn(Flux.just(customer, customer2)).`when`(customerRepository).findAll()
        Mockito.doReturn(Mono.just(customer)).`when`(customerRepository).save(customer)
    }

    @Test
    fun `Find all customers`() {
        val flux = client.get().uri("/customer/")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.TEXT_EVENT_STREAM)
                .returnResult(Customer::class.java)

        StepVerifier.create(flux.responseBody)
                .expectNext(customer, customer2)
                .expectComplete()
                .verify()
    }

    @Test
    fun `Find by id`() {
        /* val flux = client.get().uri("/customer/1")
                 .accept(MediaType.TEXT_EVENT_STREAM)
                 .exchange()
                 .expectStatus().isOk
                 .expectHeader().contentType(MediaType.TEXT_EVENT_STREAM)
                 .returnResult(Customer::class.java)

         this.client
                 .get()
                 .uri("customer")
                 .syncBody(customer)
                 .exchange()
                 .expectStatus().isCreated
                 .expectHeader().valueEquals("location", "/customer/1")
                 .expectBody().isEmpty

         StepVerifier.create(flux.responseBody)
                 .expectNext(customer,customer2)
                 .expectComplete()
                 .verify()*/
    }

    @Test
    fun `Add customer`() {
        this.client
                .post()
                .uri("customer")
                .syncBody(customer)
                .exchange()
                .expectStatus().isCreated
                .expectHeader().valueEquals("location", "/customer/1")
                .expectBody().isEmpty
    }
}