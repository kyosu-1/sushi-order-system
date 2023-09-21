package com.example.sushiordersystem.mapper

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Update
import org.springframework.stereotype.Component
import java.time.Instant

data class CustomerEntity(
        val id: String,
        val tableId: String,
        val checkedInAt: Instant,
        val checkedOutAt: Instant? = null,
)

@Mapper
@Component
interface CustomerMapper {

    @Insert("""
        INSERT INTO customers (id, table_id, checked_in_at)
        VALUES (#{id}, #{tableId}, #{checkedInAt})
    """)
    fun insertCustomer(customer: CustomerEntity): Int

    @Select("""
        SELECT * FROM customers
        WHERE table_id = #{tableId}
    """)
    fun selectCustomersByTableId(tableId: String): List<CustomerEntity>

    @Update("""
        UPDATE customers
        SET checked_out_at = #{checkedOutAt}
        WHERE id = #{id}
    """)
    fun updateCustomer(customer: CustomerEntity): Int
}
