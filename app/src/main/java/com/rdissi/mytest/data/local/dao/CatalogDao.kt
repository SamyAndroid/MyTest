package com.rdissi.mytest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.rdissi.mytest.data.local.model.CatalogEntity
import com.rdissi.mytest.data.local.model.StoryEntity
import com.rdissi.mytest.data.local.model.VideoEntity

@Dao
interface CatalogDao {

    @Query("SELECT * FROM videos ORDER BY date DESC")
    suspend fun getVideos(): List<VideoEntity>

    @Query("SELECT * FROM stories ORDER BY date DESC")
    suspend fun getStories(): List<StoryEntity>

    @Query("SELECT * FROM videos WHERE id = :videoId")
    suspend fun getVideoById(videoId: Int): VideoEntity

    @Query("SELECT * FROM stories WHERE id = :storyId")
    suspend fun geStoryById(storyId: Int): StoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videos: Collection<VideoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: Collection<StoryEntity>)

    @Query("DELETE FROM videos")
    suspend fun deleteVideos()

    @Query("DELETE FROM stories")
    suspend fun deleteStories()

    @Transaction
    suspend fun updateCatalog(
        catalogEntity: CatalogEntity
    ) {
        deleteVideos()
        deleteStories()
        insertVideos(catalogEntity.videos)
        insertStories(catalogEntity.stories)
    }
}
