package br.com.progdeelite.compose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.progdeelite.compose.R
import br.com.progdeelite.compose.ui.theme.ArsenalTheme

// 1) COMO CRIAR O BOTTOM NAVIGATION
// 2) COMO CRIAR O PREVIEW
// 3) COMO USAR O SCAFFOLD PARA CRIAR TELA


// 1) COMO CRIAR O BOTTOM NAVIGATION
@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
) {
    BottomNavigation(
        // MATERIAL 3 - TEM VIDEO AQUI NO CANAL QUE ENSINA ISSO JÁ!
        backgroundColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.app_name))
            },
            selected = true,
            onClick = onHomeClick
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    // ALTERNATIVA de icon, embora svg seja melhor
                    // painter = painterResource(id = R.drawable.ic_droid),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.profile_name))
            },
            selected = false, // quer saber como torna a seleção iterativa: https://youtu.be/UJpwxg4tv_U
            onClick = onProfileClick
        )
    }
}

// 2) COMO CRIAR O PREVIEW
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun BottomNavPreview() {
    ArsenalTheme {
        Scaffold(
            bottomBar = { BottomNav() }
        ) { padding ->
            HomeContent(Modifier.padding(padding))
        }
    }
}

