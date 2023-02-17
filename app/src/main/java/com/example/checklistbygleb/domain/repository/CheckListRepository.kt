package com.example.checklistbygleb.domain.repository

import androidx.lifecycle.LiveData
import com.example.checklistbygleb.domain.entity.CheckItem

interface CheckListRepository {

    suspend fun addCheckItem(item: CheckItem)

    suspend fun deleteCheckItem(item: CheckItem)

    suspend fun editCheckItem(item: CheckItem)

    suspend fun getCheckItem(id: Int): CheckItem

    fun getCheckList(): LiveData<List<CheckItem>>
}