package com.ilizma.presentation.ui.content.schedule

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ilizma.presentation.R
import com.ilizma.presentation.entity.mapper.day.DaysMapperUI
import com.ilizma.presentation.ui.base.BaseFragment
import com.ilizma.presentation.ui.content.MainActivity
import com.ilizma.presentation.ui.content.schedule.adapter.ScheduleAdapter
import dagger.Lazy
import kotlinx.android.synthetic.main.fragment_schedule.*
import javax.inject.Inject

class ScheduleFragment : BaseFragment() {

    @Inject
    override lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    override var fragmentLayout = R.layout.fragment_schedule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        setUpRecyclerView()
    }

    private fun setUpToolBar() {
        (activity as MainActivity).supportActionBar?.title = "Schedule"
    }

    private fun setUpRecyclerView() {
        scheduleRv.adapter = ScheduleAdapter { navigateToScheduleDetailFragment(it) }

        val daysUIList =
            resources.getStringArray(R.array.days_array).map { DaysMapperUI().mapToUI(it) }
        (scheduleRv.adapter as ScheduleAdapter).addItemsToList(daysUIList)
        scheduleRv.layoutAnimation = AnimationUtils.loadLayoutAnimation(
            context,
            R.anim.recycler_view_animation
        )
        scheduleRv.adapter?.notifyDataSetChanged()
        scheduleRv.scheduleLayoutAnimation()
    }

    private fun navigateToScheduleDetailFragment(day: String) {
        findNavController().navigate(
            ScheduleFragmentDirections.actionScheduleFragmentToScheduleDetailFragment(day)
        )
    }

}