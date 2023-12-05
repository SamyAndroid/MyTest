package com.rdissi.mytest.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rdissi.mytest.ui.component.MyTopAppBar
import com.rdissi.mytest.ui.navigation.Navigation
import com.rdissi.mytest.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val topAppBarInfo = updateTopBar(navController)

    Scaffold(
        topBar = {
            if (topAppBarInfo.isVisible) {
                MyTopAppBar(
                    title = topAppBarInfo.title,
                    canNavigateBack = topAppBarInfo.showBackIcon,
                    navigateUp = {
                        navController.navigateUp()
                    },
                    showShareIcon = topAppBarInfo.showShareIcon,
                    onShareClick = {
                        viewModel.shareStory(context)
                    }
                )
            }
        },
    ) { innerPadding ->
        Navigation(
            modifier = modifier.padding(innerPadding),
            navController = navController
        )
    }
}
@Composable
fun updateTopBar(
    navController: NavHostController,
): TopBarInfo {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = when (backStackEntry?.destination?.route) {
        Screen.Home.route -> Screen.Home
        Screen.Story.routeWithArgs -> Screen.Story
        Screen.Video.routeWithArgs -> Screen.Video
        else -> Screen.Home
    }
    return when (currentScreen) {
        Screen.Home -> TopBarInfo(
            title = stringResource(id = currentScreen.title),
            showBackIcon = false,
            showShareIcon = false,
            isVisible = true
        )
        Screen.Story -> TopBarInfo(
            title = stringResource(id = currentScreen.title),
            showBackIcon = true,
            showShareIcon = true,
            isVisible = true
        )
        Screen.Video -> TopBarInfo(
            title = stringResource(id = currentScreen.title),
            showBackIcon = true,
            showShareIcon = true,
            isVisible = false
        )
    }
}

data class TopBarInfo(
    val title: String,
    val showBackIcon: Boolean,
    val showShareIcon: Boolean,
    val isVisible: Boolean,
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}