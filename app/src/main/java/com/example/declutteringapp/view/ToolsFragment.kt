package com.example.declutteringapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.FragmentToolsBinding
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onEach


class ToolsFragment : Fragment() {

    private lateinit var _binding: FragmentToolsBinding
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences

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
      /*  var scorePref: Int = scoreNum
        var scoreNum = binding.scoreN.text*/
        var scoreNu=0
        sharedPreferences =
            this.requireActivity().getSharedPreferences("preference", Context.MODE_PRIVATE)

        //var score = sharedPref?.getInt("Score", scoreNu)

    //    val score = sharedPreferences.getInt("Score", 0)


        var tvScore =  binding.scoreN.text.toString().toInt()
        scoreNu= tvScore

val scorePref:Int =tvScore

        val editor: SharedPreferences.Editor =
            sharedPreferences.edit()
        editor.putInt("score", scorePref
        )
        editor.apply()




        binding.testScore.clicks()
            .onEach{
                scoreNu.plus(1)}


        binding.cvKeepOrToss.setOnClickListener{
            findNavController().navigate(R.id.action_toolsFragment2_to_keepOrTossFragment)

        }

        binding.saveBuy.setOnClickListener{
            findNavController().navigate(R.id.action_toolsFragment2_to_buyOrSave)

        }



    }

    fun View.clicks() = callbackFlow<Unit> {
        setOnClickListener {
            trySend(Unit)
        }
        awaitClose { setOnClickListener(null) }

    }
}