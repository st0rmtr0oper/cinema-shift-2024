package com.example.cinemashift.data

import androidx.fragment.app.Fragment
import com.example.cinemashift.MainActivity

val Fragment.mainActivity: MainActivity
    get() = requireActivity() as MainActivity
