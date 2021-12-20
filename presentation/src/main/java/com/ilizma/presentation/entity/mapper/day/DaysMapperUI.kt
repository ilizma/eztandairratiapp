package com.ilizma.presentation.entity.mapper.day

import com.ilizma.presentation.entity.mapper.MapperUI

class DaysMapperUI : MapperUI<Pair<Int, String>, DaysUI> {

    override fun mapToUI(
        obj: Pair<Int, String>
    ): DaysUI = DaysUI(obj)

}