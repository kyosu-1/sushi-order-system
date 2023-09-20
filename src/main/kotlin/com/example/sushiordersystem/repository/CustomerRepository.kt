package com.example.sushiordersystem.repository

import com.example.sushiordersystem.domain.Customer

interface CustomerRepository {
    fun createCustomer(customer: Customer): Int
}
