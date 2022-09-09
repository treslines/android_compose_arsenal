package br.com.progdeelite.compose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.progdeelite.compose.ui.theme.ArsenalTheme

@Composable
fun TopButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit = {}) {
    OutlinedButton(modifier = modifier, onClick = onClick) {
        Text(text = text)
    }
}

@Composable
fun BottomButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit = {}) {
    Button(modifier = modifier, onClick = onClick) {
        Text(text = text)
    }
}

@Preview()
@Composable
fun DialogButtonsPreview() {
    ArsenalTheme {
        Column {
            TopButton(text = "Botão superior")
            Spacer(modifier = Modifier.height(12.dp))
            BottomButton(text = "Botão inferior")
        }
    }
}