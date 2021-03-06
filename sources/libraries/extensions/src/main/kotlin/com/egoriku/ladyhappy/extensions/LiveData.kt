package com.egoriku.ladyhappy.extensions

import androidx.lifecycle.LiveData

inline fun <reified T : Any> LiveData<T>.valueOrThrow() = requireNotNull(value)