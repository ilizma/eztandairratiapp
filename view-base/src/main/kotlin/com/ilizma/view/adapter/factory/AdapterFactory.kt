package com.ilizma.view.adapter.factory

import com.ilizma.view.adapter.Adapter

interface AdapterFactory<T> {

    fun create(): Adapter<T>

}