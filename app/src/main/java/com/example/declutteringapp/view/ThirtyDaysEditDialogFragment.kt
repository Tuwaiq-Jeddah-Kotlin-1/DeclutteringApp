package com.example.declutteringapp.view


import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.*
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.DialogFragmentEditThirtyDaysBinding
import com.example.declutteringapp.model.Score
import com.example.declutteringapp.model.ThirtyDays
import com.example.declutteringapp.viewmodel.DaysViewModel
import com.example.declutteringapp.viewmodel.ScoreViewModel

import java.io.File
import java.io.IOException
import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.util.*


class ThirtyDaysEditDialogFragment: Fragment(){


 //   val  thirtysDayss  by navArgs()


    lateinit var viewModelDay: DaysViewModel
    lateinit var saveBtn: Button
    lateinit var ImageCamera: ImageButton
    lateinit var ImageGallary: ImageButton
    lateinit var itemNum: EditText
    lateinit var imagePlace: ImageView
    lateinit var scoreViewModel: ScoreViewModel


    var dayID = -1

    private var photoFile: File? = null
    private var mCurrentPhotoPath: String? = null
    private var selectedImagePath = ""


    private lateinit var binding: DialogFragmentEditThirtyDaysBinding


/*   companion object {
        @JvmStatic
        fun newInstance() =
            ThirtyDaysEditDialogFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentEditThirtyDaysBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    viewModelDay = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(DaysViewModel::class.java)

        ImageCamera = binding.imgCamera
        ImageGallary = binding.imgUpload

        imagePlace = binding.imagePlacement
        itemNum = binding.etItemNumber

        saveBtn = binding.btnSubmitDay

        val pickImages = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { binding.imagePlacement.setImageURI(uri) }
        }
        ImageCamera.setOnClickListener {
            captureImage()
        }

        ImageGallary.setOnClickListener {
            pickImages.launch("image/*")

            //getActionTakePicture.launch("image/*")

        }
        initViews()
        scoreViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(ScoreViewModel::class.java)

        binding.btnSubmitDay.setOnClickListener {
            createSpace(it)
            findNavController().navigate(R.id.action_thirtyDaysEditDialogFragment_to_thirtyDaysFragment)
            var score= Score(30)
            scoreViewModel.updateScore(score)

        }
      /*  saveBtn.setOnClickListener {
            createSpace(it)
            findNavController().navigate(R.id.action_thirtyDaysEditDialogFragment_to_thirtyDaysFragment)

        }*/

    }

    /*override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }*/


/*    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = activity?.let {

            Dialog(it)

        }*/


      /*  if (dialog != null) {
            dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
            dialog.window?.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(binding.root)
            binding?.apply {
*/



      /*          val fragment: Fragment
                val bundle = Bundle()
                bundle.putInt("dayId", -1)
                val itemCounts = bundle.putInt("itemNum", 0)
               val dayImage= bundle.putString("imgPath", "")
                        fragment = ThirtyDaysFragment.newInstance()
                fragment.arguments = bundle
                saveBtn.setText("Update Day")
                itemNum.setText("0")
*/






            /*  saveBtn.setOnClickListener {
                    // on below line we are getting
                    // title and desc from edit text.
                    val itemCounts = itemNum.text.toString()

                    val updatedDay = ThirtyDays(0,0,null)
                    updatedDay.id = dayID

                    viewModelDay.updateDay(updatedDay)
                    Toast.makeText(activity, "Day Updated..", Toast.LENGTH_LONG).show()

                    findNavController().navigate(R.id.action_thirtyDaysEditDialogFragment_to_thirtyDaysFragment)

                }*/


/*

                inputPos = arguments?.getInt("input_pos")
                inputCountss = arguments?.getString("input_name").toString()
                inputImage = arguments?.getString("input_image").toString()

                Glide.with(this@ThirtyDaysEditDialogFragment)
                    .load(inputImage)
                    .into(imagePlace)

*/


/*
    }


        return dialog!! }*/

private fun createSpace(it: View?) {

    var itemCounts= binding.etItemNumber.toString()
    val image=mCurrentPhotoPath


/*
if (itemCounts ==i){

}*/

       /* var day = ThirtyDays(0,  itemCounts, image)
       *//* selectedImagePath = day.imgPath!!
        imagePlace.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath))
        imagePlace.visibility = View.VISIBLE*/

    //   val daysNum= binding.tvDayNum.toString().toInt()
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        context?.let {
    val data =ThirtyDays(dayNum = 0,itemCounts=itemCounts, imgPath = image)

    viewModelDay.updateDay(data)

    Toast.makeText(requireContext(),"You updated the day!", Toast.LENGTH_SHORT).show()
}}


}
            private val getActionTakePicture =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it.resultCode == Activity.RESULT_OK) {

                        binding.imagePlacement.setImageURI(mCurrentPhotoPath!!.toUri())
                    } else {
                        // "Request cancelled or something went wrong."
                    }

                }


            private fun captureImage() {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ),
                        0
                    )
                } else {
                    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                        // Ensure that there's a camera activity to handle the intent
                        takePictureIntent?.also {
                            // Create the File where the photo should go
                            photoFile = try {
                                createImageFile()
                            } catch (ex: IOException) {
                                // Error occurred while creating the File

                                null
                            }
                            photoFile?.also {
                                val photoURI: Uri = FileProvider.getUriForFile(
                                    requireActivity().applicationContext,
                                    // "com.example.android.fileprovider"
                                    "com.example.declutteringapp.contentprovider",
                                    it
                                )
                                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                                getActionTakePicture.launch(takePictureIntent)
                            }
                        }
                    }
                }
            }

            private fun initViews() = binding.apply {
                if (dayID != -1) {

                    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                        context?.let {
                            val itemCounts= binding.etItemNumber.text.toString()
                            val image= mCurrentPhotoPath
                            var day = ThirtyDays(0,  itemCounts, image)
                           selectedImagePath = day.imgPath!!
                            imagePlace.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath))
                            imagePlace.visibility = View.VISIBLE
                        }
                    }
                }
            }


            @Throws(IOException::class)
            private fun createImageFile(): File {
                // Create an image file name
                val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val imageFileName = "JPEG_" + timeStamp + "_"
                val storageDir = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val image = File.createTempFile(
                    imageFileName, /* prefix */
                    ".jpg", /* suffix */
                    storageDir      /* directory */
                )
                // Save a file: path for use with ACTION_VIEW intents
                mCurrentPhotoPath = image.absolutePath
                return image
            }}











