package com.example.sushiordersystem.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

import com.example.sushiordersystem.service.CustomerService
import com.example.sushiordersystem.service.TableNotFoundException
import org.springframework.http.ResponseEntity

data class CreateCustomerRequest(
        val tableId: String,
)

data class CreateCustomerResponse(
        val customerId: String,
        val checkedInAt: Long,
)

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    @PostMapping
    fun createCustomer(@RequestBody request: CreateCustomerRequest): ResponseEntity<Any> {
        return try {
            val customer = customerService.createCustomer(request.tableId)
            ResponseEntity.ok(
                    CreateCustomerResponse(
                            customerId = customer.customerId,
                            checkedInAt = customer.checkedInAt.toEpochMilli(),
                    )
            )
        } catch (e: TableNotFoundException) {
            ResponseEntity.badRequest().body(
                    ErrorResponse(
                            message = "Invalid table_id",
                    )
            )
        }
    }
}
