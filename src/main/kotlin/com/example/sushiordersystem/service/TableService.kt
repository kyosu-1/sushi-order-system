package com.example.sushiordersystem.service

import com.example.sushiordersystem.repository.TableNotFoundException
import com.example.sushiordersystem.repository.TableRepository
import org.springframework.stereotype.Service

@Service
class TableService(private val tableRepository: TableRepository) {

    fun doesTableExist(tableId: String): Boolean {
        return try {
            tableRepository.getTableById(tableId)
            true
        } catch (e: TableNotFoundException) {
            false
        }
    }
}
