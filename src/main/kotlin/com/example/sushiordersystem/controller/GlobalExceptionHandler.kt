package com.example.sushiordersystem.controller

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseBody

import org.springframework.web.bind.annotation.ResponseStatus

data class ErrorResponse(
        val message: String,
)

@ControllerAdvice
class GlobalExceptionHandler {

    // 500エラー
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value=[Exception::class])
    @ResponseBody
    fun handleServerError(e: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse(
                        message = "Internal Server Error",
                )
        )
    }
}
