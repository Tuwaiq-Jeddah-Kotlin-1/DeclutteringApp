package com.example.declutteringapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.declutteringapp.view.SingleLiveEvent

class OnBoardingViewModel: ViewModel()  {
        internal val nextPageEvent = SingleLiveEvent<Int>()
    }
