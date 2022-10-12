package com.ilizma.schedule.view.bind.factory

import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.view.bind.ProgramItemBinder
import com.ilizma.schedule.view.bind.ProgramItemBinderImp
import com.ilizma.schedule.view.bind.ProgramLoadingBinder
import com.ilizma.schedule.view.bind.ProgramLoadingBinderImp

class ProgramBinderFactory {

    fun createLoading(
    ): ProgramLoadingBinder = ProgramLoadingBinderImp()

    fun createItem(
    ): ProgramItemBinder<ProgramType.Item> = ProgramItemBinderImp()

}