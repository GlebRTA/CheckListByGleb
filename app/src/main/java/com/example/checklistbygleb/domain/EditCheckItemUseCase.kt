package com.example.checklistbygleb.domain

class EditCheckItemUseCase(private val repository: CheckListRepository) {

    suspend fun editCheckItem(item: CheckItem) {
        repository.editCheckItem(item)
    }
}