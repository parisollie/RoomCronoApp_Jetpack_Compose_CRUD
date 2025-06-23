package com.pjff.roomcronoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pjff.roomcronoapp.viewModels.CronometroViewModel
import com.pjff.roomcronoapp.viewModels.CronosViewModel
import com.pjff.roomcronoapp.views.AddView
import com.pjff.roomcronoapp.views.EditView
import com.pjff.roomcronoapp.views.HomeView

//V-121 Paso 4.0, navegación
@Composable
//Paso 7.3,agregamos cronometroVM: CronometroViewModel
fun NavManager(cronometroVM: CronometroViewModel, cronosVM: CronosViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController, cronosVM)
        }
        composable("AddView"){

            AddView(
                navController,
                //Paso 7.4,agregamos cronometroVM
                cronometroVM,
                cronosVM)
        }
        composable("EditView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.LongType }
        )){
            val id = it.arguments?.getLong("id") ?: 0
            EditView(navController, cronometroVM, cronosVM, id)
        }
    }
}