package com.example.kafka.config

import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class GenericConsumer() : Consumer<Person> {
    override fun accept(t: Person) {
        TODO("Not yet implemented")
    }
}

data class Person(
    val name: String,
)
