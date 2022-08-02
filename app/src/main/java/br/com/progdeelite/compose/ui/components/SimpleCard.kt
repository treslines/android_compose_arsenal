package br.com.progdeelite.compose.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.progdeelite.compose.ui.theme.ArsenalThemeExtended
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.progdeelite.compose.R
import br.com.progdeelite.compose.ui.theme.ArsenalTheme

// 1) COMO CRIAR UM COMPONENTE DE CARD REUTILIZÁVEL
// 2) COMO USAR ISSO NA PRÁTICA
@Composable
fun SimpleCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    action: () -> Unit = {}
) {
    Surface(
        shape = MaterialTheme.shapes.small, // ARREDONDA AS BORDAS
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(192.dp)
                .background(Color.LightGray)
                .clickable { action() }
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = stringResource(text),
                style = ArsenalThemeExtended.typography.h2,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun SimpleCardPreview() {
    ArsenalTheme {
        SimpleCard(
            text = R.string.app_name,
            drawable = R.drawable.ic_droid,
            modifier = Modifier.padding(8.dp)
        )
    }
}
