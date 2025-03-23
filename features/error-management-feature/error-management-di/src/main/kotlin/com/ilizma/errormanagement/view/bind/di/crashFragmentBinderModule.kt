package com.ilizma.errormanagement.view.bind.di

import androidx.fragment.app.Fragment
import com.ilizma.errormanagement.view.bind.CrashFragmentBinder
import com.ilizma.errormanagement.view.bind.CrashFragmentBinderImp
import com.ilizma.errormanagement.view.fragment.CrashFragment
import org.koin.core.module.Module
import org.koin.dsl.module

val crashFragmentBinderModule: Module = module {

    scope<CrashFragment> {
        scoped<CrashFragmentBinder> {
            @Suppress("UndeclaredKoinUsage")
            CrashFragmentBinderImp(activity = get<Fragment>().requireActivity())
        }
    }

}