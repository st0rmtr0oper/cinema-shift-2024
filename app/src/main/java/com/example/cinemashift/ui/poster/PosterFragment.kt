package com.example.cinemashift.ui.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cinemashift.data.Film
import com.example.cinemashift.databinding.FragmentPosterBinding
import kotlinx.coroutines.launch
import com.example.cinemashift.mainActivity

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

        binding.posterRecyclerView.adapter =
            PosterAdapter(::handleFilmClick)    //TODO снова вопрос с кнопкой

        launchPosterLoading()
    }

    private fun handleFilmClick(film: Film) {
        Toast.makeText(context, "kk", Toast.LENGTH_SHORT).show()    //TODO  55:09
    }

    private fun launchPosterLoading() {
        showProgress()

        lifecycleScope.launch {
            try {
                val repository = mainActivity.repository    //TODO: как я понял, этот репозиторий должен валяться в ViewModel
                val films = repository.getTodayFilms()
                showContent(films)
            } catch (ex: Exception) {
                showError(ex.message.orEmpty())
            }
        }
    }

    private fun showProgress() {
        with(binding) {
            posterErrorText.isVisible = false
            posterRecyclerView.isVisible = false
            posterProgBar.isVisible = true
        }
    }

    private fun showContent(films: List<Film>) {
        with (binding) {
            posterProgBar.isVisible = false
            posterErrorText.isVisible = false
            posterRecyclerView.isVisible = true
            (posterRecyclerView.adapter as? PosterAdapter)?.films= films
        }
    }

    private fun showError(message: String) {
        with(binding) {
            posterProgBar.isVisible = false
            posterRecyclerView.isVisible = false
            posterErrorText.isVisible = true
            posterErrorText.text= message + "\n Нажмите на это сообщение, чтобы обновить страницу"
            posterErrorText.setOnClickListener {launchPosterLoading()}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}