package br.com.progdeelite.compose.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.progdeelite.compose.R
import br.com.progdeelite.compose.ui.components.ArsenalButton
import br.com.progdeelite.compose.ui.components.ArsenalCircularProgressIndicator
import br.com.progdeelite.compose.ui.theme.ArsenalTheme
import br.com.progdeelite.compose.ui.theme.ArsenalThemeExtended
import br.com.progdeelite.compose.viewmodel.ObserveStateViewModel

// 1) COMO OBSERVAR ESTADOS EM COMPOSE COM VIEWMODEL
// 2) COMO CRIAR UM COMPONENTE REUTILIZÁVEL PARA LOADING SCREENS
// 3) COMO PASSAR ESTADO PARA COMPOSABLE E COMO ALTERA-LO (LOADING VIEW)
// 4) COMO INSTANCIAR O FLOW NO PREVIEW USANDO VIEW MODEL

@Composable
fun LoadingView(viewModel: ObserveStateViewModel) {

    val loading by viewModel.loadingStateFlow.collectAsState()

    when {
        loading -> {
            // ENQUANTO ESTIVER CARREGANDO, EXIBA LOADING COM TEXTO "AGUARDE!"
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ArsenalCircularProgressIndicator(Modifier, R.string.loading)
                ArsenalButton(
                    modifier = Modifier,
                    clickAction = { viewModel.setLoadingState(false) },
                    textId = R.string.exit_loading
                )
            }
        }
        else -> {
            // DO CONTRÁRIO EXITA O CONTEUDO DESEJADO...
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Aeeeeee! Consegui!!!",
                    style = ArsenalThemeExtended.typography.h1,
                )
                ArsenalButton(
                    modifier = Modifier,
                    clickAction = { viewModel.setLoadingState(true) },
                    textId = R.string.enter_loading
                )
            }
        }
    }
}

@Preview
@Composable
fun LoadingViewLightPreview() {
    ArsenalTheme(useDarkTheme = false) {
        LoadingView(ObserveStateViewModel().apply {
            loadingStateFlow.value = true
        })
    }
}

@Preview
@Composable
fun LoadingViewDarkPreview() {
    ArsenalTheme(useDarkTheme = true) {
        LoadingView(ObserveStateViewModel().apply {
            loadingStateFlow.value = false
        })
    }
}