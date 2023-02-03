package com.example.checklistbygleb.domain

class DeleteCheckItemUseCase(private val repository: CheckListRepository) {

    suspend fun deleteCheckItem(item: CheckItem) {
        repository.deleteCheckItem(item)
        GetCheckListUseCase(repository).getCheckList()
    }
}