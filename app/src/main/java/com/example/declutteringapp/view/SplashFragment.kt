package com.example.declutteringapp.view


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.R


class SplashFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                activity?.finish()
            }
        }
        this.activity?.getOnBackPressedDispatcher()?.addCallback(this, onBackPressedCallback)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {

            if (getOnBoardStatus())

                findNavController().navigate(R.id.action_splashFragment_to_homeFragment2)
            else
                findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)

        }, 3000)
    }


    fun getOnBoardStatus(): Boolean {

        return requireActivity().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)
            .getBoolean("finished", false)
    }
}