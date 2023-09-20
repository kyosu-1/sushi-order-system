package com.example.sushiordersystem.repository

import com.example.sushiordersystem.domain.Table

interface TableRepository {

    // table取得
    fun getTableById(tableId: String): Table?
}
