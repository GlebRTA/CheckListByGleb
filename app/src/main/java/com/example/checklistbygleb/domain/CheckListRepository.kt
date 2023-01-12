package com.example.checklistbygleb.domain

interface CheckListRepository {

    fun addCheckItem(item: CheckItem)

    fun deleteCheckItem(item: CheckItem)

    fun editCheckItem(item: CheckItem)

    fun getCheckItem(id: Int): CheckItem

    fun getCheckList(): List<CheckItem>
}