package com.ilizma.presentation.ui.content.scheduledetail

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.ilizma.domain.entity.base.Failure
import com.ilizma.presentation.R
import com.ilizma.presentation.entity.mapper.schedule.ScheduleUI
import com.ilizma.presentation.extensions.handleNormalFailure
import com.ilizma.presentation.extensions.viewModel
import dagger.Lazy
import kotlinx.android.synthetic.main.fragment_schedule_detail.*
import javax.inject.Inject

class ScheduleDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    private lateinit var scheduleDetailViewModel: ScheduleDetailViewModel

    override var fragmentLayout = R.layout.fragment_schedule_detail

    private val args: ScheduleDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        setUpRecyclerView()
        setUpViewModel()
    }

    private fun setUpToolBar() {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.title = args.day
    }

    private fun setUpRecyclerView() {
        scheduleDetailRv.adapter = ScheduleDetailAdapter()
    }

    private fun setUpViewModel() {
        scheduleDetailViewModel = viewModel(viewModelFactory.get()) {

            getSchedule(args.dayInt)

            observe(ldLoading, ::showAdaptersShimmers)

            observe(ldScheduleUIList, ::addItemsToAdapter)

            observe(ldFailure, ::showFailure)

        }
    }

    private fun showAdaptersShimmers(show: Boolean) {
        if (show) {
            (scheduleDetailRv.adapter as ScheduleDetailAdapter).addLoadingPlaceholder(5)
            addAnimationToRv()
        }
    }

    private fun addItemsToAdapter(scheduleUIList: List<ScheduleUI>) {
        (scheduleDetailRv.adapter as ScheduleDetailAdapter).addItemsToList(scheduleUIList)
        addAnimationToRv()
    }

    private fun addAnimationToRv() {
        scheduleDetailRv.layoutAnimation = AnimationUtils.loadLayoutAnimation(
            context,
            R.anim.recycler_view_animation
        )
        scheduleDetailRv.scheduleLayoutAnimation()
    }

    private fun showFailure(failure: Failure) {
        handleNormalFailure(failure)
        (scheduleDetailRv.adapter as ScheduleDetailAdapter).addError {
            dismissSnackbar()
            failure.retryAction()
        }
    }

}