@file:JvmName("StubExt")

package com.egoriku.ladyhappy.presentation.adapter.dsl

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.egoriku.corelib_kt.Constants
import com.egoriku.corelib_kt.dsl.colorCompat
import com.egoriku.ladyhappy.R

@Suppress("NO_TAIL_CALLS_FOUND")
tailrec fun View.toStub() {
    when (this) {
        is ViewGroup ->
            (0 until childCount)
                    .map { getChildAt(it) }
                    .forEach { it.toStub() }

        is TextView -> {
            setBackgroundColor(context.colorCompat(R.color.adapter_stub))
            text = Constants.EMPTY
        }

        is ImageView -> {
            setImageDrawable(null)
            setBackgroundColor(context.colorCompat(R.color.adapter_stub))
        }
    }
}