package com.ilizma.main.view.widget.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable
sealed interface PlayerState {

    @Serializable
    object Playing : PlayerState

    @Serializable
    object Loading : PlayerState

    @Serializable
    object Stopped : PlayerState

    @Serializable
    sealed interface Error : PlayerState {

        @Serializable
        object Malformed : Error

        @Serializable
        object Unsupported : Error

        @Serializable
        object Timeout : Error

        @Serializable
        object Network : Error

        @Serializable
        object MediaDisconnected : Error

        @Serializable
        object Unknown : Error

        @Serializable
        object GenericError : Error

    }

}

private val module = SerializersModule {
    polymorphic(PlayerState::class) {
        subclass(PlayerState.Playing::class)
        subclass(PlayerState.Loading::class)
        subclass(PlayerState.Stopped::class)

        subclass(PlayerState.Error.Malformed::class)
        subclass(PlayerState.Error.Unsupported::class)
        subclass(PlayerState.Error.Timeout::class)
        subclass(PlayerState.Error.Network::class)
        subclass(PlayerState.Error.MediaDisconnected::class)
        subclass(PlayerState.Error.Unknown::class)
        subclass(PlayerState.Error.GenericError::class)
    }

    polymorphic(PlayerState.Error::class) {
        subclass(PlayerState.Error.Malformed::class)
        subclass(PlayerState.Error.Unsupported::class)
        subclass(PlayerState.Error.Timeout::class)
        subclass(PlayerState.Error.Network::class)
        subclass(PlayerState.Error.MediaDisconnected::class)
        subclass(PlayerState.Error.Unknown::class)
        subclass(PlayerState.Error.GenericError::class)
    }
}

val json = Json {
    serializersModule = module
    classDiscriminator = "type"
}