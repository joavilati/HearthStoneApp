package com.example.hearthstoneapp.model

import com.example.heartstone_repository.model.CardsByItemResponse

data class CardsBy(
    val name: String,
    val image: String
) {
    companion object {
        infix fun from(list: List<CardsByItemResponse>): List<CardsBy> {
            return list.mapNotNull { CardsBy(name = it.name, image = it.img?:"") }
        }
    }
}
