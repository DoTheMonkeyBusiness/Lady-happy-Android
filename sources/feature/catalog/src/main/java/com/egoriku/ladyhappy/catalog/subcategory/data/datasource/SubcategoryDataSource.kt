package com.egoriku.ladyhappy.catalog.subcategory.data.datasource

import com.egoriku.core.IFirebase
import com.egoriku.ladyhappy.catalog.subcategory.data.entity.SubCategoryEntity
import com.egoriku.network.firestore.awaitGet

class SubcategoryDataSource(private val firebase: IFirebase) {

    suspend fun fetch(categoryId: Int): List<SubCategoryEntity> {
        return firebase.firebaseFirestore
                .collection("subcategories")
                .whereEqualTo("categoryId", categoryId)
                .orderBy("subCategoryId")
                .awaitGet()
    }
}