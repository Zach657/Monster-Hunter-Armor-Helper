package com.example.monsterhunterarmorhelper

import java.lang.Exception
import java.net.URL
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class DataHandler {
    companion object {
        const val webAddress = "https://mhw-db.com/armor"
        fun fetchData(): ArrayList<MonsterHunterArmorEntity> {
            var listItems = arrayListOf<MonsterHunterArmorEntity>()
            try {
                val response = URL(webAddress).readText()
                //Parse Json using Serializeable MonsterHunterArmorEntity
                listItems = Json { ignoreUnknownKeys = true
                                   isLenient = true
                                 }.decodeFromString<ArrayList<MonsterHunterArmorEntity>>(response)
            }
            catch (e:Exception){
                listItems = arrayListOf<MonsterHunterArmorEntity>()
            }
            return listItems
        }
    }
}