package com.example.checklistbygleb.domain

import androidx.lifecycle.LiveData

interface CheckListRepository {

    fun addCheckItem(item: CheckItem)

    fun deleteCheckItem(item: CheckItem)

    fun editCheckItem(item: CheckItem)

    fun getCheckItem(id: Int): CheckItem

    fun getCheckList(): LiveData<List<CheckItem>>
}