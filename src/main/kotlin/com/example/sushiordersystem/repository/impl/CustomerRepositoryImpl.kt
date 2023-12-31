package com.example.sushiordersystem.repository.impl

import com.example.sushiordersystem.domain.Customer
import com.example.sushiordersystem.mapper.CustomerEntity
import com.example.sushiordersystem.mapper.CustomerMapper
import com.example.sushiordersystem.repository.CustomerRepository
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryImpl(private val customerMapper: CustomerMapper) : CustomerRepository {

    override fun createCustomer(customer: Customer): Int {
        val customerEntity = CustomerEntity(
                id = customer.id,
                tableId = customer.tableId,
                checkedInAt = customer.checkedInAt
        )
        return customerMapper.insertCustomer(customerEntity)
    }

    override fun getCustomersByTableId(tableId: String): List<Customer> {
        val customerEntities = customerMapper.selectCustomersByTableId(tableId)
        return customerEntities.map { customerEntity ->
            Customer(
                    id = customerEntity.id,
                    tableId = customerEntity.tableId,
                    checkedInAt = customerEntity.checkedInAt
            )
        }
    }
}
