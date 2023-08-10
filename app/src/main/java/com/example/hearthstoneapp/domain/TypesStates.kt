package com.example.hearthstoneapp.domain

enum class Classes(val value: String) {
    DRUID("Druid"),
    HUNTER("Hunter"),
    MAGE("Mage"),
    PALADIN("Paladin"),
    PRIEST("Priest"),
    ROGUE("Rogue"),
    SHAMAN("Shaman"),
    WARLOCK("Warlock"),
    WARRIOR("Warrior"),
    DREAM("Dream");

    companion object {
        fun fromString(value: String): Classes? {
            return values().find { it.value.equals(value, ignoreCase = true) }
        }
    }
}

enum class Sets(val value: String) {
    BASIC("Basic"),
    CLASSIC("Classic"),
    CREDITS("Credits"),
    NAXXRAMAS("Naxxramas"),
    DEBUG("Debug"),
    GOBLINS_VS_GNOMES("Goblins vs Gnomes"),
    MISSIONS("Missions"),
    PROMOTION("Promotion"),
    REWARD("Reward"),
    SYSTEM("System"),
    BLACKROCK_MOUNTAIN("Blackrock Mountain"),
    HERO_SKINS("Hero Skins"),
    TAVERN_BRAWL("Tavern Brawl"),
    THE_GRAND_TOURNAMENT("The Grand Tournament");

    companion object {
        fun fromString(value: String): Sets? {
            return values().find { it.value.equals(value, ignoreCase = true) }
        }
    }
}

enum class Types(val value: String) {
    HERO("Hero"),
    MINION("Minion"),
    SPELL("Spell"),
    ENCHANTMENT("Enchantment"),
    WEAPON("Weapon"),
    HERO_POWER("Hero Power");

    companion object {
        fun fromString(value: String): Types? {
            return values().find { it.value.equals(value, ignoreCase = true) }
        }
    }
}

enum class Factions(val value: String) {
    HORDE("Horde"),
    ALLIANCE("Alliance"),
    NEUTRAL("Neutral");

    companion object {
        fun fromString(value: String): Factions? {
            return values().find { it.value.equals(value, ignoreCase = true) }
        }
    }
}

enum class Qualities(val value: String) {
    FREE("Free"),
    COMMON("Common"),
    RARE("Rare"),
    EPIC("Epic"),
    LEGENDARY("Legendary");

    companion object {
        fun fromString(value: String): Qualities? {
            return values().find { it.value.equals(value, ignoreCase = true) }
        }
    }
}

enum class Races(val value: String) {
    DEMON("Demon"),
    DRAGON("Dragon"),
    MECH("Mech"),
    MURLOC("Murloc"),
    BEAST("Beast"),
    PIRATE("Pirate"),
    TOTEM("Totem");

    companion object {
        fun fromString(value: String): Races? {
            return values().find { it.value.equals(value, ignoreCase = true) }
        }
    }
}

enum class Locales(val value: String) {
    DEDE("deDE"),
    ENGB("enGB"),
    ENUS("enUS"),
    ESES("esES"),
    ESMX("esMX"),
    FRFR("frFR"),
    ITIT("itIT"),
    KOKR("koKR"),
    PLPL("plPL"),
    PTBR("ptBR"),
    RURU("ruRU"),
    ZHCN("zhCN"),
    ZHTW("zhTW");

    companion object {
        fun fromString(value: String): Locales? {
            return values().find { it.value.equals(value, ignoreCase = true) }
        }
    }
}
