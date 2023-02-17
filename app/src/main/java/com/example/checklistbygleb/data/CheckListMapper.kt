package com.example.checklistbygleb.data

import com.example.checklistbygleb.domain.entity.CheckItem
import javax.inject.Inject

class CheckListMapper @Inject constructor() {

    fun mapEntityToDbModel(checkItem: CheckItem) = CheckItemDbModel(
        id = checkItem.id,
        name = checkItem.name,
        count = checkItem.count,
        enabled = checkItem.enabled
    )

    fun mapDbModelToEntity(checkItemDbModel: CheckItemDbModel) = CheckItem(
        id = checkItemDbModel.id,
        name = checkItemDbModel.name,
        count = checkItemDbModel.count,
        enabled = checkItemDbModel.enabled
    )

    fun mapListDbModelToListEntity(list: List<CheckItemDbModel>) = list.map { mapDbModelToEntity(it) }
}