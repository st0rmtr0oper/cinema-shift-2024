package com.example.cinemashift.ui.films.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemashift.data.Schedule
import com.example.cinemashift.databinding.DatePickBinding

class ScheduleDateAdapter(private val dateClickListener: (Schedule) -> Unit) :
    RecyclerView.Adapter<ScheduleDateHolder>() {

    var scheduleList: List<Schedule> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDateHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DatePickBinding.inflate(inflater, parent, false)
        return ScheduleDateHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleDateHolder, position: Int) {
        holder.bind(scheduleList[position], dateClickListener)
    }

    override fun getItemCount(): Int = scheduleList.size
}

class ScheduleDateHolder(private val binding: DatePickBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(schedule: Schedule, dateClickListener: (Schedule) -> Unit) {
        with(binding) {
            datePickButton.text = schedule.date
            datePickButton.setOnClickListener {
                dateClickListener(schedule)
            }
        }
    }
}