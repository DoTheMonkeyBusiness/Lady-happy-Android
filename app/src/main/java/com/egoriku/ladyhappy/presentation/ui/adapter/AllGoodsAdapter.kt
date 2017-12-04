package com.egoriku.ladyhappy.presentation.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import com.egoriku.ladyhappy.presentation.ui.adapter.model.BaseDisplayableItem
import com.egoriku.ladyhappy.presentation.ui.adapter.model.CategoriesAdapterDelegate
import com.egoriku.ladyhappy.presentation.ui.adapter.model.SectionType
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class AllGoodsAdapter(val activity: Activity?) : ListDelegationAdapter<List<BaseDisplayableItem>>() {

    private var list: List<BaseDisplayableItem> = mutableListOf()

    init {
        delegatesManager.apply {
            addDelegate(CategoriesAdapterDelegate(activity))
        }
    }

    @SuppressLint("SwitchIntDef")
    fun setItems(@SectionType type: Int, newDataList: List<BaseDisplayableItem>) {
        when (type) {
            SectionType.CATEGORIES -> {
                setItems(newDataList)
                notifyDataSetChanged()
            }
        }
    }
}