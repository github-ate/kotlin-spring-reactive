package repository

import entity.Customer
import event.event.CustomerCreatedEvent
import event.producer.EventProducer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository


@Repository("extendedCustomerRepository")
class ExtendedCustomerRepository(@Qualifier("userRepository") val customerRepository: CustomerRepository, val eventProducer: EventProducer) {
    @Override
    fun save(customer: Customer) {
        customerRepository.save(customer)
        eventProducer.publishCustomerCreatedEvent(CustomerCreatedEvent(customer.id))
    }
}