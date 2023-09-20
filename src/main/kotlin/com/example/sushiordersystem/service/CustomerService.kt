package com.example.sushiordersystem.service

import java.time.Instant
import java.util.UUID

import org.springframework.stereotype.Service

import com.example.sushiordersystem.domain.Customer
import com.example.sushiordersystem.repository.CustomerRepository

@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    fun createCustomer(tableId: String): Customer {
        if (!customerRepository.isValidTable(tableId)) {
            throw TableNotFoundException()
        }
        val customer = Customer(
                customerId = UUID.randomUUID().toString(),
                tableId = tableId,
                checkedInAt = Instant.now(),
        )
        customerRepository.createCustomer(customer)
        return customer
    }
}
