package com.example.checklistbygleb.data

import com.example.checklistbygleb.domain.CheckItem
import com.example.checklistbygleb.domain.CheckListRepository

object CheckListRepositoryImpl : CheckListRepository {
    private val checkList = mutableListOf<CheckItem>()
    init {
        repeat(10) {
            addCheckItem(CheckItem("Name $it", it, true))
        }
    }
    private var autoIncrementId = 0

    override fun addCheckItem(item: CheckItem) {
        if (item.id == CheckItem.UNDEFINED_ID) {
            item.id = autoIncrementId++
        }
        checkList.add(item)
    }

    override fun deleteCheckItem(item: CheckItem) {
        checkList.remove(item)
    }

    override fun editCheckItem(item: CheckItem) {
        checkList.remove(getCheckItem(item.id))
        addCheckItem(item)
    }

    override fun getCheckItem(id: Int): CheckItem {
        return checkList.find {
            it.id == id
        } ?: throw RuntimeException("Element with $id not found")
    }

    override fun getCheckList(): List<CheckItem> {
        return checkList
    }
}