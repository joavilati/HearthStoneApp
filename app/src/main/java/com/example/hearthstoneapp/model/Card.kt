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
    val health: Int
) {

    companion object {
        infix fun from(item: CardResponse): Card {
           return Card(
               image =  item.img,
               name =  item.name,
               flavor = item.flavor,
               set = item.cardSet,
               faction = item.faction,
               rarity = item.rarity,
               attack = item.attack,
               cost = item.cost,
               health = item.health
           )
        }
    }
}
