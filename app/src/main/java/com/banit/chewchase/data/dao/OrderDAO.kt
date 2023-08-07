package com.banit.chewchase.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.banit.chewchase.data.models.UserOrdersWithFoods

@Dao
interface OrderDAO {

    @Transaction
    @Query("""
        SELECT * FROM order_table WHERE userId = :userId
    """)
    suspend fun getUserOrdersWithFoods(userId: Int): List<UserOrdersWithFoods>

}
