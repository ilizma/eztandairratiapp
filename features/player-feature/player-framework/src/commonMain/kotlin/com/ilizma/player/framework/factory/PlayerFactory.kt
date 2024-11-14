package com.ilizma.player.framework.factory

interface PlayerFactory<T> {

    fun create(): T

}