package com.example.cinemashift.ui.films.info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cinemashift.R
import com.example.cinemashift.data.CinemaRepository
import com.example.cinemashift.data.Film
import com.example.cinemashift.data.mainActivity
import com.example.cinemashift.databinding.FragmentFilmInfoBinding
import com.example.cinemashift.ui.profile.ProfileViewModel
import kotlinx.coroutines.launch

class FilmInfoFragment : Fragment() {


    private var _binding: FragmentFilmInfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val filmInfoFragmentArgs by navArgs<FilmInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val profileViewModel =
//            ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentFilmInfoBinding.inflate(inflater, container, false)


        binding.filmButton.setOnClickListener {
            findNavController().navigate(FilmInfoFragmentDirections.actionNavigationFilmInfoToScheduleFragment(filmInfoFragmentArgs.filmId))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchFilmInfoLoading(filmInfoFragmentArgs.filmId.toLong())
    }



    private fun launchFilmInfoLoading(id: Long) {
        showProgress()

        lifecycleScope.launch {
            try {
                val repository = mainActivity.repository
                val filmInfo = repository.getFilmByID(id)
                showContent(filmInfo)
            } catch (ex: Exception) {
                showError(ex.message.orEmpty())
            }
        }
    }

    private fun showProgress() {
        with(binding) {
            filmCardDescription.isVisible = false
            filmImg.isVisible = false
            filmDescription.isVisible = false
            filmErrorText.isVisible = false
            filmProgBar.isVisible = true
            filmButton.isVisible = false
        }
    }

    private fun showContent(film: Film) {
        with(binding){
            filmCardDescription.isVisible = true
            filmImg.isVisible = true
            filmDescription.isVisible = true
            filmErrorText.isVisible = false
            filmProgBar.isVisible = false
            filmButton.isVisible = true

            filmTitle.text = film.name
            filmSubtitle.text = film.genres.toString()
            filmRatingValue.text = film.userRatings.imdb + film.userRatings.kinopoisk
            filmDescriptionText.text = film.description
            Glide.with(binding.filmImg.context)
                .load(CinemaRepository.CROP_URL +film.img) // image url
                .placeholder(R.drawable.baseline_image_24) // any placeholder to load at start
                .error(R.drawable.baseline_hide_image_24)  // any image in case of error
                .into(binding.filmImg)
        }
    }

    private fun showError(message: String) {
        with(binding){
            filmCardDescription.isVisible = false
            filmImg.isVisible = false
            filmDescription.isVisible = false
            filmErrorText.isVisible = true
            filmProgBar.isVisible = false
            filmButton.isVisible = false

            filmErrorText.text = "$message\n Нажмите на это сообщение, чтобы обновить страницу"
            filmErrorText.setOnClickListener { launchFilmInfoLoading(filmInfoFragmentArgs.filmId.toLong()) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}