package com.example.sushiordersystem.service

import com.example.sushiordersystem.domain.Customer
import com.example.sushiordersystem.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class CustomerService(
        private val customerRepository: CustomerRepository,
) {

    fun createCustomer(tableId: String): Customer {
        val customer = Customer(
                id = UUID.randomUUID().toString(),
                tableId = tableId,
                checkedInAt = Instant.now()
        )
        customerRepository.createCustomer(customer)
        return customer
    }
}
