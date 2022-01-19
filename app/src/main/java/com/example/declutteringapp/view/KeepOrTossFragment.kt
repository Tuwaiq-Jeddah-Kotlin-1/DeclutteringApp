package com.example.declutteringapp.view

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AbsListView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
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

private const val MOVE_DISTANCE = 40
private const val MOVE_TIME = 50

class KeepOrTossFragment : Fragment(), QuestionsViewPagerAdapter.ResultDialog {

    private lateinit var binding: FragmentKeepOrTossBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var adapter: QuestionsViewPagerAdapter

    val displayMetrics = DisplayMetrics()
    var screenWidth =0
    val sharedPref =activity?.getPreferences(Context.MODE_PRIVATE)
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
//        var screenWidth = resources.displayMetrics.widthPixels / resources.displayMetrics.density
        var xdpi = displayMetrics.xdpi
        var ydpi = displayMetrics.ydpi


        //  screenWidth = resources.displayMetrics.widthPixels


            return if (left) x
            else screenWidth -  (x + imageWidth)
    }








    private fun moveImage(distance: Int) {

    val fraction = distance.toFloat() / MOVE_DISTANCE

    val duration = abs(MOVE_TIME * fraction).toLong()
    binding.imageToss.animate().setDuration(duration).translationXBy(distance.toFloat())

}


        suspend fun firstClick() {

            binding.yesButton.clicks()
            val distance = min(distanceToEdge(true), MOVE_DISTANCE)
            moveImage(-distance)

            val listQuestions = mutableListOf<KeepOrTossModel>()

            val adapter = QuestionsViewPagerAdapter( requireContext(),listQuestions,this)

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

        val distance = min(distanceToEdge(false), MOVE_DISTANCE)
        moveImage(distance)
        if (distanceToEdge(false) ==0){
            Toast.makeText(context, "Toss it", Toast.LENGTH_LONG).show()

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
        var score = sharedPref?.getInt("Score", 0)

        screenWidth = resources.displayMetrics.widthPixels
        //  var scorePref:Int = finalScore
        sharedPreferences =
            this.requireActivity().getSharedPreferences("preference", Context.MODE_PRIVATE)
        // scoreSave = sharedPreferences.getInt("SCORE", scorePref)


        val editor: SharedPreferences.Editor =
            sharedPreferences.edit()
        //editor.putInt("SCORE", scorePref)
        editor.apply()


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
                secondClick()
            }.launchIn(lifecycleScope)

        binding.noButton.clicks()
            .onEach {
                firstClickN()
            }
            .onEach {
                nClick++
            }
            .onEach {
                val animation = AnimationUtils.loadAnimation(context, R.anim.shake)
                binding.imageToss.startAnimation(animation)
            }
            .onEach {
                secondClickN()
            }.launchIn(lifecycleScope)

        val listQuestions = mutableListOf<KeepOrTossModel>()
        adapter = QuestionsViewPagerAdapter(requireContext(), listQuestions, this)

        val adapter = QuestionsViewPagerAdapter(requireContext(), listQuestions, this)

        binding.questionsViewpager.adapter = adapter
        //adapter.itemCount-12
        binding.questionsViewpager.isUserInputEnabled = false


        /*    if(binding.questionsViewpager.currentItem == adapter.itemCount -1 ){
          *//* yesClick>nClick ->
            Toast.makeText(context, "Keep it", Toast.LENGTH_LONG).show()
            nClick>yesClick ->  *//* Toast.makeText(context, "Toss it", Toast.LENGTH_LONG).show()
        }*/


        listQuestions.add(KeepOrTossModel("have you used it in the last year?", 1))
        listQuestions.add(KeepOrTossModel("You can't replace it with 20 SAR or less?", 2))
        listQuestions.add(KeepOrTossModel("Do you use it regularly?", 3))
        listQuestions.add(KeepOrTossModel("Will you use it in the next month?", 4))
        listQuestions.add(KeepOrTossModel("Does it have a place?", 5))
        listQuestions.add(
            KeepOrTossModel(
                "If you moved to another country would you take it with you?",
                6
            )
        )
        listQuestions.add(KeepOrTossModel("Would you replace or re-buy this item?", 7))
        listQuestions.add(KeepOrTossModel("Does it make you feel good?", 8))
        listQuestions.add(KeepOrTossModel("Does it mean something to you", 9))
        listQuestions.add(
            KeepOrTossModel(
                "Is the item adding enough value to your life to justify the tim, space and energy it takes up?",
                10
            )
        )
        listQuestions.add(KeepOrTossModel("does it have a place", 11))
        listQuestions.add(KeepOrTossModel("You don't have similar items like it?", 12))


        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )


        val cardView = view.findViewById<CardView>(R.id.card_View)
    }

        /*  getScreenShotFromView(cardView)
        Glide.with(this)
            .load(Uri.fromFile())
            .into(roomImage)*/

override fun dilog() {

        val builder = AlertDialog.Builder(context)

        builder.setTitle("You're Done")
        builder.setMessage("Hopefully you can see to witch side the item is closer ")
        builder.setPositiveButton("see the result") { dialog, which ->
            findNavController().navigate(R.id.action_keepOrTossFragment_to_resultFragment)

            builder.setCancelable(false)
            builder.show()
        }
    }
private fun getScreenShotFromView(v: View): Bitmap? {
    var screenshot: Bitmap? = null
    try {
        screenshot = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
        // Now draw this bitmap on a canvas
        val canvas = Canvas(screenshot)
        v.draw(canvas)
    } catch (e: Exception) {
        Log.e("GFG", "Failed to capture screenshot because:" + e.message)
    }
    return screenshot
}



/*     if(  binding.questionsViewpager.currentItem == binding.questionsViewpager.adapter?.itemCount?.minus(1)) {
         val builder = AlertDialog.Builder(context)
         builder.setTitle("toss it")
         builder.setMessage("toss it")
         builder.show()
     }else{}
*/

        /*   val builder = AlertDialog.Builder(context)
        builder.setTitle("toss it")
        builder.setMessage("toss it")
        builder.show()*/
/*
        when (adapter.position == adapter.itemCount - 1) {
            nClick > yesClick -> Toast.makeText(context, "Tossssssss it", Toast.LENGTH_LONG).show()
            nClick < yesClick -> Toast.makeText(context, "keep it", Toast.LENGTH_LONG).show()

        }*/


    }

/*


*/

















