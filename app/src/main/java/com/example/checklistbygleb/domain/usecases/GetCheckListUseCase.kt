package com.example.checklistbygleb.domain.usecases

import androidx.lifecycle.LiveData
import com.example.checklistbygleb.domain.entity.CheckItem
import com.example.checklistbygleb.domain.repository.CheckListRepository
import javax.inject.Inject

class GetCheckListUseCase @Inject constructor(private val repository: CheckListRepository) {

    fun getCheckList(): LiveData<List<CheckItem>> {
        return repository.getCheckList()
    }
}