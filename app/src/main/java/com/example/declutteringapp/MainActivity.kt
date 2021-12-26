package com.example.declutteringapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.declutteringapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File

lateinit var _binding:ActivityMainBinding
private val binding get() = _binding!!



class MainActivity : AppCompatActivity() {

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

                else -> {
                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }


    }
}
/*    override fun passData(etName:String,etItemNum:Int,dayImage: String) {


            val bundle = Bundle()
            bundle.putString("message", etName)
        bundle.putInt("0", etItemNum)
         bundle.putString("image", dayImage)
        }*/

