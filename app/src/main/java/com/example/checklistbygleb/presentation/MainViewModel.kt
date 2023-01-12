package com.example.checklistbygleb.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.checklistbygleb.data.CheckListRepositoryImpl
import com.example.checklistbygleb.domain.*

class MainViewModel : ViewModel() {
    private val repository = CheckListRepositoryImpl

    private val getCheckListUseCase = GetCheckListUseCase(repository)
    private val deleteCheckItemUseCase = DeleteCheckItemUseCase(repository)
    private val editCheckItemUseCase = EditCheckItemUseCase(repository)

    val checkList = MutableLiveData<List<CheckItem>>()

    fun getCheckList() {
        checkList.value = getCheckListUseCase.getCheckList()
    }

    fun deleteCheckItem(item: CheckItem) {
        deleteCheckItemUseCase.deleteCheckItem(item)
        getCheckList()
    }

    fun changeEnableState(item: CheckItem) {
        val newItem = item.copy(enabled = !item.enabled)
        editCheckItemUseCase.editCheckItem(newItem)
        getCheckList()
    }
}