package com.rdissi.mytest.data.local

import androidx.room.TypeConverter
import com.rdissi.mytest.data.local.model.SportEntity
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun stringToSportEntity(value: String): SportEntity {
        return Gson().fromJson(value, SportEntity::class.java)
    }

    @TypeConverter
    fun sportEntityToString(sport: SportEntity): String {
        return Gson().toJson(sport)
    }
}