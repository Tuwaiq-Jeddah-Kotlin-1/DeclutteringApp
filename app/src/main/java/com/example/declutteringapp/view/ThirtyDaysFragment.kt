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
import com.example.declutteringapp.model.Score
import com.example.declutteringapp.model.ThirtyDays
import com.example.declutteringapp.viewmodel.DaysViewModel
import com.example.declutteringapp.viewmodel.ScoreViewModel


class ThirtyDaysFragment : Fragment() ,ThirtyDaysRVAdapter.DayClickInterface {

    private lateinit var binding: FragmentThirtyDaysBinding
    lateinit var viewModel: DaysViewModel
    lateinit var scoreViewModel: ScoreViewModel
    lateinit var thirtyDaysRVv: RecyclerView
    lateinit var thirtyDaysRVAdapter:ThirtyDaysRVAdapter

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

        thirtyDaysRVv.layoutManager = GridLayoutManager(requireContext(), 3)
        thirtyDaysRVv.setHasFixedSize(true)

        val data = ArrayList<ThirtyDays>()


        thirtyDaysRVAdapter = ThirtyDaysRVAdapter(requireContext(), this)


        thirtyDaysRVv.adapter = thirtyDaysRVAdapter


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(DaysViewModel::class.java)

        viewModel.allDays

        var daysData=ArrayList<ThirtyDays>()

        for(i in 0..29){
            viewModel.addDay(ThirtyDays(i+1, 0,""))
        }

        viewModel.allDays.observe(viewLifecycleOwner, Observer { list ->
            list?.let {

                thirtyDaysRVAdapter.updateList(list)


            }
        })
    }
    override fun onDayClick(thirtyDays: ThirtyDays) {
        findNavController().navigate(R.id.action_thirtyDaysFragment_to_thirtyDaysEditDialogFragment)
    }

/*
        thirtyDaysRVv = binding.thirtyDaysRV

        thirtyDaysRVv.layoutManager = GridLayoutManager(context, 3)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(DaysViewModel::class.java)
       thirtyDaysRVv.setHasFixedSize(true)

        thirtyDaysRVAdapter = ThirtyDaysRVAdapter(requireContext(), this)

        thirtyDaysRVv.adapter = thirtyDaysRVAdapter



        val data = ArrayList<ThirtyDays>()
        for(i in 1..29){
            data.add(ThirtyDays(1+i,0,""))
        }*/

     /*   scoreViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(ScoreViewModel::class.java)
*/
        /*       val scoreObserver = Observer<Score> { newScore ->
                                  binding.tvScore.text = newScore.toString()
                              }

                              scoreViewModel.scores.observe(viewLifecycleOwner, scoreObserver)*/
var dayImage = R.id.imagePlacement



/*
    scoreViewModel.allScores.observe(viewLifecycleOwner, Observer { scores ->
        scores?.let {
            scoreViewModel.updateScore(scores)
        }
    })
*/

/*

        val scoreObserver = Observer<Score> { newScore ->
            binding.tvScore.text = newScore.toString()
        }

        scoreViewModel.scores.observe(viewLifecycleOwner, scoreObserver)
*/




}


 /*



}
*/



