package br.com.progdeelite.compose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.progdeelite.compose.ui.theme.ArsenalTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

// 1) CREATE EXPANDABLE COMPONENT COM ESTADO LOCAL
// 2) ENTENDER QUANDO LEVANTAR O ESTADO (HOISTING) E PORQUE
// 3) ENTENDER PORQUE POR O ESTADO NO VIEW MODEL TEM MAIS VANTAGENS (SOBREVIVE O LIFE CYCLE)

@Composable
fun ExpandableView() {
    // LOCAL STATE (ISSO NÃO É HOISTING, MAS TBM É LEGAL E BOM CONHECER)
    val expanded = remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(vertical = 0.dp, horizontal = 8.dp)
    ) {
        DropDownComponent(
            title="Estado Simples", // NÃO FACA ASSIM, INTERNACIONALIZE! VEJA O VIDEO
            description = "Conteúdo com toda a descrição que eu queira exibir nesta área!" ,
            minimizeText = "Minimizar",
            maximizeText = "Expandir",
            contentDescription ="Botão de minimizar ou maximizar o conteúdo!",
            expanded.value) { expanded.value = !expanded.value }
    }
}

@Composable
fun ExpandableSavableView() {
    // NÃO É HOISTING, MAS ESTA RESISTINDO A MUDANçAS DE ESTADO
    val expanded = rememberSaveable { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(vertical = 0.dp, horizontal = 8.dp)
    ) {
        DropDownComponent(
            title="Estado Resistente", // NÃO FACA ASSIM, INTERNACIONALIZE! VEJA O VIDEO
            description = "Conteúdo com toda a descrição que eu queira exibir nesta área!" ,
            minimizeText = "Minimizar",
            maximizeText = "Expandir",
            contentDescription ="Botão de minimizar ou maximizar o conteúdo!",
            expanded.value) { expanded.value = !expanded.value }
    }
}

// +----------------------------------------------+
// | HOISTING - ELEVANDO O ESTADO PARA O MODELO   |
// +----------------------------------------------+
class ExpandableViewModel : ViewModel() {
    private val _state = MutableStateFlow(false)
    val state: StateFlow<Boolean>
        get() = _state

    fun toggleState(newState: Boolean) {
        viewModelScope.launch {
            _state.emit(newState)
        }
    }
}

@Composable
fun ExpandableViewHoisted(viewModel: ExpandableViewModel) {

    // ISSO É HOISTING! ELEVAMOS O ESTADO PARA O VIEW MODEL,
    // VIVE DURANTE O CICLO DE VIDA, POREM NÃO RESISTE (AINDA) A MUDANçA DE CONFIGURAçÃO
    val expanded = viewModel.state.collectAsState().value

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(vertical = 0.dp, horizontal = 8.dp)
    ) {
        DropDownComponent(
            title="Hoisting VM Simples", // NÃO FACA ASSIM, INTERNACIONALIZE! VEJA O VIDEO
            description = "Descrição com toda a descrição que eu queira exibir nesta área!" ,
            minimizeText = "Menos",
            maximizeText = "Mais",
            contentDescription ="Icone de minimizar ou maximizar o conteúdo!",
            expanded) { viewModel.toggleState(!expanded)}
    }
}

// +--------------------------------------------------------------+
// | Usando um view model que resiste a mudança de configuração   |
// +--------------------------------------------------------------+
@Composable
fun ExpandableViewHoistedSavable(viewModel: SaveableViewModel) {

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(vertical = 0.dp, horizontal = 8.dp)
    ) {
        DropDownComponent(
            title="Hoisting VM Configuração", // NÃO FACA ASSIM, INTERNACIONALIZE! VEJA O VIDEO
            description = "Descrição com toda a descrição que eu queira exibir nesta área!" ,
            minimizeText = "Menos",
            maximizeText = "Mais",
            contentDescription ="Icone de minimizar ou maximizar o conteúdo!",
            viewModel.saveableMutableComposeState) { viewModel.triggerComposeState(!viewModel.saveableMutableComposeState)}
    }
}

// +--------------------------------------------------------+
// | ViewModel que resiste a mudanças de configurações      |
// +--------------------------------------------------------+
class SaveableViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var saveableMutableComposeState by SaveableComposeState(savedStateHandle, "ComposeKey", false)
        private set


    fun triggerComposeState(newState: Boolean) {
        saveableMutableComposeState = newState
    }
}

// +--------------------------------------------------------+
// | Modelagem do estado generico para view models          |
// +--------------------------------------------------------+
class SaveableComposeState<T>(
    private val savedStateHandle: SavedStateHandle,
    private val key: String,
    defaultValue: T
) {
    private var _state by mutableStateOf(savedStateHandle.get<T>(key) ?: defaultValue)

    operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): T {
        return _state
    }

    operator fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: T
    ) {
        _state = value
        savedStateHandle[key] = value
    }
}

@Preview
@Composable
fun ExpandableViewLightPreview() {
    ArsenalTheme {
        ExpandableView()
    }
}

@Preview
@Composable
fun ExpandableViewDarkPreview() {
    ArsenalTheme(useDarkTheme = true) {
        ExpandableView()
    }
}