package com.example.cinemashift.ui.films.poster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemashift.data.Film
import com.example.cinemashift.databinding.FilmPosterItemBinding

class PosterAdapter(private val filmClickListener: (Film) -> Unit) :
    RecyclerView.Adapter<PosterViewHolder>() {

    var films: List<Film> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FilmPosterItemBinding.inflate(inflater, parent, false)
        return PosterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        holder.bind(films[position], filmClickListener)
    }

    override fun getItemCount(): Int = films.size
}

class PosterViewHolder(private val binding: FilmPosterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(film: Film, filmClickListener: (Film) -> Unit) {
        with(binding) {
            filmTitle.text = film.name
            filmSubtitle.text = film.genres.toString()
            filmRatingValue.text = film.userRatings.imdb + film.userRatings.kinopoisk
//            filmImg = //TODO GLIDE???
        //TODO stringFormat
        }

        itemView.setOnClickListener {
            filmClickListener(film)
        }
    }
}