package br.com.progdeelite.compose

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import br.com.progdeelite.compose.navigation.RootNavigationGraph
import br.com.progdeelite.compose.ui.components.*
import br.com.progdeelite.compose.ui.theme.ArsenalTheme
import br.com.progdeelite.compose.ui.view.LoadingView
import br.com.progdeelite.compose.ui.view.WelcomeView
import br.com.progdeelite.compose.viewmodel.ObserveStateViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint(
        "UnusedMaterialScaffoldPaddingParameter",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ObserveStateViewModel()
        viewModel.setLoadingState(true)

        val saveableViewModel: SaveableViewModel by viewModels()
        val searchViewModel: SearchViewModel by viewModels()

        setContent {

//          Video: https://youtu.be/73jIHwk-Td0
//          VideoWelcome()

//          Video: https://youtu.be/5031eqGD4xU
//          VideoHelloWorld()

//          Video: https://youtu.be/kuwZX2fSj5A
//          VideoSaveState(viewModel)

//          Video: https://youtu.be/UJpwxg4tv_U
//          VideoComplexNavigationGraph()

//          https://youtu.be/Cb9_STKO3TU
//          VideoHoisting(saveableViewModel)

//          Video: https://youtu.be/LgqTeJTZ4c8
//          LazyColumnScreen()

//          https://youtu.be/5kSvGOrfcu0
//          LazyColumnAnimatedScreen()

//          https://youtu.be/jYJKX_7l9H4
//          SearcheableTopBarScreen(searchViewModel)

//          https://youtu.be/oSg6HDmUo1w
//          ProfileImageScreen()

//          https://youtu.be/iEk0CCMy8Lg
//          ProfileRowScreen()

//          https://youtu.be/UtjF2t_Y2GA
//          SimpleCardScreen()

//          https://youtu.be/A8lVGNqx9w8
//          GridViewScreen()

//          https://youtu.be/aQlcHIFAfVM
//          HomeScreen()

//          https://youtu.be/aQlcHIFAfVM
//          BottomNavScreen()

//          https://youtu.be/Y3VeuG3jWHo
//          SplashScreen()

//          https://youtu.be/xxxxxxx
            DrawerScreen()

        }
    }
}


private fun showSelection(context:Context, selectionId: Int) {
    Toast.makeText(context, "Cliquei: $selectionId", Toast.LENGTH_SHORT).show()
}
// import androidx.compose.material.Scaffold
@Composable
private fun DrawerScreen() {
    ArsenalTheme {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.statusBarsPadding(),
            drawerContent = {
                Drawer( onClick = ::showSelection)
            }
        ) { padding ->
            val scope = rememberCoroutineScope()
            FakeContent(
                modifier = Modifier.padding(padding),
                onClick = {
                    scope.launch {
                        // suspended function fora do escopo desse composable (no escopo do Scaffold)
                        // mas que DESENHA também! Não podemos usar LaunchedEffect como antes porque
                        // não podemos chamar elementos de composição em openDrawer. Não estamos na composição.
                        scaffoldState.drawerState.open()
                    }
                }
            )
        }
    }
}

@Composable
fun SplashScreen() {
    ArsenalTheme {
        Surface(color = MaterialTheme.colorScheme.primary) {
            var showLandingScreen by remember { mutableStateOf(true) }
            if (showLandingScreen) {
                LandingScreen(
                    modifier = Modifier,
                    splashWaitTime = 1_500L,
                    onTimeout = { showLandingScreen = false }
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Tela de Home")
                }
            }
        }
    }
}


@Composable
fun BottomNavScreen() {
    ArsenalTheme {
        Scaffold(
            bottomBar = { BottomNav() }
        ) { padding ->
            HomeContent(
                modifier = Modifier.padding(padding),
                rowData = listOf(
                    ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 1),
                    ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 2),
                    ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 3),
                    ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 4),
                    ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 5),
                    ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 6),
                    ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 7),
                ),
                gridViewData = listOf(
                    SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 1),
                    SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 2),
                    SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 3),
                    SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 4),
                    SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 5),
                    SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 6),
                    SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 7),
                )
            )
        }
    }
}

@Composable
fun HomeScreen() {
    ArsenalTheme {
        HomeContent(
            rowData = listOf(
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 1),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 2),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 3),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 4),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 5),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 6),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 7),
            ),
            gridViewData = listOf(
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 1),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 2),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 3),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 4),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 5),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 6),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 7),
            )
        )
    }
}

@Composable
fun GridViewScreen() {
    ArsenalTheme {
        Gridview(
            gridViewData = listOf(
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 1),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 2),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 3),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 4),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 5),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 6),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name, 7),
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun SimpleCardScreen() {
    ArsenalTheme {
        SimpleCard(
            text = R.string.app_name,
            drawable = R.drawable.ic_droid,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun ProfileRowScreen() {
    ArsenalTheme {
        ProfileRow(
            rowData = listOf(
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 1),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 2),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 3),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 4),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 5),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 6),
                ProfileImageItem(R.drawable.ic_droid, R.string.profile_name, 7),
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ProfileImageScreen() {
    ArsenalTheme {
        ProfileImage(
            text = R.string.profile_name,
            drawable = R.drawable.ic_droid,
            modifier = Modifier
                .background(Color.Green)
                .padding(8.dp)
        )
    }
}

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun SearcheableTopBarScreen(mainViewModel: SearchViewModel) {
    val isShowingSearchField by mainViewModel.isShowingSearchField
    val currentSearchText by mainViewModel.currentSearchText

    ArsenalTheme {
        androidx.compose.material.Scaffold(
            topBar = {
                SearchableTopBar(
                    isShowingSearchField = isShowingSearchField,
                    currentSearchText = currentSearchText,
                    onSearchTextChanged = { mainViewModel.setCurrentSearchText(newText = it) },
                    onSearchDeactivated = { mainViewModel.showSearchField(show = false) },
                    onSearchDispatched = { Log.d("SEARCH_TEST", "Usuário pesquisou por: $it") },
                    onSearchIconClicked = { mainViewModel.showSearchField(show = true) }
                )
            }
        ) {}
    }
}

@Composable
fun LazyColumnAnimatedScreen() {
    ArsenalTheme {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(
                Item(1, "meu Item 1", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(2, "meu Item 2 ", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(3, "meu Item 3 ", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(4, "meu Item 4", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(5, "meu Item 5", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(6, "meu Item 6", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(7, "meu Item 4", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(8, "meu Item 5", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(9, "meu Item 6", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(10, "meu Item 7", "Aug. 2022", "Sept. 2022", "AAAAA")
            )
            registerId.value = "R123456"
        }
        SettingsScreenAnimated(viewModel)
    }
}

@Composable
fun LazyColumnScreen() {
    ArsenalTheme {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(
                Item(1, "meu Item 1", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(2, "meu Item 2 ", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(3, "meu Item 3 ", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(4, "meu Item 4", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(5, "meu Item 5", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(6, "meu Item 6", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(7, "meu Item 4", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(8, "meu Item 5", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(9, "meu Item 6", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(10, "meu Item 7", "Aug. 2022", "Sept. 2022", "AAAAA")
            )
            registerId.value = "R123456"
        }
        SettingsScreen(viewModel)
    }
}

@Composable
private fun VideoWelcome() {
    ArsenalTheme {
        WelcomeView(LocalContext.current)
    }
}

@Composable
private fun VideoHoisting(viewModel: SaveableViewModel) {
    ArsenalTheme {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(
                text = "COM STATES",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            ExpandableView()
            ExpandableSavableView()
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = "COM VIEW MODELS",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            ExpandableViewHoisted(ExpandableViewModel())
            ExpandableViewHoistedSavable(viewModel)
        }
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