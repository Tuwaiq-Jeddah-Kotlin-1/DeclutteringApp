package com.example.declutteringapp.view
import android.app.Application
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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.databinding.FragmentOnBoardingBinding
import com.example.declutteringapp.viewmodel.SpaceViewModel
import java.io.InputStream


class OnBoardingFragment : Fragment() {

    private lateinit var _binding: FragmentOnBoardingBinding
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
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(OnBoardingViewModel::class.java)

        val adapter = LocalAdapter(viewModel)
        val items = listOf(
            Item("Owning less is better than organizing more", R.drawable.onboarding_two),
            Item(
                "start decluttering,with our fun tools and  challenges!",
                R.drawable.onboarding_one
            ),
            Item(
                "get a clear image of each room in your house with our room miniMizer",
                R.drawable.onboarding_three
            )
        )
        adapter.replaceItems(items)
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewpager.adapter = adapter

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                Timber.d("page=$position")
            }
        })

        viewModel.nextPageEvent.observe(this, Observer { position ->
            binding.viewpager.setCurrentItem(position, true)
        })

        binding?.indicator?.setViewPager(binding?.viewpager)


        fun Context.getRawInput(@RawRes resourceId: Int): InputStream {
            return resources.openRawResource(resourceId)
        }

        binding.getStarted.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_logInFragment)

        }
        binding.logInText.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_logInFragment)

        }

    }

}