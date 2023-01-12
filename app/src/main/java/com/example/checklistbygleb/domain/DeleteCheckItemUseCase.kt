package com.example.checklistbygleb.domain

class DeleteCheckItemUseCase(private val repository: CheckListRepository) {

    fun deleteCheckItem(item: CheckItem) {
        repository.deleteCheckItem(item)
    }
}