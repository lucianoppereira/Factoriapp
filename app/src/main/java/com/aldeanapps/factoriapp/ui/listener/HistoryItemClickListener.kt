package com.aldeanapps.factoriapp.ui.listener

import com.aldeanapps.factoriapp.domain.model.TimeStampModel

interface HistoryItemClickListener {
    fun onItemClickListener(item: TimeStampModel)
}
