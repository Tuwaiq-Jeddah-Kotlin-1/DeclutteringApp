package com.example.declutteringapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.declutteringapp.databinding.ActivityMainBinding






class MainActivity : AppCompatActivity() {


    lateinit var _binding:ActivityMainBinding
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

       binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    binding.bottomNav.visibility = View.GONE
                }
                R.id.viewPagerFragment -> {
                    binding.bottomNav.visibility = View.GONE
                }
                R.id.homeFragment2 -> {
                    binding.bottomNav.visibility = View.GONE
                }
                R.id.logInFragment -> {
                    binding.bottomNav.visibility = View.GONE
                }
                    R.id.keepOrTossFragment -> {
                        binding.bottomNav.visibility=View.VISIBLE
                    }



                else -> {

                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }


    }
}
