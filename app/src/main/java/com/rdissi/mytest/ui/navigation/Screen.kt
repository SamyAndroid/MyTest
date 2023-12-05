package com.rdissi.mytest.ui.navigation

import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.rdissi.mytest.R

sealed class Screen(
    val route: String,
    val routeWithArgs: String = "",
    val arguments: List<NamedNavArgument> = emptyList(),
    @StringRes val title: Int
) {

    object Home: Screen(
        route = "home_screen",
        title = R.string.author
    )

    object Story: Screen(
        route = "story_screen",
        routeWithArgs = "story_screen/{id}",
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        ),
        title = R.string.story_page,
    )

    object Video: Screen(
        route = "video_screen",
        routeWithArgs = "video_screen?url={url}",
        arguments = listOf(
            navArgument("url") { type = NavType.StringType }
        ),
        title = R.string.player_page
    )
}
