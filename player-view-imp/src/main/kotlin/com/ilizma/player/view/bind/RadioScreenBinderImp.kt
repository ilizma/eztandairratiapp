package com.ilizma.player.view.bind

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.ilizma.player.presentation.model.PlayerState
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.R
import com.ilizma.player.view.databinding.RadioScreenFragmentBinding
import com.ilizma.view.extensions.setOnReactiveClickListener
import com.ilizma.view.extensions.snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioScreenBinderImp(
    viewModelLazy: Lazy<RadioScreenViewModel>,
    private val lifecycleOwner: () -> LifecycleOwner,
) : RadioScreenBinder {

    private val viewModel: RadioScreenViewModel by viewModelLazy
    private lateinit var binding: RadioScreenFragmentBinding

    override fun bind(binding: RadioScreenFragmentBinding) {
        this.binding = binding
        setUpToolBar()
        setupObservers()
    }

    private fun setUpToolBar() {
        (binding.root.context as MainActivity).supportActionBar?.title = getString(R.string.title_radio)
    }

    private fun setupObservers() {
        viewModel.playerState.observe(
            lifecycleOwner(),
        ) { onState(it) }
    }

    private fun onState(
        state: PlayerState,
    ) {
        setupButtonListener(state)
        manageButtonIcon(state)
        showNetworkErrorIfNeeded(state)
    }

    private fun setupButtonListener(
        state: PlayerState,
    ) {
        binding.radioScreenB.setOnReactiveClickListener {
            when (state) {
                PlayerState.Stopped -> viewModel.onPlay()
                PlayerState.Playing -> viewModel.onStop()
            }
        }
    }

    private fun manageButtonIcon(
        state: PlayerState,
    ) {
        when (state) {
            PlayerState.Loading,
            PlayerState.Error,
            PlayerState.Stopped -> R.drawable.ic_play
            PlayerState.Playing -> R.drawable.ic_stop
        }.let { binding.radioScreenB.setImageResource(it) }
        binding.radioScreenPb.isVisible = state == PlayerState.Loading
        binding.radioScreenB.isInvisible = state == PlayerState.Loading
    }

    private fun showNetworkErrorIfNeeded(
        state: PlayerState,
    ) {
        when (state) {
            PlayerState.Error -> binding.root.snackbar(R.string.no_internet, R.string.retry) { viewModel.onPlay() }
        }
    }

}