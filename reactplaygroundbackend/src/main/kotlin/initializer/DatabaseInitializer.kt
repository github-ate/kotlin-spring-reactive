package initializer

import org.springframework.stereotype.Component
import repository.CustomerRepository
import javax.annotation.PostConstruct

@Component
class DatabaseInitializer(val customerRepository: CustomerRepository) {
    @PostConstruct
    fun init() {
        customerRepository.initData()
    }
}