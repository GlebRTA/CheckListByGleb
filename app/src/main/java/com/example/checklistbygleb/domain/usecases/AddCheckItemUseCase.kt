package com.example.checklistbygleb.domain.usecases

import com.example.checklistbygleb.domain.entity.CheckItem
import com.example.checklistbygleb.domain.repository.CheckListRepository

class AddCheckItemUseCase(private val repository: CheckListRepository) {

    suspend fun addCheckItem(item: CheckItem) {
        repository.addCheckItem(item)
    }
}