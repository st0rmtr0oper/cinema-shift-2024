package com.example.cinemashift.ui.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cinemashift.data.Film
import com.example.cinemashift.databinding.FragmentPosterBinding
import kotlinx.coroutines.launch

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

//        val textView: TextView = binding.textPoster
//        posterViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.posterRecyclerView.adapter = PosterAdapter(::handleFilmClick)    //TODO снова вопрос с кнопкой

        launchPosterLoading()
    }

    private fun handleFilmClick(film: Film) {
        //TODO  не готово
    }

    private fun launchPosterLoading() { //TODO надо допилить...
//        showProgress()
//
//        lifecycleScope.launch {
//            try {
//                val repository = mainActivity.repository
//                val films = repository//.get...
//            }
//        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}