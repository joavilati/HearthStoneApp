package com.example.hearthstoneapp.model

import com.example.heartstone_repository.model.CardResponse

data class Card(
    val image: String,
    val name: String,
    val flavor: String,
    val set: String,
    val faction: String,
    val rarity: String,
    val attack: Int,
    val cost: Int,
    val health: Int,
    val description: String
) {

    companion object {
        infix fun from(item: CardResponse): Card {
           return Card(
               image =  item.img?:"",
               name =  item.name?:"",
               flavor = item.flavor?:"none",
               set = item.cardSet?:"none",
               faction = item.faction?:"none",
               rarity = item.rarity?:"none",
               attack = item.attack?:0,
               cost = item.cost?:0,
               health = item.health?:0,
               description = item.text?:""
           )
        }
    }
}
