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
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.progdeelite.compose.ui.theme.ComposeArsenalTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint(
        "UnusedMaterialScaffoldPaddingParameter",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArsenalTheme {
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
                    content = { HomeScreen(this) },
                    bottomBar = { BottomAppBar { Text("Barra de Navegação") } }
                )
            }
        }
    }
}

@Composable
fun HomeScreen(context: Context) {
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
    ComposeArsenalTheme {
        Greeting("Android")
    }
}