package com.example.checklistbygleb.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CheckListDao {

    @Query("SELECT * FROM check_items")
    fun getCheckList(): LiveData<List<CheckItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCheckItem(checkItemDbModel: CheckItemDbModel)

    @Query("DELETE FROM check_items WHERE id=:checkItemId")
    suspend fun deleteCheckItem(checkItemId: Int)

    @Query("SELECT * FROM check_items WHERE id=:checkItemId LIMIT 1")
    suspend fun getCheckItem(checkItemId: Int): CheckItemDbModel
}