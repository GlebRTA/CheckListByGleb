package com.example.checklistbygleb.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checklistbygleb.domain.entity.CheckItem
import com.example.checklistbygleb.domain.repository.CheckListRepository
import com.example.checklistbygleb.domain.usecases.DeleteCheckItemUseCase
import com.example.checklistbygleb.domain.usecases.EditCheckItemUseCase
import com.example.checklistbygleb.domain.usecases.GetCheckListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCheckListUseCase: GetCheckListUseCase,
    private val deleteCheckItemUseCase: DeleteCheckItemUseCase,
    private val editCheckItemUseCase: EditCheckItemUseCase,
) : ViewModel() {

    val checkList = getCheckListUseCase.getCheckList()

    fun deleteCheckItem(item: CheckItem) {
        viewModelScope.launch {
            deleteCheckItemUseCase.deleteCheckItem(item)
        }
    }

    fun changeEnableState(item: CheckItem) {
        viewModelScope.launch {
            val newItem = item.copy(enabled = !item.enabled)
            editCheckItemUseCase.editCheckItem(newItem)
        }
    }
}