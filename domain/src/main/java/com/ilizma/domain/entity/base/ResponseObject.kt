package com.ilizma.domain.entity.base

interface ResponseObject<out DomainObject : Any?> {

    fun toDomain(): DomainObject

}