package com.example.checklistbygleb.domain

data class CheckItem(
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean
)