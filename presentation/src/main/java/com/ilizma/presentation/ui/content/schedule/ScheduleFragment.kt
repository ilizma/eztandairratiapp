package com.ilizma.presentation.ui.content.schedule

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ilizma.domain.entity.base.Failure
import com.ilizma.presentation.R
import com.ilizma.presentation.entity.mapper.day.DaysUI
import com.ilizma.presentation.extensions.handleNormalFailure
import com.ilizma.presentation.extensions.observe
import com.ilizma.presentation.extensions.viewModel
import com.ilizma.presentation.ui.base.BaseFragment
import com.ilizma.presentation.ui.content.MainActivity
import dagger.Lazy
import kotlinx.android.synthetic.main.fragment_schedule.*
import javax.inject.Inject

class ScheduleFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    private lateinit var scheduleViewModel: ScheduleViewModel

    override var fragmentLayout = R.layout.fragment_schedule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        setUpRecyclerView()
        setUpViewModel()
    }

    private fun setUpToolBar() {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.title_schedule)
    }

    private fun setUpRecyclerView() {
        scheduleRv.adapter = ScheduleAdapter { navigateToScheduleDetailFragment(it) }
    }

    private fun setUpViewModel() {
        scheduleViewModel = viewModel(viewModelFactory.get()) {

            observe(ldLoading, ::showAdaptersShimmers)

            observe(ldDaysUIList, ::addItemsToAdapter)

            observe(ldFailure, ::showFailure)

        }
    }

    private fun showAdaptersShimmers(show: Boolean) {
        if (show) {
            (scheduleRv.adapter as ScheduleAdapter).addLoadingPlaceholder(7)
            addAnimationToRv()
        }
    }

    private fun addItemsToAdapter(daysUIList: List<DaysUI>) {
        (scheduleRv.adapter as ScheduleAdapter).addItemsToList(daysUIList)
        addAnimationToRv()
    }

    private fun addAnimationToRv() {
        scheduleRv.layoutAnimation = AnimationUtils.loadLayoutAnimation(
            context,
            R.anim.recycler_view_animation
        )
        scheduleRv.scheduleLayoutAnimation()
    }

    private fun showFailure(failure: Failure) {
        handleNormalFailure(failure)
        (scheduleRv.adapter as ScheduleAdapter).addError {
            dismissSnackbar()
            failure.retryAction()
        }
    }

    private fun navigateToScheduleDetailFragment(indexedDayPair: Pair<Int, String>) {
        findNavController().navigate(
            ScheduleFragmentDirections.actionScheduleFragmentToScheduleDetailFragment(
                dayInt = indexedDayPair.first + 1,
                day = indexedDayPair.second
            )
        )
    }

}