package com.example.heartstone_repository.model

data class CardsByItemResponse(
    val cardBackId: Int,
    val enabled: Boolean,
    val img: String?,
    val text: String?,
    val imgAnimated: String,
    val locale: String,
    val name: String,
    val sortCategory: Int,
    val sortOrder: Int,
    val source: String
)