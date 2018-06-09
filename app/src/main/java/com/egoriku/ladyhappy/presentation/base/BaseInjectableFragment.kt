package com.egoriku.ladyhappy.presentation.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.corelib_kt.arch.BaseFragment
import com.egoriku.corelib_kt.dsl.inflate
import dagger.android.support.AndroidSupportInjection

abstract class BaseInjectableFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : BaseFragment<V, P>() {

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            container?.inflate(provideLayout())

    @LayoutRes
    abstract fun provideLayout(): Int
}