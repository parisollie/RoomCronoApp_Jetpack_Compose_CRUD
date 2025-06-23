package com.pjff.roomcronoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
//Vid 121
fun HomeView(navController: NavController, cronosVM: CronosViewModel){
    //Vid 121
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                //Importamos el main tittle
                title = { MainTitle(title = "CRONO APP") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {
        ContentHomeView(it, navController, cronosVM)
    }
}

@Composable
//Vid 121 ,
fun ContentHomeView(it: PaddingValues, navController: NavController, cronosVM: CronosViewModel){
    Column(
        modifier = Modifier.padding(it)
    ) {
        val cronosList by cronosVM.cronosList.collectAsState()
        LazyColumn{
            items(cronosList){item ->
                val delete = SwipeAction(
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = { cronosVM.deleteCrono(item) }
                )
                SwipeableActionsBox(endActions = listOf(delete), swipeThreshold = 270.dp) {
                    CronCard(item.title, formatTiempo(item.crono)) {
                            navController.navigate("EditView/${item.id}")
                    }
                }

            }
        }
        
    }
}














