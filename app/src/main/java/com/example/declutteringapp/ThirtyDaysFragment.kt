package com.example.declutteringapp

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.declutteringapp.databinding.FragmentThirtyDaysBinding
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class ThirtyDaysFragment : Fragment() ,ThirtyDaysRVAdapter.DayClickInterface{

    val  thirtysDayss  by navArgs<ThirtyDaysFragmentArgs>()



/*   companion object {
        @JvmStatic
        fun newInstance() =
            ThirtyDaysFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }*/

    private lateinit var binding: FragmentThirtyDaysBinding
    lateinit var viewModel: DaysViewModel
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


/*        sharedViewModel = ViewModelProvider(this).get(DaysSharedViewModel::class.java)

        sharedViewModel.daysData.observe(viewLifecycleOwner, Observer {
            tvName.text = it
        })*/

        thirtyDaysRVv = binding.thirtyDaysRV

        thirtyDaysRVv.layoutManager = GridLayoutManager(context, 3)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(DaysViewModel::class.java)
var dayImage =R.id.imagePlacement
var daysData=ArrayList<ThirtyDays>()

        for(i in 0..29){
            daysData.add(ThirtyDays(i+1, 0, ""))
        }
         thirtyDaysRVAdapter = ThirtyDaysRVAdapter(requireContext(), this)

        thirtyDaysRVv.adapter = thirtyDaysRVAdapter



        viewModel.allDays.observe(viewLifecycleOwner, Observer { list ->
            list?.let {

                thirtyDaysRVAdapter.updateList(daysData)
            }
        })
        }

   override fun onDayClick(thirtyDays: ThirtyDays) {
        findNavController().navigate(R.id.action_thirtyDaysFragment_to_thirtyDaysEditDialogFragment)
    }

}


/*
  override fun onDayClick(thirtyDays: ThirtyDays) {

            // opening a new intent and passing a data to it.
            val fragment: Fragment
            val bundle = Bundle()
            bundle.putInt("dayId", thirtyDays.id)
            fragment = ThirtyDaysEditDialogFragment.newInstance()
            fragment.arguments = bundle

            findNavController(fragment).navigate(R.id.action_thirtyDaysFragment_to_thirtyDaysEditDialogFragment)}
}*/
/**/
 /*   override fun passData(position: Int, itemCount: Int, image: String) {
        val bundle = Bundle()
        bundle.putInt("input_pos", position)
        bundle.putString("input_count", itemCount.toString())
        bundle.putString("input_image", image)

        val transaction = this.parentFragmentManager.beginTransaction()
        val frag2 = ThirtyDaysEditDialogFragment()
        frag2.arguments = bundle

        transaction.replace(R.id.fragment_container_view_tag, frag2)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}*/


