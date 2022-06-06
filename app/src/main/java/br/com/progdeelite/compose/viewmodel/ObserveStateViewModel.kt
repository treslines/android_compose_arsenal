package br.com.progdeelite.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// VIDEO COMO CRIAR E OBSERVAR ESTADOS EM JETPACK COMPOSE:  XXXXXXXX
class ObserveStateViewModel : ViewModel() {

    val loadingStateFlow = MutableStateFlow(false) // IMPORTANT QUE SEJA "FLOW"

    fun setLoadingState(loading: Boolean) {
        viewModelScope.launch {
            loadingStateFlow.emit(loading)
        }
    }
}