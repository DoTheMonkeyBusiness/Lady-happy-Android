package com.egoriku.ladyhappy.catalog.categories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.catalog.categories.domain.usecase.TabUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RootCatalogViewModel(private val tabUseCase: TabUseCase) : ViewModel() {

    private val _screenModel = MutableStateFlow<RootScreenModel>(RootScreenModel.Progress())

    val screenModel: StateFlow<RootScreenModel>
        get() = _screenModel

    init {
        viewModelScope.launch {
            _screenModel.value = tabUseCase.loadTabs()
        }
    }
}