package com.sertanfox.movieapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sertanfox.movieapp.navigation.AppNavGraph
import com.sertanfox.movieapp.navigation.NavigationProvider
import com.sertanfox.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {

                val navController = rememberNavController()
                App(navController,navigationProvider)

            }
        }
    }
}

@Composable
fun App(navHostController:NavHostController, navigationProvider:NavigationProvider){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AppNavGraph(navController = navHostController, navigationProvider = navigationProvider)
        //Greeting(name = "Android!")
    }
}

//@Preview(showBackground = true)
@Composable
fun userAddScreen(){
    var name = remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
         Row(
             Modifier.fillMaxWidth()
         ) {
            //OutlinedTextField(
            //    value = name,
            //    onValueChange = {

            //    }

            //)
         }
    }
}

@Composable
fun Counter(){
    var count = remember {
        mutableStateOf(0)
    }
    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(
            text = count.value.toString(),
            fontSize = 30.sp
        )
        Button(onClick = {
            count.value++
        }) {
            Text(text = "Click me")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    LazyRow {
        items(30){
            MyAddButtons(it)
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        Greeting("Android")
    }
}

@Composable
fun MyAddButtons(count:Int, modifier : Modifier = Modifier){
    val context = LocalContext.current
    Button(onClick = {
        Toast.makeText(context,"this is $count",Toast.LENGTH_SHORT).show()
    }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello $count!",
                modifier = modifier
            )
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = null
            )
        }
    }
}

@Composable
fun ShowSnackbar(message: String) {
    Snackbar(
        action = {
            TextButton(onClick = {

            }) {
                Text("OK")
            }
        }
    ) {
        Text(message)
    }
}
