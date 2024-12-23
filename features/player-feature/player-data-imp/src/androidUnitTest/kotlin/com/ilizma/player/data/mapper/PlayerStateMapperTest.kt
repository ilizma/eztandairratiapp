package com.ilizma.player.data.mapper

import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.data.model.PlayerState as DataPlayerState
import com.ilizma.player.framework.model.PlayerState as FrameworkPlayerState
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class PlayerStateMapperTest {

    private lateinit var mapper: PlayerStateMapper

    @BeforeTest
    fun setup() {
        mapper = PlayerStateMapper()
    }

    @Test
    fun `given Playing FrameworkPlayerState, when from, then result should be the expected Playing DataPlayerState`() {
        // given
        val data = FrameworkPlayerState.Playing
        val expected = DataPlayerState.Playing

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Loading FrameworkPlayerState, when from, then result should be the expected Loading DataPlayerState`() {
        // given
        val data = FrameworkPlayerState.Loading
        val expected = DataPlayerState.Loading

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Stopped FrameworkPlayerState, when from, then result should be the expected Stopped DataPlayerState`() {
        // given
        val data = FrameworkPlayerState.Stopped
        val expected = DataPlayerState.Stopped

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Malformed Error FrameworkPlayerState, when from, then result should be the expected Malformed Error DataPlayerState`() {
        // given
        val data = FrameworkPlayerState.Error.Malformed
        val expected = DataPlayerState.Error.Malformed

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Unsupported Error FrameworkPlayerState, when from, then result should be the expected Unsupported Error DataPlayerState`() {
        // given
        val data = FrameworkPlayerState.Error.Unsupported
        val expected = DataPlayerState.Error.Unsupported

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Timeout Error FrameworkPlayerState, when from, then result should be the expected Timeout Error DataPlayerState`() {
        // given
        val data = FrameworkPlayerState.Error.Timeout
        val expected = DataPlayerState.Error.Timeout

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Network Error FrameworkPlayerState, when from, then result should be the expected Network Error DataPlayerState`() {
        // given
        val data = FrameworkPlayerState.Error.Network
        val expected = DataPlayerState.Error.Network

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given MediaDisconnected Error FrameworkPlayerState, when from, then result should be the expected MediaDisconnected Error DataPlayerState`() {
        // given
        val data = FrameworkPlayerState.Error.MediaDisconnected
        val expected = DataPlayerState.Error.MediaDisconnected

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Unknown Error FrameworkPlayerState, when from, then result should be the expected Unknown Error DataPlayerState`() {
        // given
        val data = FrameworkPlayerState.Error.Unknown
        val expected = DataPlayerState.Error.Unknown

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given GenericError Error FrameworkPlayerState, when from, then result should be the expected GenericError Error DataPlayerState`() {
        // given
        val data = FrameworkPlayerState.Error.GenericError
        val expected = DataPlayerState.Error.GenericError

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }


    @Test
    fun `given Playing DataPlayerState, when from, then result should be the expected Playing PlayerState`() {
        // given
        val data = DataPlayerState.Playing
        val expected = PlayerState.Playing

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Loading DataPlayerState, when from, then result should be the expected Loading PlayerState`() {
        // given
        val data = DataPlayerState.Loading
        val expected = PlayerState.Loading

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Stopped DataPlayerState, when from, then result should be the expected Stopped PlayerState`() {
        // given
        val data = DataPlayerState.Stopped
        val expected = PlayerState.Stopped

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Malformed Error DataPlayerState, when from, then result should be the expected Malformed Error PlayerState`() {
        // given
        val data = DataPlayerState.Error.Malformed
        val expected = PlayerState.Error.Malformed

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Unsupported Error DataPlayerState, when from, then result should be the expected Unsupported Error PlayerState`() {
        // given
        val data = DataPlayerState.Error.Unsupported
        val expected = PlayerState.Error.Unsupported

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Timeout Error DataPlayerState, when from, then result should be the expected Timeout Error PlayerState`() {
        // given
        val data = DataPlayerState.Error.Timeout
        val expected = PlayerState.Error.Timeout

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Network Error DataPlayerState, when from, then result should be the expected Network Error PlayerState`() {
        // given
        val data = DataPlayerState.Error.Network
        val expected = PlayerState.Error.Network

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given MediaDisconnected Error DataPlayerState, when from, then result should be the expected MediaDisconnected Error PlayerState`() {
        // given
        val data = DataPlayerState.Error.MediaDisconnected
        val expected = PlayerState.Error.MediaDisconnected

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given Unknown Error DataPlayerState, when from, then result should be the expected Unknown Error PlayerState`() {
        // given
        val data = DataPlayerState.Error.Unknown
        val expected = PlayerState.Error.Unknown

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given GenericError Error DataPlayerState, when from, then result should be the expected GenericError Error PlayerState`() {
        // given
        val data = DataPlayerState.Error.GenericError
        val expected = PlayerState.Error.GenericError

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}