package com.example.declutteringapp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*
import java.lang.Runnable
import kotlin.coroutines.CoroutineContext


class SplashFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                requireActivity().finish()
            }
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {

            if (getOnBoardStatus())
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment2)
            else
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)

        }, 3000)
    }


    fun getOnBoardStatus(): Boolean {

        return requireActivity().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)
            .getBoolean("finished", false)
    }
}