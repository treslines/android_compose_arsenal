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
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import br.com.progdeelite.compose.ui.theme.ComposeArsenalTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArsenalTheme {
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = { TopAppBar(title = {Text("Home")})  },
                    floatingActionButtonPosition = FabPosition.End,
                    floatingActionButton = { FloatingActionButton(onClick = {}){
                        Text("+")
                    } },
                    drawerContent = { Text(text = "Configurações") },
                    content = { HomeScreen(this)},
                    bottomBar = { BottomAppBar() { Text("Barra de Navegação") } }
                )
            }
        }
    }
}

@Composable
fun HomeScreen(context: Context){
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