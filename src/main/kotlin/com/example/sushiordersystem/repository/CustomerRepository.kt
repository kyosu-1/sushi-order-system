package com.example.sushiordersystem.repository

import org.apache.ibatis.annotations.Insert
import com.example.sushiordersystem.domain.Customer
import org.apache.ibatis.annotations.Mapper

@Mapper
interface CustomerRepository {

    @Insert("""
        INSERT INTO customer (customer_id, table_id, checked_in_at)
        VALUES (#{customerId}, #{tableId}, #{checkedInAt})
    """)
    fun insert(customer: Customer): Int
}
