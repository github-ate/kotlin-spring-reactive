package repository

import entity.Customer
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : ReactiveCrudRepository<Customer, Long> {
    fun initData() {
        save(Customer("Jack", "Bauer"))
        save(Customer("Chloe", "O'Brian"))
        save(Customer("Kim", "Bauer"))
        save(Customer("David", "Palmer"))
        save(Customer("Michelle", "Dessler"))
    }

    fun findByLastName(name: String): List<Customer>
}