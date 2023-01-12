package com.example.checklistbygleb.domain

class GetCheckItemUseCase(private val repository: CheckListRepository) {

    fun getCheckItem(id: Int): CheckItem {
        return repository.getCheckItem(id)
    }
}