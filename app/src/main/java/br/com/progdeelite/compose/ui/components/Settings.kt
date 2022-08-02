package br.com.progdeelite.compose.ui.components

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.progdeelite.compose.R
import androidx.compose.material3.MaterialTheme
import br.com.progdeelite.compose.ui.theme.ArsenalTheme
import br.com.progdeelite.compose.ui.theme.ArsenalThemeExtended
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// 1) COMO CRIAR E EXIBIR ITENS EM LAZY COLUMN COM STICK HEADER (TELA DE SETTINGS)
// 2) COMO OBSERVAR AS MUDANCAS QUE VÃO RE-DESENHAR O COMPONENTE
// 3) COMO MODELAR UMA TELA VAZIA QUANDO A LISTA ESTA VAZIA

data class Item(
    var id: Int = 1,
    var title: String = "R123456",
    var activated: String= "Aug. 2022",
    var lastLogin: String = "Sept. 2022",
    var serial: String = "AAAAA"
)

class SettingsViewModel : ViewModel() {

    val items = MutableStateFlow<MutableList<Item>>(mutableStateListOf())
    val registerId = MutableStateFlow("")

    fun setItems(items: List<Item>) {
        viewModelScope.launch {
            this@SettingsViewModel.items.emit(items as MutableList<Item>)
        }
    }

    fun removeItem(item: Item) {
        items.value = items.value.filter { it != item }.toMutableList()
    }

    fun navigateTo(destinationId: Int) {
        Log.d("TESTANDO", "Nague para destination: $destinationId")
    }

    fun getLastItemId(): Int = items.value.last().id

    fun setRegisterId(id: String) {
        viewModelScope.launch {
            registerId.emit(id)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {

    val items by viewModel.items.collectAsState()
    val registerId by viewModel.registerId.collectAsState()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 32.dp)
    ) {
        Text(
            text = "Configurações", // Ou passa na assinatura do método ou pega da tradução!
            style = ArsenalThemeExtended.typography.h1,
            modifier = Modifier.padding(start = 32.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        if (items.isNotEmpty()) {
            // LINK OFICIAL: https://developer.android.com/jetpack/compose/lists
            LazyColumn {
                stickyHeader {
                    Text(
                        text = "Ativações", // Ou passa na assinatura do método ou pega da tradução!
                        style = ArsenalThemeExtended.typography.h1,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxWidth()
                            .padding(start = 32.dp, top= 16.dp, bottom = 16.dp)
                    )
                }
                items(items = items) { item ->
                    SettingsItem(item) { viewModel.removeItem(item) } // ação a ser executada
                    Spacer(modifier = Modifier.height(12.dp))
                    if(viewModel.getLastItemId() == item.id && registerId.isNotEmpty()){
                        RegisterId(registerId)
                    }
                }
            }
        } else {
            SettingsEmptyItem()
            if(registerId.isNotEmpty()){
                RegisterId(registerId)
            }
        }
    }
}

@Composable
fun RegisterId(
    id: String
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()
    ) {
        Spacer(
            modifier = Modifier
                .height(12.dp)
                .weight(1F)
        )
        val registerIdText = stringResource(R.string.register_id)
        Spacer(modifier = Modifier.height(8.dp))
        RegisterIdText(registerIdText)
        RegisterIdText(id)
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun SettingsItem(
    item: Item,
    onItemClicked: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(12.dp)
                .size(38.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_verified),
                contentDescription = "certified icon", // NÃO FACA ASSIM, TRADUZA OU PASSE POR REFERÊNCIA.
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Center),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Column(modifier = Modifier.weight(1F)) {
            Text(
                text = item.title,
                style = ArsenalThemeExtended.typography.h2
            )
            Text(
                text = item.activated,
            )
            Text(
                text = item.lastLogin,
            )
        }

        Box(modifier = Modifier.width(50.dp)) {
            OutlinedButton(
                onClick = { onItemClicked() },
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(2.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = "delete icon", // NÃO FACA ASSIM, TRADUZA OU PASSE POR REFERÊNCIA.
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
fun SettingsEmptyItem() {
    Box(modifier = Modifier.padding(12.dp)) {
        Column(Modifier.background(colorResource(R.color.seed))) {
            Card(
                shape = RoundedCornerShape(0.dp),
                border = BorderStroke(1.dp, colorResource(R.color.seed)),
                modifier = Modifier.padding(start = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                        .background(ArsenalThemeExtended.colors.snowWhite)
                ) {
                    Box {
                        Image(
                            modifier = Modifier
                                .padding(start = 8.dp, top = 20.dp, end = 8.dp, bottom = 20.dp)
                                .align(Alignment.TopCenter),
                            painter = painterResource(R.drawable.ic_warning),
                            contentDescription = null,
                            // ÓTIMO SABER COMO SE FAZ, PARA COLORIR UM SVG EM COMPOSE
                            colorFilter = ColorFilter.tint(color = colorResource(R.color.seed))
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 20.dp, end = 32.dp),
                        text = stringResource(R.string.app_name),
                        style = ArsenalThemeExtended.typography.body1,
                    )
                }
            }
        }
    }
}

@Composable
private fun RegisterIdText(textString: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = textString,
        style = ArsenalThemeExtended.typography.body2.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp
        ),
    )
}



@Preview
@Composable
fun PreviewSettingsEmptyItem() {
    ArsenalTheme {
        SettingsEmptyItem()
    }
}

@Preview
@Composable
fun PreviewEmptySettingsScreen() {
    ArsenalTheme {
        val viewModel = SettingsViewModel()
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewDarkSettingsEmptyItem() {
    ArsenalTheme(useDarkTheme = true) {
        SettingsEmptyItem()
    }
}

@Preview
@Composable
fun PreviewDarkEmptySettingsScreen() {
    ArsenalTheme(useDarkTheme = false) {
        val viewModel = SettingsViewModel()
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewSettingsScreen() {
    ArsenalTheme {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(Item(1,"R123456", "Aug. 2022", "Sept. 2022", "AAAAA"))
        }
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewMultiItemsSettingsScreen() {
    ArsenalTheme {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(
                Item(1, "Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(2,"Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(3,"Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA")
            )
        }
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewDarkSettingsScreen() {
    ArsenalTheme(useDarkTheme = true) {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(Item(1,"Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA"))
        }
        SettingsScreen(viewModel)
    }
}

@Preview
@Composable
fun PreviewMultiItemsDarkSettingsScreen() {
    ArsenalTheme(useDarkTheme = true) {
        val viewModel = SettingsViewModel().apply {
            items.value = mutableListOf(
                Item(1,"Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(2,"Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA"),
                Item(3,"Meu Item", "Aug. 2022", "Sept. 2022", "AAAAA")
            )
        }
        SettingsScreen(viewModel)
    }
}
