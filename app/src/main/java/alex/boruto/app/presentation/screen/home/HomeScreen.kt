@file:OptIn(ExperimentalCoilApi::class)

package alex.boruto.app.presentation.screen.home

import alex.boruto.app.navigation.Screen
import alex.boruto.app.presentation.common.ListContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {
                navController.navigate(Screen.Search.route)
            })
        }
    ) { innerPadding ->
        ListContent(
            padding = innerPadding,
            heroes = allHeroes,
            navController = navController
        )
    }
}