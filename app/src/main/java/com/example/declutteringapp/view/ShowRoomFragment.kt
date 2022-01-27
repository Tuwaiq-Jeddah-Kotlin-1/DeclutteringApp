package com.example.declutteringapp.view

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.FragmentShowRoomBinding
import com.example.declutteringapp.model.ToDeclutter
import com.example.declutteringapp.view.adapters.ToDeclutterAdapter
import com.example.declutteringapp.viewmodel.SpaceViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog


class ShowRoomFragment : Fragment(), ToDeclutterAdapter.ClickDeleteInterface {

    val spaces by navArgs<ShowRoomFragmentArgs>()
    val items by navArgs<ShowRoomFragmentArgs>()

    private lateinit var _binding: FragmentShowRoomBinding
    private val binding get() = _binding!!
    lateinit var viewModelD: SpaceViewModel
    lateinit var toDeclutterRV: RecyclerView
    lateinit var addFAB: ImageButton



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
        Glide.with(this)
            .load(spaces.space.imgPath)
            .into(binding.showRoomImage)



        toDeclutterRV = binding.toDeclutterRV
        toDeclutterRV.layoutManager = GridLayoutManager(context, 1)

        val spaceAdapter = ToDeclutterAdapter(requireContext(),this)

        toDeclutterRV.adapter = spaceAdapter
        addFAB=binding.idFAB


        viewModelD = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(SpaceViewModel::class.java)



        viewModelD.allItems(spaces.space.roomId).observe(viewLifecycleOwner,  { list ->
            list?.let {

                spaceAdapter.updateList(it)
            }
        })


        addFAB.setOnClickListener {
            itemDialog()
        }



    }
    fun itemDialog() {
        val view: View = layoutInflater.inflate(R.layout.edittext_with_button, null)

        val builder = BottomSheetDialog(requireView()?.context)
        builder.setTitle("Add Item")

        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)


        btnClose.setOnClickListener {

            var itemsDeclutterr = view.findViewById<EditText>(R.id.etAddItem)

            var  itemsD=   itemsDeclutterr.text.toString()

            var itemSpace= ToDeclutter(itemsD,spaces.space.roomId)

                viewModelD.addItem(itemSpace)

            Toast.makeText(requireContext(), "You Added an Item!", Toast.LENGTH_SHORT).show()

            builder.dismiss()

        }


        builder.setCancelable(true)

        builder.setContentView(view)

            builder.show()
        }



    override fun onDeleteIconClick(item: ToDeclutter) {

        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to Delete Item?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                viewModelD.deleteItem(item)
                Toast.makeText(this.activity, "item Deleted", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()

    }
}



