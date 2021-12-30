package com.example.declutteringapp

import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.example.declutteringapp.databinding.FragmentKeepOrTossBinding
import com.example.declutteringapppackage.QuestionsViewPagerAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val MOVE_DISTANCE = 60
private const val MOVE_TIME = 40

class KeepOrTossFragment : Fragment(){

    private lateinit var binding: FragmentKeepOrTossBinding
    private lateinit var sharedPreferences: SharedPreferences

var scoreSave=0
    val displayMetrics = DisplayMetrics()
    var screenWidth =0
  //  private lateinit var finalScore:TextView




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


    fun View.clicks() = callbackFlow<Unit> {
        setOnClickListener {
            trySend(Unit)
        }
        awaitClose { setOnClickListener(null) }

    }

   // var finalScore = binding.scoreNumber.text.toString().toInt()


    private fun score(taskDone:Boolean) {}
      //  finalScore= score

 /*       var taskDone: Boolean
        taskDone = false
        when (taskDone) {
            true -> score++
            false -> score
        }}*/

 /*   fun snackBarShow(){
        val snackBar = Snackbar.make(requireView(), "Replace with your own action",
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
        val snackBarView = snackBar.view
        snackBar.show()
    }*/


    private fun distanceToEdge(left: Boolean): Int {


        val location = IntArray(2)
        binding.imageToss.getLocationOnScreen(location)
        val x = location[0]

        val imageWidth =  binding.imageToss.width

        //screenWidth = displayMetrics.widthPixels


    return if (left) x
    else screenWidth - (x + imageWidth)


    }






    private fun moveImage(distance: Int) {

    val fraction = distance.toFloat() / MOVE_DISTANCE

    val duration = Math.abs(MOVE_TIME * fraction).toLong()
    binding.imageToss.animate().setDuration(duration).translationXBy(distance.toFloat())

}


        suspend fun firstClick() {

            binding.yesButton.clicks()
            val distance = Math.min(distanceToEdge(true), MOVE_DISTANCE)
            moveImage(-distance)

            if (distanceToEdge(true) == 0 ) {
                Toast.makeText(context, "Keep it, you earned 50 Points", Toast.LENGTH_LONG).show()
               // score(taskDone = true)

            } else {
            }



        }

    suspend fun secondClick(){
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

        val distance = Math.min(distanceToEdge(true), MOVE_DISTANCE)
        moveImage(distance)
        if (distanceToEdge(false) ==0){
            Toast.makeText(context, "Tossss it", Toast.LENGTH_LONG).show()

        }else{}


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



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        screenWidth =  requireContext().resources.displayMetrics.widthPixels
        //  var scorePref:Int = finalScore

        sharedPreferences =
            this.requireActivity().getSharedPreferences("preference", Context.MODE_PRIVATE)
        // scoreSave = sharedPreferences.getInt("SCORE", scorePref)


        val editor: SharedPreferences.Editor =
            sharedPreferences.edit()
        //editor.putInt("SCORE", scorePref)
        editor.apply()


        binding.yesButton.clicks()
            .onEach { firstClick()
            }
            .onEach {      val animation = AnimationUtils.loadAnimation(context, R.anim.shake)
                binding.imageToss.startAnimation(animation)
            }
            .onEach { secondClick()
            }.launchIn(lifecycleScope)

        binding.noButton.clicks()
            .onEach { firstClickN()
            }
            .onEach { val animation = AnimationUtils.loadAnimation(context, R.anim.shake)
                binding.imageToss.startAnimation(animation) }
            .onEach { secondClickN()
            }.launchIn(lifecycleScope)



        val listQuestions = mutableListOf<KeepOrTossModel>()

        val adapter = QuestionsViewPagerAdapter(context, listQuestions)

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
        listQuestions.add(KeepOrTossModel("Is the item adding enough value to your life to justify the tim, space and energy it takes up?", 10))
        listQuestions.add(KeepOrTossModel("does it have a place", 11))
        listQuestions.add(KeepOrTossModel("You don't have similar items like it?", 12))

        /*  var score = 6
         var scoreNumbers= binding.scoreNumber
          scoreNumbers.text= score.toString()
  */
        /*       var score = 6
               var scoreNumbers= binding.scoreNumber
               score.text= scoreNumbers.toString()*/
        //  finalScore=binding.scoreNumber




    }









}



