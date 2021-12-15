package com.example.monsterhunterarmorhelper

import kotlinx.serialization.Serializable
import java.util.*
import java.util.Map
import kotlin.collections.HashMap

@Serializable
data class MonsterHunterArmorEntity(
    val id: Int,
    val type: String,
    val rank: String,
    val rarity: Int,
    val defense: Defense,
    val resistances: Resistances,
    val attributes: HashMap<String,String>,
    val name: String,
    val slots: ArrayList<Map.Entry<String, Int>>

    //TODO: Deserialize the rest of the data
    //val skills: ArrayList<Skill>,
    //val armorSet: String,
    //val assets: String,
    //val crafting: String
)

@Serializable
data class Defense(
    val base: Int,
    val max: Int,
    val augmented: Int
)

@Serializable
data class Resistances(
    val fire: Int,
    val water: Int,
    val ice: Int,
    val thunder: Int,
    val dragon: Int
)

@Serializable
data class Skill(
    val id: Int,
    val level: Int,
    val modifiers: Map.Entry<String, Int>,
    val description: String,
    val skill: Int,
    val skillName: String
)
