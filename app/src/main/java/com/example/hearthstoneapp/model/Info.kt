package com.example.hearthstoneapp.model

import com.example.heartstone_repository.model.InfoResponse

data class Info(
    val patch: String, // Mantido como String porque não há enum correspondente para "patch"
    val classes: List<Classes>,
    val sets: List<Sets>,
    val types: List<Types>,
    val factions: List<Factions>,
    val qualities: List<Qualities>,
    val races: List<Races>,
    val locales: List<Locales>
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
                locales = response.locales.mapNotNull { Locales.fromString(it) }
            )
        }
    }
}