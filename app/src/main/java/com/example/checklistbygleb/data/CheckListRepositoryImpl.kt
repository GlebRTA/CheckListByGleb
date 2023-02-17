package com.example.checklistbygleb.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.checklistbygleb.domain.entity.CheckItem
import com.example.checklistbygleb.domain.repository.CheckListRepository
import javax.inject.Inject

class CheckListRepositoryImpl @Inject constructor(
    private val checkListDao: CheckListDao,
    private val checkListMapper: CheckListMapper,
) : CheckListRepository {

    override suspend fun addCheckItem(item: CheckItem) {
        checkListDao.addCheckItem(checkListMapper.mapEntityToDbModel(item))
    }

    override suspend fun deleteCheckItem(item: CheckItem) {
        checkListDao.deleteCheckItem(item.id)
    }

    override suspend fun editCheckItem(item: CheckItem) {
        checkListDao.addCheckItem(checkListMapper.mapEntityToDbModel(item))
    }

    override suspend fun getCheckItem(id: Int): CheckItem {
        val dbModel = checkListDao.getCheckItem(id)
        return checkListMapper.mapDbModelToEntity(dbModel)
    }

    override fun getCheckList(): LiveData<List<CheckItem>> {
        return Transformations.map(checkListDao.getCheckList()) {
            checkListMapper.mapListDbModelToListEntity(it)
        }
    }
}