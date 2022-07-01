package br.com.progdeelite.compose

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import br.com.progdeelite.compose.navigation.RootNavigationGraph
import br.com.progdeelite.compose.ui.theme.ArsenalTheme
import br.com.progdeelite.compose.ui.view.LoadingView
import br.com.progdeelite.compose.ui.view.WelcomeView
import br.com.progdeelite.compose.viewmodel.ObserveStateViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint(
        "UnusedMaterialScaffoldPaddingParameter",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ObserveStateViewModel()
        viewModel.setLoadingState(true)

        setContent {

//          Video: https://youtu.be/73jIHwk-Td0
//          VideoWelcome()

//          Video: https://youtu.be/xxxxxxxx
            VideoComplexNavigationGraph()

//          Video: https://youtu.be/5031eqGD4xU
//          VideoHelloWorld()

//          Video: https://youtu.be/kuwZX2fSj5A
//          VideoSaveState(viewModel)
        }
    }
}

@Composable
private fun VideoWelcome() {
    ArsenalTheme {
        WelcomeView(LocalContext.current)
    }
}

@Composable
private fun VideoComplexNavigationGraph() {
    ArsenalTheme {
        RootNavigationGraph(navController = rememberNavController())
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VideoHelloWorld() {
    ArsenalTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Home") },
                    backgroundColor = MaterialTheme.colorScheme.onSecondary
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(onClick = {}) {
                    Text("+")
                }
            },

            content = { HomeView(LocalContext.current) }, // Video: https://youtu.be/5031eqGD4xU
            bottomBar = { BottomAppBar { Text("Barra de Navegação") } }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VideoSaveState(viewModel: ObserveStateViewModel) {
    ArsenalTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Home") },
                    backgroundColor = MaterialTheme.colorScheme.onSecondary
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(onClick = {}) {
                    Text("+")
                }
            },

            content = { LoadingView(viewModel) }, // Video: https://youtu.be/kuwZX2fSj5A
            bottomBar = { BottomAppBar { Text("Barra de Navegação") } }
        )
    }
}

@Composable
fun HomeView(context: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Greeting(name = "Compose")
        TextButton {
            Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun TextButton(clickAction: () -> Unit) {
    Button(onClick = clickAction) {
        Text(text = "Click Me!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArsenalTheme {
        Greeting("Android")
    }
}