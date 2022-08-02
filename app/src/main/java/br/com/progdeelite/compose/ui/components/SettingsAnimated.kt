package br.com.progdeelite.compose.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.progdeelite.compose.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.progdeelite.compose.navigation.AppGraph
import br.com.progdeelite.compose.ui.theme.ArsenalThemeExtended

// 1) COMO ANIMAR LISTAS
// 2) COMO OBSERVAR AS MUDANCAS QUE VÃO RE-DESENHAR O COMPONENTE
// 3) COMO MODELAR UMA ANIMAçÃO MAIS ELABORADA

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingsScreenAnimated(viewModel: SettingsViewModel) {

    val items by viewModel.items.collectAsState()
    val registerId by viewModel.registerId.collectAsState()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 32.dp)
    ) {
        Text(
            text = "Configurações", // Ou passa na assinatura Metódp ou pega da tradução!
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
                            .padding(start = 32.dp, top = 16.dp, bottom = 16.dp)
                    )
                }
                items(items = items) { item ->
                    SettingsItemAnimated(item) { viewModel.navigateTo(item.id) }// ação a ser executada
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
fun SettingsItemAnimated(
    item: Item,
    onItemClicked: () -> Unit = {},
) {

    val expanded = remember { mutableStateOf(false) }

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

        Column(modifier = Modifier.weight(1F).clickable { onItemClicked() }) {
            Text(
                text = item.title,
                style = ArsenalThemeExtended.typography.h2
            )

            // ANIMACAO SIMPLES
//            AnimatedVisibility(visible = expanded.value) {
//                Column {
//                    Text(
//                        text = item.activated,
//                    )
//                    Text(
//                        text = item.lastLogin,
//                    )
//                }
//            }

            // ANIMACÃO ELABORADA
            AnimatedVisibility(
                visible = expanded.value,
                enter = fadeIn(animationSpec = tween(250)) +
                        expandVertically (
                            animationSpec = tween(500,
                                easing = FastOutLinearInEasing )),
                exit = fadeOut(animationSpec = tween(250)) +
                        shrinkVertically (
                            animationSpec = tween(500,
                                easing = FastOutSlowInEasing)))
            {
                Column {
                    Text(
                        text = item.activated,
                    )
                    Text(
                        text = item.lastLogin,
                    )
                }
            }
        }
        Box(modifier = Modifier.width(50.dp)) {
            TextButton(
                onClick = { expanded.value = !expanded.value },
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(2.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    imageVector = if(expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "delete icon", // NÃO FACA ASSIM, TRADUZA OU PASSE POR REFERÊNCIA.
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}
