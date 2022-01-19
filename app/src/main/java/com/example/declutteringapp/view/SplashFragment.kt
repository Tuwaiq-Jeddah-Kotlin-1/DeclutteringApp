package com.example.declutteringapp.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.FragmentEditSpaceBinding
import com.example.declutteringapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.*

/*import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.**/
import java.lang.Runnable
import kotlin.coroutines.CoroutineContext


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
/*
            if (getOnBoardStatus())
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment2)
            else*/
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)

        }, 3000)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      /*  var logo=view.findViewById<TextView>(R.id.textView3)
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_up)
        logo.startAnimation(animation)*/
    }

/*
    fun getOnBoardStatus(): Boolean {
        addPref()
        return this.requireActivity().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)!!.getBoolean("finished", false)
    }


    fun addPref()
    {
        val shpf=requireContext().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)
        shpf.edit().apply {  putBoolean("finished",true)}.apply()
    }*/
}



/*
class SplashFragment : Fragment() {
    private lateinit var _binding: FragmentSplashBinding
    private val binding get() = _binding!!




    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityScope.launch {
            delay(3000)
        }
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment2)

           val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                activity?.finish()
            }
        }
        this.activity?.getOnBackPressedDispatcher()?.addCallback(this, onBackPressedCallback)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    *//*    val animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
        binding.imageb.startAnimation(animation)*//*

         activityScope.launch {

            if(getOnBoardStatus()){
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment2)}
            else{

                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)}

         delay(3000)}


    }



    fun getOnBoardStatus(): Boolean {
       addPref()
        return this.requireActivity().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)!!.getBoolean("finished", false)
    }


    fun addPref()
    {
        val shpf=requireContext().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)
        shpf.edit().apply {  putBoolean("finished",true)}.apply()
    }*/
