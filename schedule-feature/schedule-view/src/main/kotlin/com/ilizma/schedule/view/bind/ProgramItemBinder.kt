package com.ilizma.schedule.view.bind

import com.ilizma.schedule.view.databinding.ProgramItemBinding

interface ProgramItemBinder<T> {

    fun bind(binding: ProgramItemBinding, program: T)

    fun unBind()

}