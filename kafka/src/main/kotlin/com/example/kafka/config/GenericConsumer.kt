package com.example.kafka.config

import Person
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class GenericConsumer() : Consumer<Person> {
    override fun accept(t: Person) {
        TODO("Not yet implemented")
    }
}

