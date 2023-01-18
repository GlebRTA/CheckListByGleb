package com.example.checklistbygleb.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.checklistbygleb.data.CheckListRepositoryImpl
import com.example.checklistbygleb.domain.AddCheckItemUseCase
import com.example.checklistbygleb.domain.CheckItem
import com.example.checklistbygleb.domain.EditCheckItemUseCase
import com.example.checklistbygleb.domain.GetCheckItemUseCase

class CheckItemViewModel : ViewModel() {

    private val repository = CheckListRepositoryImpl

    private val getCheckItemUseCase = GetCheckItemUseCase(repository)
    private val addCheckItemUseCase = AddCheckItemUseCase(repository)
    private val editCheckItemUseCase = EditCheckItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _checkItem = MutableLiveData<CheckItem>()
    val checkItem: LiveData<CheckItem>
        get() = _checkItem

    private val _isClosable = MutableLiveData<Boolean>()
    val isClosable: LiveData<Boolean>
        get() = _isClosable


    fun getCheckItem(itemId: Int) {
        val item = getCheckItemUseCase.getCheckItem(itemId)
        _checkItem.value = item
    }

    fun addCheckItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if (isValid(name, count)) {
            val item = CheckItem(name, count, true)
            addCheckItemUseCase.addCheckItem(item)
            closeActivity()
        }
    }

    fun editCheckItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if (isValid(name, count)) {
            _checkItem.value?.let {
                editCheckItemUseCase.editCheckItem(it.copy(name = name, count = count))
                closeActivity()
            }
        }
    }

    private fun parseName(name: String?): String {
        return name?.trim() ?: ""
    }

    private fun parseCount(count: String?): Int {
        return try {
            count?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
           0
        }
    }

    private fun isValid(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun closeActivity() {
        _isClosable.value = true
    }
}