package com.example.heartstone_repository.model

data class InfoResponse(
    val patch: String,
    val classes: List<String>,
    val sets: List<String>,
    val types: List<String>,
    val factions: List<String>,
    val qualities: List<String>,
    val races: List<String>,
    val locales: List<String>
)
