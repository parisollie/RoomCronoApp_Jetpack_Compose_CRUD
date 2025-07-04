package com.pjff.roomcronoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pjff.roomcronoapp.components.CronCard
import com.pjff.roomcronoapp.components.FloatButton
import com.pjff.roomcronoapp.components.MainTitle
import com.pjff.roomcronoapp.components.formatTiempo
import com.pjff.roomcronoapp.viewModels.CronosViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    //Paso 9.1
    cronosVM: CronosViewModel
){
    //Paso 5.1
    Scaffold(
        topBar = {
            //Paso 5.3
            CenterAlignedTopAppBar(
                //Paso 5.5 Importamos el mainTitle
                title = { MainTitle(title = "CRONO APP") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        //Paso 5.6
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {
        //paso 5.2,ponemos el contentHomeView con el it y el nav
        ContentHomeView(
            it,
            navController,
            //Paso 9.2
            cronosVM)
    }
}

//Paso 9.3
@Composable
fun ContentHomeView(
    it: PaddingValues,
    navController: NavController,
    cronosVM: CronosViewModel
){
    Column(
        modifier = Modifier.padding(it)
    ) {
        val cronosList by cronosVM.cronosList.collectAsState()
        LazyColumn{
            items(cronosList){item ->
                //V-133,Paso 10.0, Swipes
                val delete = SwipeAction(
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = { cronosVM.deleteCrono(item) }
                )
                //startactions es para que este a la derecha,swipeThreshold es para alargar la accion
                SwipeableActionsBox(endActions = listOf(delete), swipeThreshold = 270.dp) {
                    //Paso 9.5, traemos la CronoCard
                    CronCard(item.title, formatTiempo(item.crono)) {
                            //Paso 11.5, le ponemos el id, para editar
                            navController.navigate("EditView/${item.id}")
                    }
                }//SwipeableActionsBox
            }
        }//Lazy column
    }
}
