package com.example.declutteringapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.databinding.FragmentRoomBinding
import com.example.declutteringapp.databinding.FragmentToolsBinding


class ToolsFragment : Fragment() {

    private lateinit var _binding: FragmentToolsBinding
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentToolsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


      //  findNavController().navigate(R.id.action_editSpaceFragment_to_mySpaceFragment22)


    }
}