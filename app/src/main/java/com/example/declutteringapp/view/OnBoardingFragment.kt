package com.example.declutteringapp.view
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.declutteringapp.R
import com.example.declutteringapp.model.Item
import com.example.declutteringapp.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import androidx.annotation.RawRes
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.databinding.FragmentOnBoardingBinding
import java.io.InputStream


class OnBoardingFragment : Fragment() {

    private lateinit var _binding: FragmentOnBoardingBinding
    private val languages = arrayOf("Java","Python","Kotlin","Scala","C++")
    private var index = 0
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init()
    }

    private fun init() {
        val viewModel = ViewModelProviders.of(this).get(OnBoardingViewModel::class.java)

        val adapter = LocalAdapter(viewModel)
        val items = listOf(
            Item("Start decluttring, and spend more time living and keeping only what matters",  R.raw.ic_leavs),
                    Item("use our tools to help you decides what to keep and what to toss.",R.drawable.interior_with_cats),
            Item("use our motivating Challenges to keep you on track, and earn points",  R.drawable.ic_add)
        )
        adapter.replaceItems(items)
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewpager.adapter = adapter
        // disable scrolling
        // viewPager.isUserInputEnabled = false
        // viewPager.currentItem = 1
        binding.viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                Timber.d("page=$position")
            }
        })

        viewModel.nextPageEvent.observe(this, Observer { position ->
            binding.viewpager.setCurrentItem(position, true)
        })

        binding?.indicator?.setViewPager(binding?.viewpager)

/*
        // accessing the TextSwitcher from XML layout
        val textSwitcher = binding.textSwitcher
        textSwitcher.setFactory {
            val textView = TextView(activity)
            textView.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            textView.textSize = 32f
            textView.setTextColor(Color.BLUE)
            textView
        }
        textSwitcher.setText(languages[index])
            // Your code logic goes here.

        val textIn = AnimationUtils.loadAnimation(
            context, android.R.anim.slide_in_left)
        textSwitcher.inAnimation = textIn

        val textOut = AnimationUtils.loadAnimation(
            activity, android.R.anim.slide_out_right)
        textSwitcher.outAnimation = textOut*/

        /*if (index == arr.length - 1) {
            index = 0;
            textSwitcher.setText(arr[index]);
        }
        else {
            textSwitcher.setText(arr[++index]);
        }*/
/*
       val out: Animation = AlphaAnimation(1.0f, 0.0f)
        out.setRepeatCount(Animation.INFINITE)
        out.setRepeatMode(Animation.REVERSE)
        out.setDuration(3000)
        textSwitcher.startAnimation(out)

        val inn: Animation = AlphaAnimation(1.0f, 0.0f)
        out.setRepeatCount(Animation.INFINITE)
        out.setRepeatMode(Animation.REVERSE)
        out.setDuration(3000)
        textSwitcher.startAnimation(inn)

      index = if (index - 1 >= 0) index - 1 else 4
          textSwitcher.setText(languages[index])

          index = if (index + 1 < languages.size) index + 1 else 0

          textSwitcher.setText(languages[index])*/

        fun Context.getRawInput(@RawRes resourceId: Int): InputStream {
            return resources.openRawResource(resourceId)
        }

        binding.getStarted.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_logInFragment)

        }
        binding.logInText.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_logInFragment)

        }

        // previous button functionality
    /*    val prev = findViewById<Button>(R.id.prev)
        prev.setOnClickListener {

        }*/
     /*   // next button functionality
        val button = findViewById<Button>(R.id.next)
        button.setOnClickListener {

        }*/

    }























/*

        val viewPager = binding.viewpager as ViewPager2
  //      viewPager.adapter = OnBoardingScreenViewPagerAdapter(activity.this)
*/

    }



/*
   lifecycleScope.launch {

        if(getOnBoardStatus()){
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment2)}
        else{

            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)}

        delay(3000)}


}



fun getOnBoardStatus(): Boolean {
    addPref()
    return this.requireActivity().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)!!.getBoolean("finished", false)
}


fun addPref()
{
    val shpf=requireContext().getSharedPreferences("OnBoarding", Context.MODE_PRIVATE)
    shpf.edit().apply {  putBoolean("finished",true)}.apply()
}


    */






       /* val fragmentList= arrayListOf<Fragment>(FirstFragment(),SecondFragment(),ThirdFragment())
        val viewpager=getView()?.findViewById<ViewPager2>(R.id.viewpager)
        viewpager?.adapter=OnBoardingScreenViewPagerAdapter(fragmentList,childFragmentManager,lifecycle)
*/
