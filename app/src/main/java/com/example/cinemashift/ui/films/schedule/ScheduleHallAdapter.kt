package com.example.cinemashift.ui.films.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemashift.data.Schedule
import com.example.cinemashift.data.ScheduleSeance
import com.example.cinemashift.databinding.CeanseTimeBinding

class ScheduleHallAdapter(private val ceanseClickListener: (ScheduleSeance) -> Unit, private val hall: String) :
    RecyclerView.Adapter<ScheduleHallHolder>() {
    var scheduleSeanceList: List<ScheduleSeance> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHallHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CeanseTimeBinding.inflate(inflater, parent, false)
        return ScheduleHallHolder(binding, hall)
    }

    override fun onBindViewHolder(holder: ScheduleHallHolder, position: Int) {
        holder.bind(scheduleSeanceList[position], ceanseClickListener)
    }

    override fun getItemCount(): Int = scheduleSeanceList.size
}

class ScheduleHallHolder(private val binding: CeanseTimeBinding, private val hall: String) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(scheduleSeance: ScheduleSeance, ceanseClickListener: (ScheduleSeance) -> Unit) {
        binding.ceansePickButton.text = scheduleSeance.time
        itemView.setOnClickListener {
            ceanseClickListener(scheduleSeance)
        }
    }
}