package com.example.checklistbygleb.domain.usecases

import com.example.checklistbygleb.domain.entity.CheckItem
import com.example.checklistbygleb.domain.repository.CheckListRepository

class DeleteCheckItemUseCase(private val repository: CheckListRepository) {

    suspend fun deleteCheckItem(item: CheckItem) {
        repository.deleteCheckItem(item)
        GetCheckListUseCase(repository).getCheckList()
    }
}