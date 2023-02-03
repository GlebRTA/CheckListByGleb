package com.example.checklistbygleb.domain

class AddCheckItemUseCase(private val repository: CheckListRepository) {

    suspend fun addCheckItem(item: CheckItem) {
        repository.addCheckItem(item)
    }
}