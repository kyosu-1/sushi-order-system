package com.example.sushiordersystem.repository

import com.example.sushiordersystem.domain.Customer

interface CustomerRepository {
    fun isValidTable(tableId: String): Boolean
    fun createCustomer(customer: Customer): Int
}
