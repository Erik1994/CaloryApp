package com.mylearnings.caloryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mylearnings.caloryapp.ui.theme.CaloryAppTheme
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core.util.orDefault
import com.mylearnings.caloryapp.navigation.Route
import com.mylearnings.onboarding_presentation.activity.ActivityScreen
import com.mylearnings.onboarding_presentation.age.AgeScreen
import com.mylearnings.onboarding_presentation.gender.GenderScreen
import com.mylearnings.onboarding_presentation.goal.GoalScreen
import com.mylearnings.onboarding_presentation.height.HeightScreen
import com.mylearnings.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.mylearnings.onboarding_presentation.weight.WeightScreen
import com.mylearnings.onboarding_presentation.welcome.WelcomeScreen
import com.mylearnings.tracker_presentation.search.SearchScreen
import com.mylearnings.tracker_presentation.trackeroverview.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferences: Preferences

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shouldShowOnBoarding = preferences.loadShouldShowOnBoarding()
        setContent {
            CaloryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val snackBarHostState = remember { SnackbarHostState() }
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = {
                            SnackbarHost(snackBarHostState)
                        }
                    ) { padding ->
                        NavHost(
                            navController = navController,
                            startDestination = if (shouldShowOnBoarding) Route.WELCOME else Route.TRACKER_OVERVIEW,
                            modifier = Modifier.padding(padding)
                        ) {
                            composable(Route.WELCOME) {
                                WelcomeScreen(onNextClick = { navController.navigate(Route.GENDER) })
                            }
                            composable(Route.AGE) {
                                AgeScreen(
                                    onNextClick = { navController.navigate(Route.HEIGHT) },
                                    snackBarHostState = snackBarHostState
                                )
                            }
                            composable(Route.GENDER) {
                                GenderScreen(onNextClick = { navController.navigate(Route.AGE) })
                            }
                            composable(Route.HEIGHT) {
                                HeightScreen(
                                    onNextClick = { navController.navigate(Route.WEIGHT) },
                                    snackBarHostState = snackBarHostState
                                )
                            }
                            composable(Route.WEIGHT) {
                                WeightScreen(
                                    onNextClick = { navController.navigate(Route.ACTIVITY) },
                                    snackBarHostState = snackBarHostState
                                )
                            }
                            composable(Route.NUTRIENT_GOAL) {
                                NutrientGoalScreen(
                                    onNextClick = { navController.navigate(Route.TRACKER_OVERVIEW) },
                                    snackBarHostState = snackBarHostState
                                )
                            }
                            composable(Route.ACTIVITY) {
                                ActivityScreen(onNextClick = { navController.navigate(Route.GOAL) })
                            }
                            composable(Route.GOAL) {
                                GoalScreen(onNextClick = { navController.navigate(Route.NUTRIENT_GOAL) })
                            }
                            composable(Route.TRACKER_OVERVIEW) {
                                TrackerOverviewScreen(onNavigateToSearch = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.SEARCH
                                                + "/$mealName"
                                                + "/$day"
                                                + "/$month"
                                                + "/$year"
                                    )
                                })
                            }
                            composable(
                                route = Route.SEARCH + "/{${Route.MEAL_NAME}}/{${Route.DAY_OF_MONTH}}/{${Route.MONTH}}/{${Route.YEAR}}",
                                arguments = listOf(
                                    navArgument(Route.MEAL_NAME) {
                                        type = NavType.StringType
                                    },
                                    navArgument(Route.DAY_OF_MONTH) {
                                        type = NavType.IntType
                                    },
                                    navArgument(Route.MONTH) {
                                        type = NavType.IntType
                                    },
                                    navArgument(Route.YEAR) {
                                        type = NavType.IntType
                                    }
                                )
                            ) {
                                val mealName = it.arguments?.getString(Route.MEAL_NAME).orEmpty()
                                val dayOfMonth =
                                    it.arguments?.getInt(Route.DAY_OF_MONTH).orDefault(0)
                                val month = it.arguments?.getInt(Route.MONTH).orDefault(0)
                                val year = it.arguments?.getInt(Route.YEAR).orDefault(0)
                                SearchScreen(
                                    snackbarHostState = snackBarHostState,
                                    month = month,
                                    day = dayOfMonth,
                                    year = year,
                                    mealName = mealName,
                                    onNavigateUp = { navController.navigateUp() })
                            }
                        }
                    }
                }
            }
        }
    }
}