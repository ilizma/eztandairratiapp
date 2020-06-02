package com.ilizma.presentation.entity.mapper

interface MapperUI<DomainObject, UIObject> {

    fun mapToUI(obj: DomainObject): UIObject

}