package br.com.progdeelite.compose.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.progdeelite.compose.R
import br.com.progdeelite.compose.ui.theme.ArsenalTheme

@Composable
fun ArsenalButton(
    modifier: Modifier,
    clickAction: () -> Unit,
    @StringRes textId: Int
) {
    Button(modifier = modifier, onClick = clickAction) {
        Text(text = stringResource(id = textId))
    }
}

@Composable
fun ArsenalButtonRow(
    modifier: Modifier, // single button modifier
    positiveAction: (() -> Unit)? = null,
    negativeAction: (() -> Unit)? = null,
    neutralAction: (() -> Unit)? = null,
    @StringRes positiveTextId: Int? = null,
    @StringRes negativeTextId: Int? = null,
    @StringRes neutralTextId: Int? = null,
) {
    Row(
        Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        negativeAction?.let { clickAction ->
            negativeTextId?.let { textId ->
                ArsenalButton(modifier, clickAction, textId)
            }
        }
        if (negativeAction == null) Spacer(modifier = modifier)

        neutralAction?.let { clickAction ->
            neutralTextId?.let { textId ->
                ArsenalButton(modifier, clickAction, textId)
            }
        }
        if (neutralAction == null) Spacer(modifier = modifier)

        positiveAction?.let { clickAction ->
            positiveTextId?.let { textId ->
                ArsenalButton(modifier, clickAction, textId)
            }
        }
        if (positiveAction == null) Spacer(modifier = modifier)
    }
}


@Preview
@Composable
fun ArsenalButtonLightPreview() {
    ArsenalTheme(useDarkTheme = false) {
        ArsenalButton(
            modifier = Modifier.widthIn(min = 100.dp, max = 120.dp),
            {},
            R.string.btn_template_name
        )
    }
}

@Preview
@Composable
fun ArsenalButtonDarkPreview() {
    ArsenalTheme(useDarkTheme = true) {
        ArsenalButton(
            modifier = Modifier.widthIn(min = 100.dp, max = 120.dp),
            {},
            R.string.btn_template_name
        )
    }
}

@Preview
@Composable
fun ArsenalButtonRowLightPreview() {
    ArsenalTheme(useDarkTheme = false) {
        ArsenalButtonRow(
            modifier = Modifier.widthIn(min = 100.dp, max = 120.dp),
            positiveAction = {},
            positiveTextId = R.string.btn_template_name,
            neutralAction = {},
            neutralTextId = R.string.btn_template_name,
            negativeAction = {},
            negativeTextId = R.string.btn_template_name
        )
    }
}

@Preview
@Composable
fun ArsenalButtonRowDarkPreview() {
    ArsenalTheme(useDarkTheme = true) {
        ArsenalButtonRow(
            modifier = Modifier.widthIn(min = 100.dp, max = 120.dp),
            positiveAction = {},
            positiveTextId = R.string.btn_template_name,
            neutralAction = {},
            neutralTextId = R.string.btn_template_name,
            negativeAction = {},
            negativeTextId = R.string.btn_template_name
        )
    }
}