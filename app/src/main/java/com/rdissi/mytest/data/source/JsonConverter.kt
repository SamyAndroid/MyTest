package com.rdissi.mytest.data.source

import com.rdissi.mytest.data.remote.model.CatalogJson
import com.rdissi.mytest.data.remote.model.SportJson
import com.rdissi.mytest.data.remote.model.StoryJson
import com.rdissi.mytest.data.remote.model.VideoJson
import com.rdissi.mytest.domain.model.Catalog
import com.rdissi.mytest.domain.model.Sport
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.model.Video

object JsonConverter {

    fun CatalogJson.toCatalog() = Catalog(
        stories = this.stories.toStories(),
        videos = this.videos.toVideos()
    )

    fun Catalog.toCatalogJson() = CatalogJson(
        stories = this.stories.toStoriesJson(),
        videos = this.videos.toVideosJson()
    )

    fun VideoJson.toVideo() = Video(
        id = this.id,
        title = this.title,
        date = this.date,
        sport = Sport(id = this.sport.id, name = this.sport.name),
        thumb = this.thumb,
        url = this.url,
        views = this.views,
    )

    fun StoryJson.toStory() = Story(
        id = this.id,
        title = this.title,
        date = this.date,
        sport = Sport(id = this.sport.id, name = this.sport.name),
        author = this.author,
        image = this.image,
        teaser = this.teaser,
    )

    fun Video.toVideoJson() = VideoJson(
        id = this.id,
        title = this.title,
        date = this.date,
        sport = SportJson(id = this.sport.id, name = this.sport.name),
        thumb = this.thumb,
        url = this.url,
        views = this.views,
    )

    fun Story.toStoryJson() = StoryJson(
        id = this.id,
        title = this.title,
        date = this.date,
        sport = SportJson(id = this.sport.id, name = this.sport.name),
        author = this.author,
        image = this.image,
        teaser = this.teaser,
    )

    fun List<VideoJson>.toVideos(): List<Video> {
        return map {
            it.toVideo()
        }
    }

    fun List<Video>.toVideosJson(): List<VideoJson> {
        return map {
            it.toVideoJson()
        }
    }

    fun List<StoryJson>.toStories(): List<Story> {
        return map {
            it.toStory()
        }
    }

    fun List<Story>.toStoriesJson(): List<StoryJson> {
        return map {
            it.toStoryJson()
        }
    }
}
