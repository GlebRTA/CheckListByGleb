package com.example.checklistbygleb.domain.usecases

import androidx.lifecycle.LiveData
import com.example.checklistbygleb.domain.entity.CheckItem
import com.example.checklistbygleb.domain.repository.CheckListRepository

class GetCheckListUseCase(private val repository: CheckListRepository) {

    fun getCheckList(): LiveData<List<CheckItem>> {
        return repository.getCheckList()
    }
}