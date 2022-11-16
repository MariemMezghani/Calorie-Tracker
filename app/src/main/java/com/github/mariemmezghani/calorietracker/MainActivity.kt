package com.github.mariemmezghani.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mariemmezghani.calorietracker.navigation.navigate
import com.github.mariemmezghani.calorietracker.ui.theme.CaloryTrackerTheme
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.onboarding_presentation.welcome.GenderScreen
import com.github.mariemmezghani.onboarding_presentation.welcome.WelcomeScreen
import com.github.mariemmezghani.onboarding_presentation.welcome.age.AgeScreen
import com.github.mariemmezghani.onboarding_presentation.welcome.height.HeightScreen
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            CaloryTrackerTheme {

                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold( modifier= Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState

                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(navController::navigate)
                        }
                        composable(Route.GENDER) {
                            GenderScreen(navController::navigate)
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )

                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )

                        }
                        composable(Route.Activity) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )

                        }
                    }


                }
            }
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CaloryTrackerTheme {

        val navController = rememberNavController()
        NavHost(
            navController= navController,
            startDestination = Route.WELCOME
        ){
            composable(Route.WELCOME){
                WelcomeScreen (navController::navigate)
            }
            composable(Route.AGE){

            }
        }
}
}
*/
