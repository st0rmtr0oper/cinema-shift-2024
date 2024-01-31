package com.example.cinemashift

import androidx.fragment.app.Fragment

val Fragment.mainActivity: MainActivity
    get() = requireActivity() as MainActivity
