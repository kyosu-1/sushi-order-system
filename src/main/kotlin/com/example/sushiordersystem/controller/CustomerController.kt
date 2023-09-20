package com.example.sushiordersystem.controller

import com.example.sushiordersystem.service.CustomerService
import com.example.sushiordersystem.service.TableNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema


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

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "201", description = "Created",
                        content = [Content(schema = Schema(implementation = CreateCustomerResponse::class))]),
                ApiResponse(responseCode = "400", description = "Bad Request",
                        content = [Content(schema = Schema(implementation = ErrorResponse::class))])
            ]
    )
    @PostMapping
    fun createCustomer(@RequestBody request: CreateCustomerRequest): ResponseEntity<Any> {
        return try {
            val customer = customerService.createCustomer(request.tableId)
            ResponseEntity.status(201).body(
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
