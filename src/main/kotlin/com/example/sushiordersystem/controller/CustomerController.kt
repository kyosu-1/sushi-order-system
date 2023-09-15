package com.example.sushiordersystem.controller

import java.util.UUID
import org.springframework.web.bind.annotation.*

data class CustomerRequest(
    val tableId: UUID,
)

data class CustomerResponse(
    val customerId: UUID,
    val checkedInAt: Long,
)

@RestController
@RequestMapping("/customers")
class CustomerController {
    @PostMapping
    fun createCustomer(@RequestBody request: CustomerRequest): CustomerResponse {
        return CustomerResponse(
            customerId = UUID.randomUUID(),
            checkedInAt = System.currentTimeMillis(),
        )
    }
}
