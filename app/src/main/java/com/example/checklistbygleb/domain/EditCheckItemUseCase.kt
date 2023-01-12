package com.example.checklistbygleb.domain

class EditCheckItemUseCase(private val repository: CheckListRepository) {

    fun editCheckItem(item: CheckItem) {
        repository.editCheckItem(item)
    }
}