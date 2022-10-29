package com.github.mariemmezghani.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mariemmezghani.calorietracker.navigation.navigate
import com.github.mariemmezghani.calorietracker.ui.theme.CaloryTrackerTheme
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.onboarding_presentation.welcome.GenderScreen
import com.github.mariemmezghani.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {

                val navController = rememberNavController()
                NavHost(
                    navController= navController,
                    startDestination = Route.WELCOME
                ){
                    composable(Route.WELCOME){
                        WelcomeScreen (navController::navigate)
                    }
                    composable(Route.GENDER){
                        GenderScreen (navController::navigate)
                    }
                    composable(Route.AGE){

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
