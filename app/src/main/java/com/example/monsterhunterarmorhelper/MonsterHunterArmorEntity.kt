package com.example.monsterhunterarmorhelper

import java.util.*

data class MonsterHunterArmorEntity(
    val id: Int,
    val type: String,
    val rank: String,
    val rarity: Int,
    val defense: Defense,
    val resistances: Resistances,
    val attributes: String,
    val name: String,
    val slots: Any,
    val skills: Any,
    val armorSet: Any,
    val assets: Any,
    val crafting: Any
)

data class Defense(
    val base: Int,
    val max: Int,
    val augmented: Int
)

data class Resistances(
    val fire: Int,
    val water: Int,
    val ice: Int,
    val thunder: Int,
    val dragon: Int
)
