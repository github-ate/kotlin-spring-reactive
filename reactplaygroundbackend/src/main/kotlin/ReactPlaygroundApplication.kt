package test.kotlin.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class ReactPlaygroundApplication

fun main(args: Array<String>) {
    SpringApplication.run(ReactPlaygroundApplication::class.java, *args)
}
