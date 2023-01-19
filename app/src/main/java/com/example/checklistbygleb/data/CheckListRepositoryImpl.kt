package com.example.checklistbygleb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.checklistbygleb.domain.CheckItem
import com.example.checklistbygleb.domain.CheckListRepository
import kotlin.random.Random

object CheckListRepositoryImpl : CheckListRepository {

    private val checkListLD = MutableLiveData<List<CheckItem>>()
    private val checkList = sortedSetOf<CheckItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    init {
        repeat(5) {
            addCheckItem(CheckItem("Name $it", it, Random.nextBoolean()))
        }
    }
    private var autoIncrementId = 0

    override fun addCheckItem(item: CheckItem) {
        if (item.id == CheckItem.UNDEFINED_ID) {
            item.id = autoIncrementId++
        }
        checkList.add(item)
        updateList()
    }

    override fun deleteCheckItem(item: CheckItem) {
        checkList.remove(item)
        updateList()
    }

    override fun editCheckItem(item: CheckItem) {
        checkList.remove(getCheckItem(item.id))
        addCheckItem(item) //include updateList()
    }

    override fun getCheckItem(id: Int): CheckItem {
        return checkList.find {
            it.id == id
        } ?: throw RuntimeException("Element with $id not found")
    }

    override fun getCheckList(): LiveData<List<CheckItem>> {
        return checkListLD
    }

    private fun updateList() {
        checkListLD.value = checkList.toList()
    }
}