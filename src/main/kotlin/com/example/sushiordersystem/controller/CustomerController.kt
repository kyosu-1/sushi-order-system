package com.example.sushiordersystem.controller

import java.util.UUID

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

import com.example.sushiordersystem.service.CustomerService

data class CreateCustomerRequest(
    val tableId: UUID,
)

data class CreateCustomerResponse(
    val customerId: UUID,
    val checkedInAt: Long,
)

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    @PostMapping
    fun createCustomer(@RequestBody request: CreateCustomerRequest): CreateCustomerResponse {
        val customer = customerService.createCustomer(request.tableId)
        return CreateCustomerResponse(
            customerId = customer.customerId,
            checkedInAt = customer.checkedInAt.toEpochMilli(),
        )
    }
}
