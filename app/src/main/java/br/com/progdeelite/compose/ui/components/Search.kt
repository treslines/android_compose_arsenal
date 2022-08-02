package br.com.progdeelite.compose.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import br.com.progdeelite.compose.R
import br.com.progdeelite.compose.ui.theme.ArsenalThemeExtended

// 0) COMO CRIAR O VIEW MODEL DO CAMPO DE BUSCA
// 1) COMO CRIAR UM ICON PADRÃO REUTILIZÁVEL
// 2) COMO CRIAR OS ICONES DE BUSCA PARTINDO DO ICONE PADRÃO
// 3) COMO CRIAR UM CAMPO DE BUSCA E CONFIGURAR O CAMPO DE TEXTO
// 4) COMO CRIAR O COMPONENTE DE BUSCA USANDO O CAMPO DE BUSCA


// 0) COMO CRIAR O VIEW MODEL DO CAMPO DE BUSCA
class SearchViewModel : ViewModel() {

    private val _isShowingSearchField: MutableState<Boolean> = mutableStateOf(false)
    val isShowingSearchField: State<Boolean> = _isShowingSearchField

    private val _currentSearchText: MutableState<String> = mutableStateOf(value = "")
    val currentSearchText: State<String> = _currentSearchText

    fun showSearchField(show: Boolean) {
        _isShowingSearchField.value = show
    }

    fun setCurrentSearchText(newText: String) {
        _currentSearchText.value = newText
    }

}

// 4) COMO CRIAR O COMPONENTE DE BUSCA USANDO O CAMPO DE BUSCA
@Composable
fun SearchableTopBar(
    isShowingSearchField: Boolean,
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit,
    onSearchIconClicked: () -> Unit
) {
    when (isShowingSearchField) {
        true -> SearchTopBar(
            currentSearchText = currentSearchText,
            onSearchTextChanged = onSearchTextChanged,
            onSearchDeactivated = onSearchDeactivated,
            onSearchDispatched = onSearchDispatched
        )
        false -> HomeTopBar(topBarNameId = R.string.app_name, onSearchIconClicked = onSearchIconClicked)
    }
}

// 3) COMO CRIAR UM CAMPO DE BUSCA E CONFIGURAR O CAMPO DE TEXTO
@Composable
fun SearchTopBar(
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = currentSearchText,
            onValueChange = { onSearchTextChanged(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Digite aqui...",
                    color = Color.White
                )
            },
            textStyle = TextStyle( fontSize = ArsenalThemeExtended.typography.body1.fontSize),
            singleLine = true,
            leadingIcon = { SearchLeadingIcon() },
            trailingIcon = { SearchTrailingIcon {
                                if (currentSearchText.isNotEmpty()) onSearchTextChanged("") else onSearchDeactivated() }
                           },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchDispatched(currentSearchText) } ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}

@Composable
fun HomeTopBar(@StringRes topBarNameId: Int = R.string.empty, onSearchIconClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = topBarNameId) ) },
        actions = { SearchIcon(action = onSearchIconClicked) }
    )
}

// 2) COMO CRIAR OS ICONES DE BUSCA PARTINDO DO ICONE PADRÃO
@Composable
fun SearchIcon(action: () -> Unit = {}) {
    DefaultIcon(
        searchIcon = Icons.Filled.Search,
        contentDescription = "Search Icon",
        onIconClickAction = action
    )
}

@Composable
fun SearchLeadingIcon(action: () -> Unit = {}) {
    DefaultIcon(
        modifier = Modifier.alpha(ContentAlpha.medium),
        onIconClickAction = action
    )
}

@Composable
fun SearchTrailingIcon(action: () -> Unit = {}) {
    DefaultIcon(
        searchIcon = Icons.Default.Close,
        contentDescription = "Deactivate Search Icon",
        onIconClickAction = action
    )
}

// 1) COMO CRIAR UM ICON PADRÃO REUTILIZÁVEL
@Composable
fun DefaultIcon (
    modifier: Modifier = Modifier,
    searchIcon: ImageVector = Icons.Default.Search,
    iconColor: Color = Color.White,
    contentDescription: String = "Magnifier Search Icon",
    onIconClickAction: () -> Unit = {}
) {
    IconButton(
        modifier = modifier,
        onClick = onIconClickAction
    ) {
        Icon(
            imageVector = searchIcon,
            contentDescription = contentDescription,
            tint = iconColor
        )
    }
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar(R.string.app_name, onSearchIconClicked = {})
}

@Composable
@Preview
fun SearchTopBarPreview() {
    SearchTopBar(
        currentSearchText = "Texto de busca",
        onSearchTextChanged = {},
        onSearchDeactivated = {},
        onSearchDispatched = {}
    )
}