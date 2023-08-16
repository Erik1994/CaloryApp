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
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mylearnings.caloryapp.navigation.navigate
import com.mylearnings.caloryapp.ui.theme.CaloryAppTheme
import com.mylearnings.core_ui.navigation.Route
import com.mylearnings.onboarding_presentation.activity.ActivityScreen
import com.mylearnings.onboarding_presentation.age.AgeScreen
import com.mylearnings.onboarding_presentation.gender.GenderScreen
import com.mylearnings.onboarding_presentation.goal.GoalScreen
import com.mylearnings.onboarding_presentation.height.HeightScreen
import com.mylearnings.onboarding_presentation.weight.WeightScreen
import com.mylearnings.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                            startDestination = Route.WELCOME,
                            modifier = Modifier.padding(padding)
                        ) {
                            composable(Route.WELCOME) {
                                WelcomeScreen(onNavigate = { navController.navigate(it) })
                            }
                            composable(Route.AGE) {
                                AgeScreen(
                                    onNavigate = { navController.navigate(it) },
                                    snackBarHostState = snackBarHostState
                                )
                            }
                            composable(Route.GENDER) {
                                GenderScreen(onNavigate = { navController.navigate(it) })
                            }
                            composable(Route.HEIGHT) {
                                HeightScreen(
                                    onNavigate = { navController.navigate(it) },
                                    snackBarHostState = snackBarHostState
                                )
                            }
                            composable(Route.WEIGHT) {
                                WeightScreen(
                                    onNavigate = { navController.navigate(it) },
                                    snackBarHostState = snackBarHostState
                                )
                            }
                            composable(Route.NUTRIENT_GOAL) {

                            }
                            composable(Route.ACTIVITY) {
                                ActivityScreen(onNavigate = { navController.navigate(it) })
                            }
                            composable(Route.GOAL) {
                                GoalScreen(onNavigate = { navController.navigate(it) })
                            }
                            composable(Route.TRACKER_OVERVIEW) {

                            }
                            composable(Route.SEARCH) {

                            }
                        }
                    }
                }
            }
        }
    }
}