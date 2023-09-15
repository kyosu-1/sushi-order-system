package com.example.sushiordersystem.service

import java.time.Instant
import java.util.UUID

import org.springframework.stereotype.Service

import com.example.sushiordersystem.domain.Customer
import com.example.sushiordersystem.repository.CustomerRepository


@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    fun createCustomer(tableId: UUID): Customer {
        val customer = Customer(
            customerId = UUID.randomUUID(),
            tableId = tableId,
            checkedInAt = Instant.now(),
        )
        customerRepository.insert(customer)
        return customer
    }
}
