package com.ilizma.player.framework.factory

interface MediaPlayerFactory<T> {

    fun create(): T

}