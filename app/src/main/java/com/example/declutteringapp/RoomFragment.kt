package com.example.declutteringapp

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.declutteringapp.databinding.FragmentRoomBinding

class RoomFragment : Fragment() {

// val    spaces by navArgs<RoomFragmentArgs>()

    private lateinit var _binding: FragmentRoomBinding
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
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(SpaceViewModel::class.java)
    }}

     /*   private fun getRoomInfo(it: View?) {
           binding.tvRoomName.setText(spaces.data.roomName)
           binding.tvRoomStatus.setText(spaces.data.status)
          // binding.tvRoomName.set(spaces.data.roomName)
        }}
*/

