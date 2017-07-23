package test.kotlin.demo.customer.controller

import entity.Customer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import repository.CustomerRepository
import java.net.URI


@RestController
@RequestMapping("/customer")
class CustomerController(val repository: CustomerRepository) {
    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun findAll(): Flux<Customer> = repository.findAll()

    @GetMapping("/{name}")
    fun findByLastName(@PathVariable name: String) = repository.findByLastName(name)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = repository.findById(id)

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun create(@RequestBody customer: Customer): ResponseEntity<String> {
        repository.save(customer)
        val returnVal: ResponseEntity<String> = ResponseEntity.created(URI.create("/customer/${customer.id}")).build()
        return returnVal
    }
}