package com.example.sushiordersystem.domain

import java.time.Instant

data class Customer(
    val customerId: String,
    val tableId: String,
    val checkedInAt: Instant
)
