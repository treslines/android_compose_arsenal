package br.com.progdeelite.compose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.progdeelite.compose.ui.theme.ArsenalTheme

@Composable
fun DropDownComponent(
    title: String,
    description: String,
    minimizeText: String,
    maximizeText: String,
    contentDescription: String,
    expanded: Boolean,
    expandAction: () -> Unit
) {
    Row(modifier = Modifier.padding(top= 0.dp, start= 24.dp, end=24.dp, bottom = 0.dp)) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 14.dp)
        ) {
            Text(text = title, fontWeight = FontWeight.Bold)
            // DESCRIçÃO DINÂMICA
            if (expanded) {
                Text(text = description)
            }
        }
        // ICON E TEXTOS SÃO DINÂMICOS
        TextButton(
            onClick = expandAction
        ) {
            Icon(
                // SE QUISER MAIS ICONS, USE UMA LIB: material-icons-extended (VIDE build.gradle)
                if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = contentDescription
            )
            Text(
                text = if (expanded) minimizeText else maximizeText,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun DropDownLightPreview() {
    ArsenalTheme {
        DropDownComponent(
            "titulo",
            "descrição",
            "Menos",
            "Mais",
            "contentDescription",
            false) {}
    }
}

@Preview
@Composable
fun DropDownDarkPreview() {
    ArsenalTheme(useDarkTheme = true) {
        DropDownComponent(
            "titulo",
            "descrição",
            "Menos",
            "Mais",
            "contentDescription",
            true) {}
    }
}