package com.example.declutteringapp.view

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.FragmentBuyOrSaveBinding
import com.example.declutteringapp.model.KeepOrTossModel
import com.example.declutteringapp.view.adapters.QuestionsViewPagerAdapter
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.math.abs
import kotlin.math.min


private const val MOVE_DISTANCE = 26
private const val MOVE_TIME = 50

class BuyOrSave : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: FragmentBuyOrSaveBinding
    private lateinit var adapter: QuestionsViewPagerAdapter

    var screenWidth =0
    var yesClick=0
    var nClick=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuyOrSaveBinding.inflate(inflater, container, false)
        val view = binding.root

        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        screenWidth = resources.displayMetrics.widthPixels



        binding.yesButton.clicks()

            .onEach {
                firstClick()
            }
            .onEach {
                yesClick++
            }
            .onEach {
                val animation = AnimationUtils.loadAnimation(context, R.anim.shake)
                binding.imageToss.startAnimation(animation)
            }
            .onEach {
                val animation = AnimationUtils.loadAnimation(context, R.anim.line_shake)
                binding.lines.startAnimation(animation)
            }
            .onEach {
                secondClick()
                if (nClick+yesClick>= 12  || yesClick>= 12 || nClick>= 12) {
                    binding.noButton.isEnabled=false
                    binding.yesButton.isEnabled=false
                    val builder = AlertDialog.Builder(context)
                    if(nClick>yesClick){
                        builder.setMessage("Save Your Money \uD83D\uDCB0")
                            .setCancelable(false)
                            .setPositiveButton("Do something else") { dialog, id ->
                                findNavController().navigate(R.id.action_buyOrSave_to_startFragment2)
                            }
                            .setNegativeButton("Start Again") { dialog, id ->
                                findNavController().navigate(R.id.action_buyOrSave_self)

                            }
                        val alert = builder.create()
                        alert.show()}else{ builder.setMessage("Buy it \uD83D\uDCB8  ")
                        .setCancelable(false)
                        .setPositiveButton("Do something else") { dialog, id ->
                            findNavController().navigate(R.id.action_buyOrSave_to_startFragment2)
                        }
                        .setNegativeButton("Start Again") { dialog, id ->
                            findNavController().navigate(R.id.action_buyOrSave_self)}
                        val alert = builder.create()
                        alert.show()
                        alert.getWindow()?.setGravity(Gravity.TOP)
                    }
                }else{}

            }.launchIn(lifecycleScope)

        binding.noButton.clicks()
            .onEach {
                firstClickN()
            }
            .onEach {
                nClick++

            }
            .onEach {
                val animation = AnimationUtils.loadAnimation(context, com.example.declutteringapp.R.anim.shake)
                binding.imageToss.startAnimation(animation)
            }
            .onEach {
                val animation = AnimationUtils.loadAnimation(context, R.anim.line_shake)
                binding.lines.startAnimation(animation)
            }
            .onEach {
                secondClickN()
                if (nClick+yesClick>= 12  || yesClick>= 12 || nClick>= 12) {
                    binding.noButton.isEnabled=false
                    binding.yesButton.isEnabled=false
                    val builder = AlertDialog.Builder(context)
                    if(nClick>yesClick){
                        builder.setMessage("Save your money \uD83D\uDCB0")
                            .setCancelable(false)
                            .setPositiveButton("Do something else") { dialog, id ->
                                findNavController().navigate(R.id.action_buyOrSave_to_startFragment2)
                            }
                            .setNegativeButton("Start Again") { dialog, id ->
                                findNavController().navigate(R.id.action_buyOrSave_self)

                            }
                        val alert = builder.create()
                        alert.show()}else{ builder.setMessage("Buy it \uD83D\uDCB8 ")
                        .setCancelable(false)
                        .setPositiveButton("Do something else") { dialog, id ->
                            findNavController().navigate(R.id.action_buyOrSave_to_startFragment2)
                        }
                        .setNegativeButton("Start Again") { dialog, id ->
                            findNavController().navigate(R.id.action_buyOrSave_self)}
                        val alert = builder.create()
                        alert.show()
                        alert.getWindow()?.setGravity(Gravity.TOP)
                    }
                }else{}
            }
            .launchIn(lifecycleScope)


        val listQuestions = mutableListOf<KeepOrTossModel>()

        adapter = QuestionsViewPagerAdapter(requireContext(), listQuestions)

        val adapter = QuestionsViewPagerAdapter(requireContext(), listQuestions)

        binding.questionsViewpager.adapter = adapter

        binding.questionsViewpager.isUserInputEnabled = false


        if(binding.questionsViewpager.currentItem == adapter.itemCount -1 ){
            Toast.makeText(context, "Keep it", Toast.LENGTH_LONG).show()
        }



        listQuestions.add(KeepOrTossModel("Would I Buy It for Full Price?", 1))
        listQuestions.add(KeepOrTossModel("Do You Already Own Something Similar?", 2))
        listQuestions.add(KeepOrTossModel("Can You Borrow It?", 3))
        listQuestions.add(KeepOrTossModel("Will You Still Love This in a Year?", 4))
        listQuestions.add(KeepOrTossModel("Is It Worth the Cost?", 5))
        listQuestions.add(KeepOrTossModel("Will This Add Value to Your life?", 6))
        listQuestions.add(KeepOrTossModel("Is This The Best Use of This Money?", 7))
        listQuestions.add(KeepOrTossModel("Would The Best Version of You Buy This?", 8))
        listQuestions.add(KeepOrTossModel("Do You Really Need It?", 9))
        listQuestions.add(KeepOrTossModel(
            "Do You Have Time To Maintain It, Space To Keep It, and Engery To Take Care of It?",
            10))
        listQuestions.add(KeepOrTossModel("How Often Will You Use It?", 11))
        listQuestions.add(KeepOrTossModel("Is There a Place Where You Could Buy The Exact Item For Less Money?", 12))
        listQuestions.add(KeepOrTossModel("Will This Improve Your Life", 13))


    }


    fun View.clicks() = callbackFlow<Unit> {
        setOnClickListener {
            trySend(Unit)
        }
        awaitClose { setOnClickListener(null) }

    }


    private fun distanceToEdge(left: Boolean): Int {
        val location = IntArray(2)
        binding.imageToss.getLocationOnScreen(location)
        val x = location[0]
        val imageWidth = binding.imageToss.width
        return if (left) x
        else screenWidth -  (x + imageWidth)
    }


    private fun moveImage(distance: Int) {
        val fraction = distance.toFloat() / MOVE_DISTANCE
        val duration = abs(MOVE_TIME * fraction).toLong()
        binding.imageToss.animate().setDuration(duration).translationXBy(distance.toFloat())

    }

    fun firstClick() {
        binding.yesButton.clicks()
        val distance = min(distanceToEdge(true), MOVE_DISTANCE)
        moveImage(-distance)

    }

    fun secondClick(){
        binding.yesButton.clicks()
        if (binding.questionsViewpager.scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            binding.questionsViewpager.setCurrentItem(
                binding.questionsViewpager.currentItem.plus(
                    1
                ), true
            )
        } else {
        }
    }

    suspend fun firstClickN(){
        binding.noButton.clicks()
        val distance = min(distanceToEdge(false), MOVE_DISTANCE)
        moveImage(distance)
    }

    suspend fun secondClickN(){
        binding.noButton.clicks()
        if (binding.questionsViewpager.scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            binding.questionsViewpager.setCurrentItem(
                binding.questionsViewpager.currentItem.plus(
                    1
                ), true
            )
        } else {
        }
    }

}

