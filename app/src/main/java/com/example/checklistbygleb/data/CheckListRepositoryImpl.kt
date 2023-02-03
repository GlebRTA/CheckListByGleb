package com.example.checklistbygleb.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.checklistbygleb.domain.CheckItem
import com.example.checklistbygleb.domain.CheckListRepository

class CheckListRepositoryImpl(
    application: Application
) : CheckListRepository {

    private val checkListDao = AppDatabase.getInstance(application).shopListDao()
    private val checkListMapper = CheckListMapper()

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