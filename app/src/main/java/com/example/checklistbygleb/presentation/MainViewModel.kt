package com.example.checklistbygleb.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checklistbygleb.data.CheckListRepositoryImpl
import com.example.checklistbygleb.domain.CheckItem
import com.example.checklistbygleb.domain.DeleteCheckItemUseCase
import com.example.checklistbygleb.domain.EditCheckItemUseCase
import com.example.checklistbygleb.domain.GetCheckListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CheckListRepositoryImpl(application)

    private val getCheckListUseCase = GetCheckListUseCase(repository)
    private val deleteCheckItemUseCase = DeleteCheckItemUseCase(repository)
    private val editCheckItemUseCase = EditCheckItemUseCase(repository)

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