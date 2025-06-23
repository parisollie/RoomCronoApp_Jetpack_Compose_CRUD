package com.pjff.roomcronoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pjff.roomcronoapp.navigation.NavManager
import com.pjff.roomcronoapp.ui.theme.RoomCronoAppTheme
import com.pjff.roomcronoapp.viewModels.CronometroViewModel
import com.pjff.roomcronoapp.viewModels.CronosViewModel
import dagger.hilt.android.AndroidEntryPoint

//Paso 3.3,ponemos ,@AndroidEntryPoint que usara lo de dagger Hilt
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //V-127,Paso 7.1, inicializamos los viewModels
        val cronometroVM : CronometroViewModel by viewModels()
        val cronosVM : CronosViewModel by viewModels()
        setContent {
            RoomCronoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Paso 4.1, ponemos la navegacion
                    NavManager(
                        //Paso 7.2, ponemos el cronometroVM y el cronosVM
                        cronometroVM,
                        cronosVM)
                }
            }
        }
    }
}

