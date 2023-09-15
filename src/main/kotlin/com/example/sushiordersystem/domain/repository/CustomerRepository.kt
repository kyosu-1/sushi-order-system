package com.example.sushiordersystem.domain.repository

import com.example.sushiordersystem.domain.model.Customer
import java.util.UUID

interface CustomerRepository {

    fun createCustomer(tableId: UUID): Customer
}
