package com.example.sushiordersystem.domain

import java.time.Instant

data class Customer(
        val id: String,
        val tableId: String,
        val checkedInAt: Instant,
        val checkedOutAt: Instant? = null,
)
