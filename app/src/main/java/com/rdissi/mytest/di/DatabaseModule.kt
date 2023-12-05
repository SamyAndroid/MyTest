package com.rdissi.mytest.di

import android.content.Context
import androidx.room.Room
import com.rdissi.mytest.data.local.CatalogDatabase
import com.rdissi.mytest.data.local.dao.CatalogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): CatalogDatabase {
        return Room.databaseBuilder(
            applicationContext,
            CatalogDatabase::class.java,
            "catalog.db",
        ).build()
    }

    @Provides
    fun provideLogDao(database: CatalogDatabase): CatalogDao {
        return database.catalogDao()
    }
}
