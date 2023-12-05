package com.rdissi.mytest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rdissi.mytest.data.local.dao.CatalogDao
import com.rdissi.mytest.data.local.model.StoryEntity
import com.rdissi.mytest.data.local.model.VideoEntity

@Database(
    entities = [
        VideoEntity::class,
        StoryEntity::class
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class CatalogDatabase : RoomDatabase() {
    abstract fun catalogDao(): CatalogDao
}
