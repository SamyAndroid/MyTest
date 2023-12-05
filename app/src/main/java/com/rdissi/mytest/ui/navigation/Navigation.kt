package com.rdissi.mytest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.model.Video
import com.rdissi.mytest.ui.catalog.CatalogPage
import com.rdissi.mytest.ui.story.StoryScreen
import com.rdissi.mytest.ui.video.Player

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Main Page
        composable(route = Screen.Home.route) {
            CatalogPage(
                onItemClick = { media ->
                    when (media) {
                        is Video -> {
                            navController.navigate(route = Screen.Video.route + "?url=" + media.url)
                        }
                        is Story -> {
                            navController.navigate(route = Screen.Story.route + "/" + media.id)
                        }
                    }
                },
            )
        }
        // Story screen
        composable(
            route = Screen.Story.routeWithArgs,
            arguments = Screen.Story.arguments
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id") ?: -1
            StoryScreen(storyId = id)
        }
        // Player
        composable(
            route = Screen.Video.routeWithArgs,
            arguments = Screen.Video.arguments
        ) { navBackStackEntry ->
            val url = navBackStackEntry.arguments?.getString("url").orEmpty()
            Player(url)
        }
    }
}