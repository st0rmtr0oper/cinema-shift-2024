package com.example.cinemashift

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cinemashift.data.CinemaRepository
import com.example.cinemashift.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val repository =
        CinemaRepository() //TODO вот это вот недоразумение лучше убрать в нужное место потом

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Calling SlashScreen
        Thread.sleep(800)
        installSplashScreen()

        //binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //bottom nav bar
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_poster, R.id.navigation_tickets, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //hiding bottom nav bar in non-home fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_film_info) {

                navView.visibility = View.GONE
            } else {

                navView.visibility = View.VISIBLE
            }
        }

        //enabling up button (1/2)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }
    //enabling up button (2/2)
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp()
    }
}