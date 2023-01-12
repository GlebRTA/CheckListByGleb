package com.example.checklistbygleb.domain

import com.example.checklistbygleb.data.CheckListRepositoryImpl.getCheckList

class DeleteCheckItemUseCase(private val repository: CheckListRepository) {

    fun deleteCheckItem(item: CheckItem) {
        repository.deleteCheckItem(item)
        GetCheckListUseCase(repository).getCheckList()
    }
}