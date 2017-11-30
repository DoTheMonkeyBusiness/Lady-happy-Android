package com.egoriku.ladyhappy.presentation.ui.adapter.model

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.egoriku.corelib_kt.extensions.inflate
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.domain.models.CategoryModel
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.adapter_categories.view.*

class CategoriesAdapterDelegate(val activity: Activity?) : AdapterDelegate<List<BaseDisplayableItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CategoriesViewHolder(parent.inflate(R.layout.adapter_categories))
    }

    override fun onBindViewHolder(items: List<BaseDisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val categoriesItem = items[position] as CategoryModel

        (holder as CategoriesViewHolder).title.text = categoriesItem.title

    }

    override fun isForViewType(items: List<BaseDisplayableItem>, position: Int) = items[position] is CategoryModel

    inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.title
    }
}