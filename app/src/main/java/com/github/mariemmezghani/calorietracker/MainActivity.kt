package com.github.mariemmezghani.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mariemmezghani.calorietracker.navigation.navigate
import com.github.mariemmezghani.calorietracker.ui.theme.CaloryTrackerTheme
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.core.util.UiEvent
import com.github.mariemmezghani.onboarding_presentation.welcome.WelcomeScreen

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
