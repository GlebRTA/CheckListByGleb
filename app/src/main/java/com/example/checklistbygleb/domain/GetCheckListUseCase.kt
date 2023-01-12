package com.example.checklistbygleb.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GetCheckListUseCase(private val repository: CheckListRepository) {

    fun getCheckList(): LiveData<List<CheckItem>> {
        return repository.getCheckList()
    }
}