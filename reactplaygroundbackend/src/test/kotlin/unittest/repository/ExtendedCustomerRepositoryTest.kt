package unittest.repository

import entity.Customer
import event.event.CustomerCreatedEvent
import event.producer.EventProducer
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import repository.CustomerRepository
import repository.ExtendedCustomerRepository


@RunWith(MockitoJUnitRunner::class)
class ExtendedCustomerRepositoryTest {
    @Mock
    lateinit var customerRepository: CustomerRepository

    @Mock
    lateinit var eventProducer: EventProducer

    @InjectMocks
    lateinit var extendedCustomerRepository: ExtendedCustomerRepository

    @Test
    fun `Save customer with event`() {
        val customer = Customer("Test1", "Test1", 1)

        extendedCustomerRepository.save(customer)

        Mockito.verify(eventProducer).publishCustomerCreatedEvent(CustomerCreatedEvent(customer.id))
        Mockito.verify(customerRepository).save(customer)
    }
}
