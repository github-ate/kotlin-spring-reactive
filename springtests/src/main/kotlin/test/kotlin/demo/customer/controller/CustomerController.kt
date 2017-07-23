package test.kotlin.demo.customer.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import test.kotlin.demo.customer.repository.CustomerRepository

@RestController
class CustomerController(val repository: CustomerRepository) {
    @GetMapping("/customer")
    fun findAll() = repository.findAll()

    @GetMapping("/customer/{name}")
    fun findByLastName(@PathVariable name: String) = repository.findByLastName(name)
}