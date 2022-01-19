package com.example.declutteringapp.view

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.FragmentMySpaceBinding
import com.example.declutteringapp.model.Score
import com.example.declutteringapp.model.Space
import com.example.declutteringapp.view.adapters.SpaceRvAdapter
import com.example.declutteringapp.viewmodel.ScoreViewModel
import com.example.declutteringapp.viewmodel.SpaceViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

import java.util.*


class MySpaceFragment : Fragment() , SpaceRvAdapter.SpaceClickDeleteInterface,
    SpaceRvAdapter.SpaceClickInterface {

    lateinit var viewModel: SpaceViewModel
    lateinit var spacesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var scoreViewModel: ScoreViewModel
lateinit var score:Score
    private lateinit var _binding: FragmentMySpaceBinding
    private val binding get() = _binding

    private var list: ArrayList<Space>? = null


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

        // emptyBinding= RvEmptyBinding.inflate()
        spacesRV.layoutManager = GridLayoutManager(this.activity, 2)

        val spaceRvAdapter = SpaceRvAdapter(requireContext(),this,this)


        spacesRV.adapter = spaceRvAdapter


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(SpaceViewModel::class.java)


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
       if(list?.size == 0){
            spacesRV.visibility=View.GONE
            binding.emtyTextRV.visibility=View.VISIBLE
        }else{
            spacesRV.visibility=View.VISIBLE
            binding.emtyTextRV.visibility=View.GONE
        }


    }


    override fun onDeleteIconClick(space: Space) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to Delete Item?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                viewModel.deleteSpace(space)
                Toast.makeText(this.activity, "room Deleted", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()

    }

    override fun onSpaceClick(space: Space) {
        val  action= MySpaceFragmentDirections.actionMySpaceFragment2ToShowRoomFragment(space=space )
          Navigation.findNavController(requireView()).navigate(action)

    }


}


