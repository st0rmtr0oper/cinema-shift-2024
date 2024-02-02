package com.example.cinemashift.ui.films.schedule

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.cinemashift.R
import com.example.cinemashift.data.Schedule
import com.example.cinemashift.data.mainActivity
import com.example.cinemashift.databinding.FragmentFilmInfoBinding
import com.example.cinemashift.databinding.FragmentScheduleBinding
import com.example.cinemashift.ui.films.info.FilmInfoFragmentArgs
import com.example.cinemashift.ui.profile.ProfileViewModel
import kotlinx.coroutines.launch

class ScheduleFragment : Fragment() {


    private var _binding: FragmentScheduleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val scheduleFragmentArgs by navArgs<ScheduleFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val scheduleViewModel = ScheduleViewModel()

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)

        //TODO click handler

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchScheduleLoading(scheduleFragmentArgs.scheduleFilmId.toLong())
    }

    private fun launchScheduleLoading(id: Long) {
        showProgress()

        lifecycleScope.launch {
            try {
                val repository = mainActivity.repository
                val filmSchedule = repository.getFilmSchedule(id)
                updateSchedule(filmSchedule)
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

    private fun updateSchedule(scheduleList: List<Schedule>) {
        with(binding) {
            scheduleErrorText.isVisible = false
            scheduleProgBar.isVisible = false
            setContentVisible(true)
        }
    }

    private fun setContentVisible(bool: Boolean) {
        with(binding) {
            rvText.isVisible = bool
            svDate.isVisible = bool
            redText.isVisible = bool
            redButtons.isVisible = bool
            greenText.isVisible = bool
            greenButtons.isVisible = bool
            blueText.isVisible = bool
            blueButtons.isVisible = bool
        }
    }
}