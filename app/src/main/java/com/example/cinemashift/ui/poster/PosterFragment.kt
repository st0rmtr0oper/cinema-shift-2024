package com.example.cinemashift.ui.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cinemashift.databinding.FragmentPosterBinding

class PosterFragment : Fragment() {

    private var _binding: FragmentPosterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val posterViewModel =
            ViewModelProvider(this)[PosterViewModel::class.java]

        _binding = FragmentPosterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPoster
        posterViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}