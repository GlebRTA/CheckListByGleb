package com.example.checklistbygleb.domain

class GetCheckListUseCase(private val repository: CheckListRepository) {

    fun getCheckList(): List<CheckItem> {
        return repository.getCheckList()
    }
}