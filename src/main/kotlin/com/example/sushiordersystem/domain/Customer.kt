package com.example.sushiordersystem.domain

import java.util.UUID
import java.time.Instant

data class Customer(
    val customerId: UUID,
    val tableId: UUID,
    val checkedInAt: Instant
)
