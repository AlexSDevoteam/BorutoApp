@file:OptIn(ExperimentalCoilApi::class)

package alex.boruto.app.navigation

import alex.boruto.app.presentation.screen.details.DetailsScreen
import alex.boruto.app.presentation.screen.home.HomeScreen
import alex.boruto.app.presentation.screen.search.SearchScreen
import alex.boruto.app.presentation.screen.splash.SplashScreen
import alex.boruto.app.presentation.screen.welcome.WelcomeScreen
import alex.boruto.app.util.Constants
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            Screen.Details.route,
            arguments = listOf(navArgument(Constants.DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navController = navController)
        }
        composable(Screen.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}