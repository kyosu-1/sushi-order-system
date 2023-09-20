package com.example.sushiordersystem.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Component

data class TableEntity (
    val id: String,
    val name: String,
)

@Mapper
@Component
interface TableMapper {

    @Select("""
        SELECT * FROM tables
        WHERE id = #{tableId}
    """)
    fun selectTableById(tableId: String): TableEntity?
}
