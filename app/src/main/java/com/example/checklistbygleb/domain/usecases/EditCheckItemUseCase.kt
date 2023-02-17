package com.example.checklistbygleb.domain.usecases

import com.example.checklistbygleb.domain.entity.CheckItem
import com.example.checklistbygleb.domain.repository.CheckListRepository
import javax.inject.Inject

class EditCheckItemUseCase @Inject constructor(private val repository: CheckListRepository) {

    suspend fun editCheckItem(item: CheckItem) {
        repository.editCheckItem(item)
    }
}