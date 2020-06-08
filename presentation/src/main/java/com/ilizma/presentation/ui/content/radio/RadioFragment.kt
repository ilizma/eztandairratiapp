package com.ilizma.presentation.ui.content.radio

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.*
import com.ilizma.presentation.ui.base.BaseFragment
import com.ilizma.presentation.ui.content.MainActivity
import dagger.Lazy
import kotlinx.android.synthetic.main.fragment_radio.*
import javax.inject.Inject

class RadioFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    private lateinit var radioViewModel: RadioViewModel

    override var fragmentLayout = R.layout.fragment_radio

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        setUpUI()
        setUpViewModel()
    }

    private fun setUpToolBar() {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.title_radio)
    }

    private fun setUpUI() {
        playStopBtn.setOnReactiveClickListener {
            if (radioViewModel.ldIsPlaying.value!!.not()) {
                radioViewModel.start()
            } else {
                radioViewModel.stop()
            }
        }
    }

    private fun setUpViewModel() {
        radioViewModel = viewModel(viewModelFactory.get()) {

            observe(ldIsPlaying) {
                manageButtonIcon(it)
                if (it) showLoading(false)
            }

            observe(ldRadioLoading, ::showLoading)

            observe(ldFailure, ::handleNormalFailure)

            observe(ldNetworkError, ::showNetworkError)

        }
    }

    private fun manageButtonIcon(isPlaying: Boolean) {
        if (isPlaying) {
            playStopBtn.setImageResource(R.drawable.ic_stop)
        } else {
            playStopBtn.setImageResource(R.drawable.ic_play)
        }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            progressBar.visible()
            playStopBtn.invisible()
        } else {
            playStopBtn.visible()
            progressBar.gone()
        }
    }

    private fun showNetworkError(show: Boolean) {
        if (show) {
            showSnackbarWithRes(R.string.unknown_error, R.string.retry) { radioViewModel.start() }
        }
    }

}