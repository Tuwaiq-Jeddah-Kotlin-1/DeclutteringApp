package com.example.declutteringapp

import android.app.Application
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.declutteringapp.databinding.FragmentMySpaceBinding
import com.example.declutteringapp.databinding.FragmentProfileBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class MySpaceFragment : Fragment() , SpaceClickDeleteInterface,SpaceClickInterface{

    lateinit var viewModel: SpaceViewModel
    lateinit var spacesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    private lateinit var _binding: FragmentMySpaceBinding
    private val binding get() = _binding!!

    private lateinit var nameTitle:TextView


            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMySpaceBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        spacesRV = binding.spaceRV
        addFAB = binding.idFAB


        spacesRV.layoutManager = GridLayoutManager(this.activity, 2)

        val spaceRvAdapter = SpaceRvAdapter(requireContext(), this, this)


        spacesRV.adapter = spaceRvAdapter


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(SpaceViewModel::class.java)

/*

        var roomsImages=  Glide.with(this)
            .load(Uri.fromFile())
            .into(roomImage)
*/


        viewModel.allSpaces.observe(viewLifecycleOwner, Observer { list ->
            list?.let {

                spaceRvAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {

            findNavController().navigate(
                R.id.action_mySpaceFragment2_to_editSpaceFragment2
            )

        }
    }




    override fun onSpaceClick(space:Space) {
      val  action=MySpaceFragmentDirections.actionMySpaceFragment2ToShowRoomFragment(space=space)
Navigation.findNavController(requireView()).navigate(action)

    }

    override fun onDeleteIconClick(space: Space) {

        viewModel.deleteSpace(space)
        Toast.makeText(this.activity, "${space.roomName} Deleted", Toast.LENGTH_LONG).show()
    }
}


