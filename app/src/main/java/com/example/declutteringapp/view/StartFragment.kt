package com.example.declutteringapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.FragmentStartBinding


class StartFragment : Fragment() {


    private lateinit var binding: FragmentStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tools.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment2_to_keepOrTossFragment)
        }

        binding.toolsTwo.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment2_to_buyOrSave)
        }
        binding.btnSpacesStart.setOnClickListener {

            findNavController().navigate(R.id.action_startFragment2_to_mySpaceFragment2)
        }
        binding.challDay.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment2_to_thirtyDaysFragment)

        }
        binding.challFive.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment2_to_fiveMinFragment)

        }



       /* val animationSlideSpaces = AnimationUtils.loadAnimation(context, R.anim.move_right)
        binding.btnSpacesStart.startAnimation(animationSlideSpaces)

        val animationTips = AnimationUtils.loadAnimation(context, R.anim.move_left)
        binding.btnTipsStart.startAnimation(animationTips)

        val animationTools = AnimationUtils.loadAnimation(context, R.anim.move_right)
        binding.btnToolsStart.startAnimation(animationTools)
        val animationChallenges = AnimationUtils.loadAnimation(context, R.anim.move_left)
        binding.btnChallengesStart.startAnimation(animationChallenges)*/



val qoutes= resources.getStringArray(R.array.decluttring_qoutes)
        var randomQoutes=qoutes.random()
  binding.qoutes.setText(randomQoutes)
    /*    val animation = AnimationUtils.loadAnimation(context, R.anim.slide_up)
        binding.qoutes.startAnimation(animation)*/

    }}
