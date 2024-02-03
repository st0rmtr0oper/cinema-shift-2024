package com.example.cinemashift.ui.films.place

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinemashift.R

class PlaceFragment : Fragment() {

    companion object {
        fun newInstance() = PlaceFragment()
    }

    private lateinit var viewModel: PlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlaceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}