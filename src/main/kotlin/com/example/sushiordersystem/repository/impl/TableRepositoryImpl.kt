package com.example.sushiordersystem.repository.impl

import com.example.sushiordersystem.domain.Table
import com.example.sushiordersystem.mapper.TableMapper
import com.example.sushiordersystem.repository.TableNotFoundException
import com.example.sushiordersystem.repository.TableRepository
import org.springframework.stereotype.Repository


@Repository
class TableRepositoryImpl(private val tableMapper: TableMapper) : TableRepository {

    override fun getTableById(tableId: String): Table {
        val tableEntity = tableMapper.selectTableById(tableId) ?: throw TableNotFoundException()
        return Table(
                id = tableEntity.id,
                name = tableEntity.name
        )
    }
}
