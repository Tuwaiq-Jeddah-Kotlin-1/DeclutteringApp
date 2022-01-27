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
import com.example.declutteringapp.databinding.FragmentKeepOrTossBinding
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

class KeepOrTossFragment : Fragment() {

    private lateinit var binding: FragmentKeepOrTossBinding
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
        binding = FragmentKeepOrTossBinding.inflate(inflater, container, false)
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
                binding.line.startAnimation(animation)
            }
            .onEach {
                secondClick()
                if (nClick+yesClick>= 12  || yesClick>= 12 || nClick>= 12) {
                    binding.noButton.isEnabled=false
                    binding.yesButton.isEnabled=false
                    val builder = AlertDialog.Builder(context)
                    if(nClick>yesClick){
                        builder.setMessage("Tosss \uD83D\uDC4B")
                            .setCancelable(false)
                            .setPositiveButton("Do something else") { dialog, id ->
                                findNavController().navigate(R.id.action_keepOrTossFragment_to_startFragment2)
                            }
                            .setNegativeButton("Start Again") { dialog, id ->
                                findNavController().navigate(R.id.action_keepOrTossFragment_self)

                            }
                        val alert = builder.create()
                        alert.show()

                        alert.getWindow()?.setGravity(Gravity.TOP)


                    }else{ builder.setMessage("Keep \uD83D\uDC4D")
                        .setCancelable(false)
                        .setPositiveButton("Do something else") { dialog, id ->
                            findNavController().navigate(R.id.action_keepOrTossFragment_to_startFragment2)
                        }
                        .setNegativeButton("Start Again") { dialog, id ->
                            findNavController().navigate(R.id.action_keepOrTossFragment_self)}
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
                binding.line.startAnimation(animation)
            }
            .onEach {
                secondClickN()
                if (nClick+yesClick>= 12  || yesClick>= 12 || nClick>= 12) {
                    binding.noButton.isEnabled=false
                    binding.yesButton.isEnabled=false
                  Toast.makeText(context, "Well done", Toast.LENGTH_LONG).show()
                    val builder = AlertDialog.Builder(context)
                    if(nClick>yesClick){
                    builder.setMessage("Tosss \uD83D\uDC4B")
                        .setCancelable(false)
                        .setPositiveButton("Do something else") { dialog, id ->
                            findNavController().navigate(R.id.action_keepOrTossFragment_to_startFragment2)
                        }
                        .setNegativeButton("Start Again") { dialog, id ->
                            findNavController().navigate(R.id.action_keepOrTossFragment_self)}
                    val alert = builder.create()
                    alert.show()
                        alert.getWindow()?.setGravity(Gravity.TOP)

                    }else{ builder.setMessage("Keep\uD83D\uDC4D")
                        .setCancelable(false)
                        .setPositiveButton("Do something else") { dialog, id ->
                            findNavController().navigate(R.id.action_keepOrTossFragment_to_startFragment2)
                        }
                            .setNegativeButton("Start Again") { dialog, id ->
                                findNavController().navigate(R.id.action_keepOrTossFragment_self)}
                        val alert = builder.create()
                        alert.show()
                        alert.getWindow()?.setGravity(Gravity.TOP)
                    }
                }else{}
            }
            .launchIn(lifecycleScope)


        if (nClick > yesClick) {
            Toast.makeText(context, "nnnnnnnn", Toast.LENGTH_LONG).show()

        } else if (nClick > yesClick) {
            Toast.makeText(context, "kkkkkkk", Toast.LENGTH_LONG).show()
        } else {
        }
        val listQuestions = mutableListOf<KeepOrTossModel>()

        adapter = QuestionsViewPagerAdapter(requireContext(), listQuestions)

        val adapter = QuestionsViewPagerAdapter(requireContext(), listQuestions)

        binding.questionsViewpager.adapter = adapter

        binding.questionsViewpager.isUserInputEnabled = false






        listQuestions.add(KeepOrTossModel("have you used it in the last year?", 1))
        listQuestions.add(KeepOrTossModel("You can't replace it with 20 SAR or less?", 2))
        listQuestions.add(KeepOrTossModel("Do you use it regularly?", 3))
        listQuestions.add(KeepOrTossModel("Will you use it in the next month?", 4))
        listQuestions.add(KeepOrTossModel("Does it have a place?", 5))
        listQuestions.add(KeepOrTossModel("If you moved to another country would you take it with you?", 6))
        listQuestions.add(KeepOrTossModel("Would you replace or re-buy this item?", 7))
        listQuestions.add(KeepOrTossModel("Does it make you feel good?", 8))
        listQuestions.add(KeepOrTossModel("Does it mean something to you", 9))
        listQuestions.add(KeepOrTossModel(
                "Is the item adding enough value to your life to justify the time, space and energy it takes up?",
                10
            )
        )
        listQuestions.add(KeepOrTossModel("does it have a place", 11))
        listQuestions.add(KeepOrTossModel("If you didn’t have this item, could you use something else in its place?", 12))
        listQuestions.add(KeepOrTossModel("You don't have similar items like it?", 13))

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

     fun firstClickN(){
        binding.noButton.clicks()
        val distance = min(distanceToEdge(false), MOVE_DISTANCE)
        moveImage(distance)
    }

     fun secondClickN(){
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







