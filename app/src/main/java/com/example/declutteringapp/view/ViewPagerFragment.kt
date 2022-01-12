package com.example.declutteringapp.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {

    private lateinit var _binding: FragmentViewPagerBinding

    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList= arrayListOf<Fragment>(FirstFragment(),SecondFragment(),ThirdFragment())
        val viewpager=getView()?.findViewById<ViewPager2>(R.id.viewpager)
        viewpager?.adapter=OnBoardingScreenViewPagerAdapter(fragmentList,childFragmentManager,lifecycle)
        binding?.indicator?.setViewPager(binding?.viewpager)

    }


}