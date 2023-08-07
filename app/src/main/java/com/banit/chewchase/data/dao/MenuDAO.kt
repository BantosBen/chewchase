package com.banit.chewchase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.banit.chewchase.data.entity.Menu

@Dao
interface MenuDAO {
    @Query("SELECT * FROM menu_table")
    fun getAllMenuItems(): List<Menu>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Menu>)
}
