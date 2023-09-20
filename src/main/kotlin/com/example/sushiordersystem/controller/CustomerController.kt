package com.example.sushiordersystem.controller

import com.example.sushiordersystem.service.CustomerService
import com.example.sushiordersystem.service.TableService
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


data class CreateCustomerRequest(
        val tableId: String,
)

data class CreateCustomerResponse(
        val customerId: String,
        val checkedInAt: Long,
)

@RestController
@RequestMapping("/customers")
class CustomerController(
        private val customerService: CustomerService,
        private val tableService: TableService,
) {
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
        // テーブルが存在しない場合
        if (!tableService.doesTableExist(request.tableId)) {
            return ResponseEntity.badRequest().body(
                    ErrorResponse(
                            message = "Invalid table_id",
                    )
            )
        }
        // テーブルが利用中の場合
        if (!tableService.isTableAvailable(request.tableId)) {
            return ResponseEntity.badRequest().body(
                    ErrorResponse(
                            message = "Table is not available",
                    )
            )
        }
        val customer = customerService.createCustomer(request.tableId)
        return ResponseEntity.status(201).body(
                CreateCustomerResponse(
                        customerId = customer.id,
                        checkedInAt = customer.checkedInAt.toEpochMilli(),
                )
        )
    }
}
