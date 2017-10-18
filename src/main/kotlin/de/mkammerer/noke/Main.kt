package de.mkammerer.noke

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class NokeApplication

fun main(args: Array<String>) {
    SpringApplication.run(NokeApplication::class.java, *args)
}
