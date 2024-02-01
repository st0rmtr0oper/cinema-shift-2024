package com.example.cinemashift.ui.films.schedule

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinemashift.R
import com.example.cinemashift.databinding.FragmentFilmInfoBinding
import com.example.cinemashift.databinding.FragmentScheduleBinding
import com.example.cinemashift.ui.profile.ProfileViewModel

class ScheduleFragment : Fragment() {


    private var _binding: FragmentScheduleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

        launchScheduleLoading()
    }

    private fun launchScheduleLoading() {
        //TODO подгрузка данных
    }

}