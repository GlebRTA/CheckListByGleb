package com.example.checklistbygleb.presentation

import androidx.lifecycle.ViewModel
import com.example.checklistbygleb.data.CheckListRepositoryImpl
import com.example.checklistbygleb.domain.CheckItem
import com.example.checklistbygleb.domain.DeleteCheckItemUseCase
import com.example.checklistbygleb.domain.EditCheckItemUseCase
import com.example.checklistbygleb.domain.GetCheckListUseCase

class MainViewModel : ViewModel() {
    private val repository = CheckListRepositoryImpl

    private val getCheckListUseCase = GetCheckListUseCase(repository)
    private val deleteCheckItemUseCase = DeleteCheckItemUseCase(repository)
    private val editCheckItemUseCase = EditCheckItemUseCase(repository)

    val checkList = getCheckListUseCase.getCheckList()

    fun deleteCheckItem(item: CheckItem) {
        deleteCheckItemUseCase.deleteCheckItem(item)
    }

    fun changeEnableState(item: CheckItem) {
        val newItem = item.copy(enabled = !item.enabled)
        editCheckItemUseCase.editCheckItem(newItem)
    }
}