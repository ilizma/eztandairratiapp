package com.ilizma.player.presentation.mapper

import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.presentation.model.PlayerState as PresentationPlayerState
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class PlayerStateMapperTest {

    private lateinit var mapper: PlayerStateMapper

    @BeforeTest
    fun setup() {
        mapper = PlayerStateMapper()
    }

    @Test
    fun `given Playing PlayerState, when from, then result should be the expected Playing PresentationPlayerState`() {
        // given
        val data = PlayerState.Playing
        val expected = PresentationPlayerState.Playing

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Loading PlayerState, when from, then result should be the expected Loading PresentationPlayerState`() {
        // given
        val data = PlayerState.Loading
        val expected = PresentationPlayerState.Loading

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Stopped PlayerState, when from, then result should be the expected Stopped PresentationPlayerState`() {
        // given
        val data = PlayerState.Stopped
        val expected = PresentationPlayerState.Stopped

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Malformed Error PlayerState, when from, then result should be the expected Malformed Error PresentationPlayerState`() {
        // given
        val data = PlayerState.Error.Malformed
        val expected = PresentationPlayerState.Error.Malformed

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Unsupported Error PlayerState, when from, then result should be the expected Unsupported Error PresentationPlayerState`() {
        // given
        val data = PlayerState.Error.Unsupported
        val expected = PresentationPlayerState.Error.Unsupported

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Timeout Error PlayerState, when from, then result should be the expected Timeout Error PresentationPlayerState`() {
        // given
        val data = PlayerState.Error.Timeout
        val expected = PresentationPlayerState.Error.Timeout

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Network Error PlayerState, when from, then result should be the expected Network Error PresentationPlayerState`() {
        // given
        val data = PlayerState.Error.Network
        val expected = PresentationPlayerState.Error.Network

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given MediaDisconnected Error PlayerState, when from, then result should be the expected MediaDisconnected Error PresentationPlayerState`() {
        // given
        val data = PlayerState.Error.MediaDisconnected
        val expected = PresentationPlayerState.Error.MediaDisconnected

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Unknown Error PlayerState, when from, then result should be the expected Unknown Error PresentationPlayerState`() {
        // given
        val data = PlayerState.Error.Unknown
        val expected = PresentationPlayerState.Error.Unknown

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given GenericError Error PlayerState, when from, then result should be the expected GenericError Error PresentationPlayerState`() {
        // given
        val data = PlayerState.Error.GenericError
        val expected = PresentationPlayerState.Error.GenericError

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}