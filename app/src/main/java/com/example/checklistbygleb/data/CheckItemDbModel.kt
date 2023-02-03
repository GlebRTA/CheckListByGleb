package com.example.checklistbygleb.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "check_items")
data class CheckItemDbModel(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean,

)