package com.example.sushiordersystem.service

import com.example.sushiordersystem.repository.TableNotFoundException
import com.example.sushiordersystem.repository.TableRepository
import com.example.sushiordersystem.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class TableService(
        private val tableRepository: TableRepository,
        private val customerRepository: CustomerRepository
) {

    fun doesTableExist(tableId: String): Boolean {
        return try {
            tableRepository.getTableById(tableId)
            true
        } catch (e: TableNotFoundException) {
            false
        }
    }

    fun isTableAvailable(tableId: String): Boolean {
        // 全ての客のチェックアウト時間がnot nullであることを確認する
        // TODO: 直近の客だけ確認する方が効率が上がって良さそう
        val customers = customerRepository.getCustomersByTableId(tableId)
        return customers.all { it.checkedOutAt != null }
    }
}
