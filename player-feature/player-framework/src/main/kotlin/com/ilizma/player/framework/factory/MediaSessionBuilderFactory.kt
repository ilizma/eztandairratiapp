package com.ilizma.player.framework.factory

interface MediaSessionBuilderFactory<T, K> {

    fun create(player: K): T

}