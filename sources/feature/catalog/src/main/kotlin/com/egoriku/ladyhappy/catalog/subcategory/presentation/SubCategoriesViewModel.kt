package com.egoriku.ladyhappy.catalog.subcategory.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.catalog.subcategory.domain.usecase.CatalogUseCase
import com.egoriku.ladyhappy.network.ResultOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SubCategoriesViewModel(private val catalogUseCase: CatalogUseCase, private val categoryId: Int) : ViewModel() {

    private val _subcategoryItems = MutableStateFlow<SubcategoryScreenState>(SubcategoryScreenState.Loading)

    val subcategoryItems: StateFlow<SubcategoryScreenState>
        get() = _subcategoryItems

    init {
        viewModelScope.launch {
            when (val result = catalogUseCase.loadSubCategories(categoryId)) {
                is ResultOf.Success -> _subcategoryItems.value = SubcategoryScreenState.Success(result.value)
                is ResultOf.Failure -> _subcategoryItems.value = SubcategoryScreenState.Error
            }
        }
    }
}