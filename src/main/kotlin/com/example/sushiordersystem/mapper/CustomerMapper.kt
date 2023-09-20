package com.example.sushiordersystem.mapper

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component
import java.time.Instant

data class CustomerEntity(
        val id: String,
        val tableId: String,
        val checkedInAt: Instant
)

@Mapper
@Component
interface CustomerMapper {

    @Insert("""
        INSERT INTO customers (id, table_id, checked_in_at)
        VALUES (#{id}, #{tableId}, #{checkedInAt})
    """)
    fun insertCustomer(customer: CustomerEntity): Int
}
