package com.example.cinemashift.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cinemashift.databinding.FragmentTicketsBinding

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ticketsViewModel =
            ViewModelProvider(this)[TicketsViewModel::class.java]

        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTickets
        ticketsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}