package test.kotlin.demo.customer.repository

import org.springframework.data.repository.CrudRepository
import test.kotlin.demo.customer.entity.Customer

interface CustomerRepository : CrudRepository<Customer, Long> {
    fun initData() {
        save(Customer("Jack", "Bauer"))
        save(Customer("Chloe", "O'Brian"))
        save(Customer("Kim", "Bauer"))
        save(Customer("David", "Palmer"))
        save(Customer("Michelle", "Dessler"))
    }

    fun findByLastName(name: String): List<Customer>
}