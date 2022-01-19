package com.example.declutteringapp.view

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.declutteringapp.databinding.FragmentThirtyDaysBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.declutteringapp.R
import com.example.declutteringapp.model.ThirtyDays
import com.example.declutteringapp.view.adapters.ThirtyDaysRVAdapter
import com.example.declutteringapp.viewmodel.DaysViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ThirtyDaysFragment : Fragment() , ThirtyDaysRVAdapter.DayClickInterface {

    val  dayss  by navArgs<ThirtyDaysFragmentArgs>()


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

        val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
        val currentDateAndTime: String = sdf.format(Date())

        val daysData = ArrayList<ThirtyDays>()



        viewModel.allDays.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                thirtyDaysRVAdapter = ThirtyDaysRVAdapter(requireContext(), this,daysData)

                thirtyDaysRVv.adapter = thirtyDaysRVAdapter

                thirtyDaysRVAdapter.updateList(list)
            }
        })
    }
    override fun onDayClick(thirtyDays: ThirtyDays) {
        findNavController().navigate(R.id.action_thirtyDaysFragment_to_thirtyDaysEditDialogFragment)
    }




 /*   private fun fetchData():ArrayList<ThirtyDays>{
        var daysData = ArrayList<ThirtyDays>()

        for (i in 0..29) {
            daysData.add(ThirtyDays(i + 1, 0, "",1+29))
        }
        return daysData
    }*/


}