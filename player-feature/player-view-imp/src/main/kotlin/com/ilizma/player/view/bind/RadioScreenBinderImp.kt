package com.ilizma.player.view.bind

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.ilizma.player.presentation.model.PlayerState
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.databinding.RadioScreenFragmentBinding
import com.ilizma.resources.R
import com.ilizma.view.extensions.setOnReactiveClickListener
import com.ilizma.view.extensions.snackbar

class RadioScreenBinderImp(
    viewModelLazy: Lazy<RadioScreenViewModel>,
    private val lifecycleOwner: () -> LifecycleOwner,
) : RadioScreenBinder {

    private val viewModel: RadioScreenViewModel by viewModelLazy
    private lateinit var binding: RadioScreenFragmentBinding

    override fun bind(binding: RadioScreenFragmentBinding) {
        this.binding = binding
        setupObservers()
        createOptionsMenu()
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
                else -> { /* no-op */ }
            }
        }
    }

    private fun manageButtonIcon(
        state: PlayerState,
    ) {
        when (state) {
            PlayerState.Loading,
            is PlayerState.Error,
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
            is PlayerState.Error -> binding.root.snackbar(
                titleRes = errorRes(state),
                actionRes = R.string.retry
            ) { viewModel.onPlay() }
            else -> { /* no-op */ }
        }
    }

    private fun errorRes(
        errorState: PlayerState.Error
    ): Int = when (errorState) {
        PlayerState.Error.Malformed -> R.string.malformed_media
        PlayerState.Error.Unsupported -> R.string.unsupported_media
        PlayerState.Error.Timeout -> R.string.timeout_message
        PlayerState.Error.Network -> R.string.no_internet
        PlayerState.Error.MediaDisconnected -> R.string.media_disconnected
        PlayerState.Error.Unknown -> R.string.unknown_error
        PlayerState.Error.GenericError -> R.string.generic_error
    }

    private fun createOptionsMenu() {
        binding.radioScreenTb.inflateMenu(com.ilizma.cast.view.R.menu.cast_controller)
        binding.radioScreenTb.setOnMenuItemClickListener { true }
        viewModel.setUpMediaRouteButton(
            menu = binding.radioScreenTb.menu,
            menuResourceId = com.ilizma.cast.view.R.id.media_route_menu_item,
        )
    }

}