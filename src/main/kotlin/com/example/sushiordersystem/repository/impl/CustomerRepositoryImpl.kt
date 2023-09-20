package com.example.sushiordersystem.repository.impl

import com.example.sushiordersystem.domain.Customer
import com.example.sushiordersystem.mapper.CustomerMapper
import com.example.sushiordersystem.repository.CustomerRepository
import com.example.sushiordersystem.mapper.CustomerEntity
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryImpl(private val customerMapper: CustomerMapper) : CustomerRepository {
    override fun isValidTable(tableId: String): Boolean {
        return customerMapper.isValidTable(tableId)
    }

    override fun createCustomer(customer: Customer): Int {
        val customerEntity = CustomerEntity(
                id = customer.customerId,
                tableId = customer.tableId,
                checkedInAt = customer.checkedInAt
        )
        return customerMapper.insertCustomer(customerEntity)
    }
}
