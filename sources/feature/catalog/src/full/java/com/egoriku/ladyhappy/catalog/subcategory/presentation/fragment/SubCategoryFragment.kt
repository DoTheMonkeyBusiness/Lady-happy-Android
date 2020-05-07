package com.egoriku.ladyhappy.catalog.subcategory.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.databinding.FragmentCatalogBinding
import com.egoriku.ladyhappy.catalog.subcategory.presentation.SubCategoriesViewModel
import com.egoriku.ladyhappy.catalog.subcategory.presentation.SubcategoryScreenState
import com.egoriku.ladyhappy.catalog.subcategory.presentation.SubcategoryScreenState.Success
import com.egoriku.ladyhappy.catalog.subcategory.presentation.adapter.controller.CatalogController
import com.egoriku.ladyhappy.catalog.subcategory.presentation.adapter.decorator.MarginItemDecoration
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.toast
import com.egoriku.ladyhappy.extensions.visible
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import kotlin.properties.Delegates

const val ARGUMENT_CATEGORY_ID = "category_id"

class SubCategoryFragment : Fragment(R.layout.fragment_catalog) {

    private val binding: FragmentCatalogBinding by viewBinding()

    private val catalogViewModel: SubCategoriesViewModel by lifecycleScope.viewModel(this) {
        parametersOf(arguments?.getInt(ARGUMENT_CATEGORY_ID))
    }

    private var catalogController: CatalogController by Delegates.notNull()

    private val catalogAdapter = EasyAdapter().apply {
        setFirstInvisibleItemEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catalogController = CatalogController {
            toast("Item ${it.name} was clicked")
        }

        binding.catalogRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catalogAdapter
            addItemDecoration(MarginItemDecoration(context.resources.getDimension(R.dimen.adapter_item_catalog_margin).toInt()))
        }

        catalogViewModel.subcategoryItems.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun render(screenState: SubcategoryScreenState) {
        when (screenState) {
            is Success -> {
                binding.noItemsView.gone()
                catalogAdapter.setItems(
                        ItemList.create()
                                .addAll(screenState.screenData, catalogController)
                )
            }
            is Error -> {
                binding.noItemsView.visible()
            }
        }
    }
}