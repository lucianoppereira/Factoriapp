package com.aldeanapps.factoriapp.ui.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.aldeanapps.factoriapp.databinding.StampHistoryItemBinding
import com.aldeanapps.factoriapp.domain.model.TimeStampModel
import com.aldeanapps.factoriapp.ui.listener.HistoryItemClickListener

class HistoryItemViewHolder(
    private val itemBinding: StampHistoryItemBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(timeStamp: TimeStampModel, onItemClick: HistoryItemClickListener) {
        with(itemBinding) {
            currentTimeTV.text = timeStamp.datetime
            dayOfWeekTV.text = timeStamp.dayOfWeek
            timeZoneTV.text = "${timeStamp.timeZone}, UTC${timeStamp.utcOffset}"
            deleteButton.setOnClickListener { onItemClick.onItemClickListener(timeStamp) }
        }
    }
}