package com.ilizma.schedule.view.router.id

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ilizma.schedule.flow.navigator.ScheduleDetailBackCloseNavigator
import com.ilizma.schedule.flow.router.ScheduleDetailRouterImp
import com.ilizma.schedule.presentation.viewmodel.factory.di.SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object ScheduleDetailRouterModule {

    @Provides
    fun provideScheduleDetailRouter(
        fragment: Fragment,
        navigator: ScheduleDetailBackCloseNavigator,
        @Named(SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED) viewModelProviderFactory: ViewModelProvider.Factory,
    ): ScheduleDetailRouter = ScheduleDetailRouterImp(
        lifecycleOwner = { fragment.viewLifecycleOwner },
        onBackPressedDispatcher = fragment.requireActivity().onBackPressedDispatcher,
        viewModelLazy = fragment.viewModels { viewModelProviderFactory },
        navigator = navigator,
    )

}