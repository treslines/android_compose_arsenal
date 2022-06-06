package br.com.progdeelite.compose.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.progdeelite.compose.R
import br.com.progdeelite.compose.ui.theme.ArsenalTheme

@Composable
fun ArsenalIconImage(
    @DrawableRes iconLightId: Int,
    iconColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.onBackground,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(38.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(iconLightId),
            contentDescription = "",
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.Center)
                .clickable { onClick() },
            colorFilter = ColorFilter.tint(
                iconColor
            )
        )
    }
}

@Preview
@Composable
fun IconImageLightPreview() {
    ArsenalTheme {
        ArsenalIconImage(
            R.drawable.ic_airplanemode,
        )
    }
}

@Preview
@Composable
fun IconImageDarkPreview() {
    ArsenalTheme(useDarkTheme = true) {
        ArsenalIconImage(
            R.drawable.ic_airplanemode,
        )
    }
}