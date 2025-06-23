package com.pjff.roomcronoapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pjff.roomcronoapp.R
import com.pjff.roomcronoapp.components.CircleButton
import com.pjff.roomcronoapp.components.MainIconButton
import com.pjff.roomcronoapp.components.MainTextField
import com.pjff.roomcronoapp.components.MainTitle
import com.pjff.roomcronoapp.components.formatTiempo
import com.pjff.roomcronoapp.model.Cronos
import com.pjff.roomcronoapp.viewModels.CronometroViewModel
import com.pjff.roomcronoapp.viewModels.CronosViewModel

//V-124, paso 6.0
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(
    navController: NavController,
    //paso 7.5, le pasamos el cronometroVM: CronometroViewModel
    cronometroVM: CronometroViewModel,
    cronosVM: CronosViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                title = { MainTitle(title = "ADD CRONO") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                //Paso 6.1
                navigationIcon = {
                    // Mandamos a llamar a MainIconButton y le pasamos el icono
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { it ->
        ContentAddView(
            it,
            navController,
            // Paso 7.6
            cronometroVM,
            cronosVM)
    }
}

@Composable
fun ContentAddView(
    it: PaddingValues,
    navController: NavController,
    //Paso 7.7
    cronometroVM: CronometroViewModel,
    cronosVM: CronosViewModel
) {
    //paso 7.9, traemos los datos en el estado
    val state = cronometroVM.state
    //Funcion para ejecutar el view Model 
    LaunchedEffect(state.cronometroActivo) {
        //Traemos la función cronos que es la funcion para ejecutar el cronometro.
        cronometroVM.cronos()
    }

    Column(
        //Paso 7.8
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Paso 7.10
        Text(
            text = formatTiempo(cronometroVM.tiempo),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )

        //Paso 8.1
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            // Iniciar
            CircleButton(
                icon = painterResource(id = R.drawable.play),
                enabled = !state.cronometroActivo
            ) {
                cronometroVM.iniciar()
            }
            // Pausar
            CircleButton(
                icon = painterResource(id = R.drawable.pausa),
                enabled = state.cronometroActivo
            ) {
                cronometroVM.pausar()
            }

            // Detener
            CircleButton(
                icon = painterResource(id = R.drawable.stop),
                enabled = !state.cronometroActivo
            ) {
                cronometroVM.detener()
            }

            // Mostrar guardar
            CircleButton(
                icon = painterResource(id = R.drawable.save),
                enabled = state.showSaveButton
            ) {
                cronometroVM.showTextField()
            }

        }//Row

        if (state.showTextField) {
            MainTextField(
                value = state.title,
                onValueChange = { cronometroVM.onValue(it) },
                label = "Title"
            )

            Button(onClick = {
                cronosVM.addCrono(
                    Cronos(
                        title = state.title,
                        crono = cronometroVM.tiempo
                    )
                )

                navController.popBackStack()

            }) {
                Text(text = "Guardar")
            }
            DisposableEffect(Unit){
                onDispose {
                    cronometroVM.detener()
                }
            }
        }//If
    }
}