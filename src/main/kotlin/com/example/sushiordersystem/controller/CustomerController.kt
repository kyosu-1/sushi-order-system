package com.example.sushiordersystem.controller

import java.util.UUID

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

import com.example.sushiordersystem.service.CustomerService

data class CustomerRequest(
    val tableId: UUID,
)

data class CustomerResponse(
    val customerId: UUID,
    val checkedInAt: Long,
)

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    @PostMapping
    fun createCustomer(@RequestBody request: CustomerRequest): CustomerResponse {
        val customer = customerService.createCustomer(request.tableId)
        return CustomerResponse(
            customerId = customer.customerId,
            checkedInAt = customer.checkedInAt.toEpochMilli(),
        )
    }
}
