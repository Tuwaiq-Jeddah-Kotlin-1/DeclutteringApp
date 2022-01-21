package com.example.declutteringapp.view


import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.ActivityMainBinding
import com.example.declutteringapp.model.repo.SpaceRepo
import java.util.*


class MainActivity : AppCompatActivity() {


    lateinit var _binding:ActivityMainBinding
    private val binding get() = _binding!!
   // val sharedPref =getPreferences(Context.MODE_PRIVATE)

    var score= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        SpaceRepo.NotificationRepo().myNotification(this)

        val sharedPreferences = getSharedPreferences("SHARED_PREF", Activity.MODE_PRIVATE)

        if (isNetworkConnected(this) ==false){
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()

        }
        else{}



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

        if (sharedPreferences.getString("LOCALE","") == "ar") {
            setLocate(this,"ar")
        } else {
            setLocate(this,"en")
        }

        if (sharedPreferences.getBoolean("DARK_MOOD", false)) {
            resources.configuration.uiMode = AppCompatDelegate.MODE_NIGHT_YES

        } else {
            resources.configuration.uiMode = AppCompatDelegate.MODE_NIGHT_NO
        }
    }



    private fun setLocate(activity: Activity, Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val resources = activity.resources
        val  config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

    }

/*

        val editor: SharedPreferences.Editor =
            sharedPref.edit()
        editor.putInt("score", score)
        editor.apply()



        val score = sharedPref.getInt("Score", 0)

*/



     fun isNetworkConnected(context: Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }


}
