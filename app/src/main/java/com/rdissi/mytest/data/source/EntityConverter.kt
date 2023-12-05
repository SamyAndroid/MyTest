package com.rdissi.mytest.data.source

import com.rdissi.mytest.data.local.model.CatalogEntity
import com.rdissi.mytest.data.local.model.SportEntity
import com.rdissi.mytest.data.local.model.StoryEntity
import com.rdissi.mytest.data.local.model.VideoEntity
import com.rdissi.mytest.domain.model.Catalog
import com.rdissi.mytest.domain.model.Sport
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.model.Video

object EntityConverter {

    fun CatalogEntity.toCatalog() = Catalog(
        stories = this.stories.toStories(),
        videos = this.videos.toVideos()
    )

    fun Catalog.toCatalogEntity() = CatalogEntity(
        stories = this.stories.toStoryEntities(),
        videos = this.videos.toVideoEntities()
    )

    fun VideoEntity.toVideo() = Video(
        id = this.id,
        title = this.title,
        date = this.date,
        sport = Sport(id = this.sport.id, name = this.sport.name),
        thumb = this.thumb,
        url = this.url,
        views = this.views,
    )

    fun StoryEntity.toStory() = Story(
        id = this.id,
        title = this.title,
        date = this.date,
        sport = Sport(id = this.sport.id, name = this.sport.name),
        author = this.author,
        image = this.image,
        teaser = this.teaser,
    )

    fun Video.toVideoEntity() = VideoEntity(
        id = this.id,
        title = this.title,
        date = this.date,
        sport = SportEntity(id = this.sport.id, name = this.sport.name),
        thumb = this.thumb,
        url = this.url,
        views = this.views,
    )

    fun Story.toStoryEntity() = StoryEntity(
        id = this.id,
        title = this.title,
        date = this.date,
        sport = SportEntity(id = this.sport.id, name = this.sport.name),
        author = this.author,
        image = this.image,
        teaser = this.teaser,
    )

    fun List<VideoEntity>.toVideos(): List<Video> {
        return map {
            it.toVideo()
        }
    }

    fun List<Video>.toVideoEntities(): List<VideoEntity> {
        return map {
            it.toVideoEntity()
        }
    }

    fun List<StoryEntity>.toStories(): List<Story> {
        return map {
            it.toStory()
        }
    }

    fun List<Story>.toStoryEntities(): List<StoryEntity> {
        return map {
            it.toStoryEntity()
        }
    }
}
