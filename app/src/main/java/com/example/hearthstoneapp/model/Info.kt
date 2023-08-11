package com.example.hearthstoneapp.model

import com.example.heartstone_repository.model.InfoResponse

data class Info(
    val patch: String = "",
    val classes: List<Classes> = listOf(),
    val sets: List<Sets> = listOf(),
    val types: List<Types> = listOf(),
    val factions: List<Factions> = listOf(),
    val qualities: List<Qualities> = listOf(),
    val races: List<Races> = listOf(),
    val locales: List<Locales> = listOf()
) {
    companion object {
        infix fun from(response: InfoResponse): Info {
            return Info(
                patch = response.patch,
                classes = response.classes.mapNotNull { Classes.fromString(it) },
                sets = response.sets.mapNotNull { Sets.fromString(it) },
                types = response.types.mapNotNull { Types.fromString(it) },
                factions = response.factions.mapNotNull { Factions.fromString(it) },
                qualities = response.qualities.mapNotNull { Qualities.fromString(it) },
                races = response.races.mapNotNull { Races.fromString(it) },
                locales = response.locales.toList().mapNotNull { Locales.fromString(it) }
            )
        }
    }
}