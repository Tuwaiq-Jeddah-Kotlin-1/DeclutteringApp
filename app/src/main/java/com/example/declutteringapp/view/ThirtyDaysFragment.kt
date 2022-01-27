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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.declutteringapp.databinding.FragmentThirtyDaysBinding
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.R
import com.example.declutteringapp.model.ThirtyDays
import com.example.declutteringapp.view.adapters.ThirtyDaysRVAdapter
import com.example.declutteringapp.viewmodel.DaysViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ThirtyDaysFragment : Fragment()  {


    private lateinit var binding: FragmentThirtyDaysBinding
    lateinit var viewModel: DaysViewModel
    lateinit var thirtyDaysRVv: RecyclerView
    lateinit var thirtyDaysRVAdapter: ThirtyDaysRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirtyDaysBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        thirtyDaysRVv = binding.thirtyDaysRV


        thirtyDaysRVv.layoutManager = GridLayoutManager(context, 3)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(DaysViewModel::class.java)

        thirtyDaysRVAdapter = ThirtyDaysRVAdapter(requireContext())

        thirtyDaysRVv.adapter = thirtyDaysRVAdapter

        binding.fabDays.setOnClickListener{
            findNavController().navigate(R.id.action_thirtyDaysFragment_to_thirtyDaysEditDialogFragment)
        }


        viewModel.allDays.observe(viewLifecycleOwner, Observer { list ->
            list?.let {

                thirtyDaysRVAdapter.updateList(it)
              /*  when (list.size-1) {
                    val builder = AlertDialog.Builder(context)
                    builder.setMessage("Your done clear, startagain, or do another challenge")
                        .setCancelable(false)
                        .setPositiveButton("Yes") { dialog, id ->
                            thirtyDaysRVv.removeAllViewsInLayout()
                            Toast.makeText(this.activity, "list cleard", Toast.LENGTH_LONG).show()
                        }
                        .setNegativeButton("No") { dialog, id ->
                            dialog.dismiss()
                        }
                    val alert = builder.create()
                    alert.show()
*/


            }

        })
    }



}