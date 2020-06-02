package com.ilizma.presentation.entity.mapper.day

import com.ilizma.presentation.entity.mapper.MapperUI

class DaysMapperUI : MapperUI<String, DaysUI> {

    override fun mapToUI(obj: String): DaysUI = DaysUI(obj)

}