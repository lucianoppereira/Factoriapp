package com.aldeanapps.factoriapp.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aldeanapps.factoriapp.databinding.StampHistoryItemBinding
import com.aldeanapps.factoriapp.domain.model.TimeStampModel
import com.aldeanapps.factoriapp.ui.listener.HistoryItemClickListener
import com.aldeanapps.factoriapp.ui.view.viewholder.HistoryItemViewHolder

class TimeStampListAdapter(
    private val timeStamps: List<TimeStampModel>,
    private val itemClickListener: HistoryItemClickListener,
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        HistoryItemViewHolder(
            StampHistoryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as HistoryItemViewHolder).bind(timeStamps[position], itemClickListener)
    }

    override fun getItemCount() = timeStamps.size
}
