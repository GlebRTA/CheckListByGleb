package com.example.checklistbygleb.domain.usecases

import com.example.checklistbygleb.domain.entity.CheckItem
import com.example.checklistbygleb.domain.repository.CheckListRepository

class GetCheckItemUseCase(private val repository: CheckListRepository) {

    suspend fun getCheckItem(id: Int): CheckItem {
        return repository.getCheckItem(id)
    }
}