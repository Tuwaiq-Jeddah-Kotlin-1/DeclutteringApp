package com.example.declutteringapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.declutteringapp.databinding.FragmentRoomBinding
import com.example.declutteringapp.databinding.FragmentShowRoomBinding


class ShowRoomFragment : Fragment() {

    val spaces by navArgs<ShowRoomFragmentArgs>()

    private lateinit var _binding: FragmentShowRoomBinding
    private val binding get() = _binding!!
    lateinit var viewModel: SpaceViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowRoomBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.ShowRoomName.setText(spaces.space.roomName)
        binding.ShowRoomStatus.setText(spaces.space.status)
       // binding.ShowRoomImage.setImage((spaces.space.imgPath))
    }
    }
