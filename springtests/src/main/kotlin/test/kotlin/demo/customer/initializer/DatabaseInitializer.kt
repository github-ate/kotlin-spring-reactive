package test.kotlin.demo.customer.initializer

import org.springframework.stereotype.Component
import test.kotlin.demo.customer.repository.CustomerRepository
import javax.annotation.PostConstruct

@Component
class DatabaseInitializer(val customerRepository: CustomerRepository) {
    @PostConstruct
    fun init() {
        customerRepository.initData()
    }
}