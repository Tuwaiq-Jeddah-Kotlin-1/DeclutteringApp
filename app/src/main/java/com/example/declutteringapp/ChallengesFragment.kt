package com.example.declutteringapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.databinding.FragmentChallengesBinding
import com.example.declutteringapp.databinding.FragmentViewPagerBinding


class ChallengesFragment : Fragment() {

    private lateinit var binding: FragmentChallengesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*tvName = binding.tvYourProfile
        tvName.setText("${tvName.text}")
        */


        binding.thirtDays.setOnClickListener{
        findNavController().navigate(R.id.action_challengesFragment2_to_thirtyDaysFragment)
        }
        binding.bagChallenge.setOnClickListener{
            findNavController().navigate(R.id.action_challengesFragment2_to_bagFragment)
        }
        binding.fiveMin.setOnClickListener{
            findNavController().navigate(R.id.action_challengesFragment2_to_fiveMinFragment)
        }


   }}



