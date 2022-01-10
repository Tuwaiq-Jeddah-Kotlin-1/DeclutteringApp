package com.example.declutteringapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.FragmentStartBinding
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow


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

        binding.btnToolsStart.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment2_to_toolsFragment2)
        }

        binding.btnChallengesStart.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment2_to_challengesFragment2)
        }
        binding.btnSpacesStart.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment2_to_mySpaceFragment2)
        }
        binding.btnTipsStart.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment2_to_tipsFragment)
        }
        fun View.Texts() = callbackFlow<Unit> {
            setOnClickListener {
                trySend(Unit)
            }
            awaitClose { setOnClickListener(null) }

        }
val qoutes= resources.getStringArray(R.array.decluttring_qoutes)
        var randomQoutes=qoutes.random()
  binding.qoutes.setText(randomQoutes)
    /*    val animation = AnimationUtils.loadAnimation(context, R.anim.slide_up)
        binding.qoutes.startAnimation(animation)*/

    }}
