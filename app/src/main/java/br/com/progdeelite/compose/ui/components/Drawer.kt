package br.com.progdeelite.compose.ui.components

import android.content.Context
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.progdeelite.compose.BuildConfig
import br.com.progdeelite.compose.R
import br.com.progdeelite.compose.ui.theme.ArsenalTheme


// 1) COMO CRIAR UM DRAWER SIMPLES (FAKE CONTENT PAGE)
// 2) COMO REAGIR AOS CLIQUES
// 3) QUANDO USAR O "rememberCoroutineScope"


private val screens =
    listOf("Home", "Minhas Viagens", "Favoritos", "Alertas Viagens", "Minha Conta")

@Composable
fun Drawer(modifier: Modifier = Modifier, onClick: ((context:Context, selectionId:Int)-> Unit)? = null ) {
    val context = LocalContext.current
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        Image(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(id = R.drawable.ic_droid),
            contentDescription = stringResource(id = R.string.app_name)
        )
        screens.forEachIndexed { index, screen ->
            Spacer(Modifier.height(12.dp))
            Text(
                modifier = if(onClick != null) Modifier.clickable { onClick(context,index) } else Modifier,
                text = screen,
                style = MaterialTheme.typography.h6
            )
        }
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = "App Version: ${BuildConfig.VERSION_NAME}",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    ArsenalTheme {
        Drawer()
    }
}