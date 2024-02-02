package com.example.cinemashift.ui.films.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.cinemashift.data.Schedule
import com.example.cinemashift.data.ScheduleSeance
import com.example.cinemashift.data.mainActivity
import com.example.cinemashift.databinding.FragmentScheduleBinding
import kotlinx.coroutines.launch

class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private val scheduleFragmentArgs by navArgs<ScheduleFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val scheduleViewModel = ScheduleViewModel()

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            binding.datePickerRv.adapter =
                ScheduleDateAdapter(::handleDateClick)
            binding.redRv.adapter = ScheduleHallAdapter(::handleRedClick, "red")
            binding.greenRv.adapter = ScheduleHallAdapter(::handleGreenClick, "green")
            binding.blueRv.adapter= ScheduleHallAdapter(::handleBlueClick, "blue")
        }
        launchScheduleLoading(scheduleFragmentArgs.scheduleFilmId.toLong())
    }
    private fun handleDateClick(schedule: Schedule) {
        //TODO fun выбор дня
    }
    private fun handleRedClick(scheduleSeance: ScheduleSeance) {

    }
    private fun handleGreenClick(scheduleSeance: ScheduleSeance) {

    }
    private fun handleBlueClick(scheduleSeance: ScheduleSeance) {

    }
    private fun launchScheduleLoading(id: Long) {
        showProgress()

        lifecycleScope.launch {
            try {
                val repository = mainActivity.repository
                val filmSchedule = repository.getFilmSchedule(id)
                showContent(filmSchedule)
            } catch (ex: Exception) {
                showError(ex.message.orEmpty())
            }
        }
    }
    private fun showError(message: String) {
        with(binding) {
            scheduleErrorText.isVisible = true
            scheduleProgBar.isVisible = false
            setContentVisible(false)
            scheduleErrorText.text = "$message\n Нажмите на это сообщение, чтобы обновить страницу"
            scheduleErrorText.setOnClickListener { launchScheduleLoading(scheduleFragmentArgs.scheduleFilmId.toLong()) }
        }
    }
    private fun showProgress() {
        with(binding) {
            scheduleErrorText.isVisible = false
            scheduleProgBar.isVisible = true
            setContentVisible(false)
        }
    }
    private fun showContent(scheduleList: List<Schedule>) {
        with(binding) {
            scheduleErrorText.isVisible = false
            scheduleProgBar.isVisible = false
            setContentVisible(true)
            (datePickerRv.adapter as? ScheduleDateAdapter)?.scheduleList = scheduleList
            updateSheduleByDay(scheduleList[0])
        }
    }

    private fun updateSheduleByDay(schedule: Schedule) {
        val redSeancesList = getRedHallSeanceList(schedule.seances)
        val greenSeancesList = getGreenHallSeanceList(schedule.seances)
        val blueSeancesList = getBlueHallSeanceList(schedule.seances)
        with(binding){
            (redRv.adapter as? ScheduleHallAdapter)?.scheduleSeanceList = redSeancesList
            (greenRv.adapter as? ScheduleHallAdapter)?.scheduleSeanceList = greenSeancesList
            (blueRv.adapter as? ScheduleHallAdapter)?.scheduleSeanceList = blueSeancesList
        }
    }
    private fun getRedHallSeanceList(seances: List<ScheduleSeance>): List<ScheduleSeance> {
        var redSeances: MutableList<ScheduleSeance> = arrayListOf()
        for (s in seances) {
            if (s.hall.name == "Red") {
                redSeances.add(s)
            }
        }
        return redSeances
    }
    private fun getGreenHallSeanceList(seances: List<ScheduleSeance>): MutableList<ScheduleSeance> {
        var greenSeances: MutableList<ScheduleSeance> = arrayListOf()
        for (s in seances) {
            if (s.hall.name == "Green") {
                greenSeances.add(s)
            }
        }
        return greenSeances
    }
    private fun getBlueHallSeanceList(seances: List<ScheduleSeance>): MutableList<ScheduleSeance> {
        var blueSeances: MutableList<ScheduleSeance> = arrayListOf()
        for (s in seances) {
            if (s.hall.name == "Blue") {
                blueSeances.add(s)
            }
        }
        return blueSeances
    }

    private fun setContentVisible(bool: Boolean) {
        with(binding) {
            rvText.isVisible = bool
            datePickerRv.isVisible = bool
            redText.isVisible = bool
            redRv.isVisible = bool
            greenText.isVisible = bool
            greenRv.isVisible = bool
            blueText.isVisible = bool
            blueRv.isVisible = bool
        }
    }
}