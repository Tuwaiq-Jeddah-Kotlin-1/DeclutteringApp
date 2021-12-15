package com.example.declutteringapp




import android.os.Bundle

import android.os.CountDownTimer
import android.os.SystemClock
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.declutteringapp.databinding.FragmentFiveMinBinding
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
class FiveMinFragment : Fragment() {

    var START_MILLI_SECONDS = 60000L

    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false;
    var time_in_milli_seconds = 0L

    private lateinit var binding: FragmentFiveMinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiveMinBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            binding.button.setOnClickListener {
                if (isRunning) {
                    pauseTimer()
                } else {
                    val time  = 5
                    time_in_milli_seconds = time.toLong() *60000L
                    startTimer(time_in_milli_seconds)
                }
            }

            binding.reset.setOnClickListener {
                resetTimer()
            }


        }

        private fun pauseTimer() {

            binding.button.text = "Start"
            countdown_timer.cancel()
            isRunning = false
            binding.reset.visibility = View.VISIBLE
        }

        private fun startTimer(time_in_seconds: Long) {
            countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
                override fun onFinish() {
                    loadConfeti()
                }

                override fun onTick(p0: Long) {
                    time_in_milli_seconds = p0
                    updateTextUI()
                }
            }
            countdown_timer.start()

            isRunning = true
            binding.button.text = "Pause"
            binding.reset.visibility = View.INVISIBLE

        }

        private fun resetTimer() {
            time_in_milli_seconds = START_MILLI_SECONDS
            updateTextUI()
            binding.reset.visibility = View.INVISIBLE
        }

        private fun updateTextUI() {
            val minute = (time_in_milli_seconds / 1000) / 60
            val seconds = (time_in_milli_seconds / 1000) % 60

            binding.timer.text = "$minute:$seconds"
        }


         fun loadConfeti() {
             binding.viewKonfetti.build()
                 .addShapes(Shape.Square, Shape.Circle)
                 .setDirection(0.0, 359.0)
                 .setSpeed(1f, 5f)
                 .setFadeOutEnabled(true)
                 .setTimeToLive(2000L)
                 .addSizes(Size(12))
                 .setPosition(-50f, binding.viewKonfetti.width + 50f, -50f, -50f)
                 .streamFor(300, 5000L)
        }
    }


       /*         binding.btn.setText(if (isWorking) R.string.start else R.string.stop)

                Toast.makeText(
                    context, getString(
                        if (isWorking)
                            R.string.working
                        else
                            R.string.stopped
                    ),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
*/


/*    var isWorking = false

    val meter = binding.cMeter

    override fun onClick(v: View) {

        if (!isWorking) {
            meter.start()
            isWorking = true
        } else {
            meter.stop()
            isWorking = false
        }

}*/















