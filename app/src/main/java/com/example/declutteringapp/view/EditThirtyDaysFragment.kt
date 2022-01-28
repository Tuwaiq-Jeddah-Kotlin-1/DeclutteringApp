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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
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
import com.example.declutteringapp.databinding.EditThirtyDaysFragmentBinding
import com.example.declutteringapp.model.ThirtyDays
import com.example.declutteringapp.viewmodel.DaysViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class EditThirtyDaysFragment: Fragment(){

    lateinit var viewModelDay: DaysViewModel
    lateinit var saveBtn: Button
    lateinit var ImageCamera: ImageButton
    lateinit var imagePlace: ImageView

    var dayID = -1

    private var photoFile: File? = null
    private var mCurrentPhotoPath: String? = null
    private var selectedImagePath = ""


    private lateinit var binding:  EditThirtyDaysFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditThirtyDaysFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      initViews()

    viewModelDay = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(DaysViewModel::class.java)

        ImageCamera = binding.imgCamera

        imagePlace = binding.imagePlacement

        saveBtn = binding.btnSubmitDay

        imagePlace.setOnClickListener{
            captureImage()
        }

        ImageCamera.setOnClickListener {
            captureImage()
        }


        binding.btnSubmitDay.setOnClickListener {
            val image = mCurrentPhotoPath

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                context?.let {
                    val data =
                        ThirtyDays(dayNum = 0, imgPath = image)

                    if (image.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "please add an image!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        if (data != null) {
                            viewModelDay.addDay(data)
                        }

                        findNavController().navigate(R.id.action_thirtyDaysEditDialogFragment_to_thirtyDaysFragment)

                        Toast.makeText(
                            requireContext(),
                            "You Added Today Items",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

            }
        }}

    private fun createSpace(it: View?) {

            }


            private val getActionTakePicture =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it.resultCode == Activity.RESULT_OK) {

                        binding.imagePlacement.setImageURI(mCurrentPhotoPath!!.toUri())
                    } else {
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
                        takePictureIntent?.also {
                            photoFile = try {
                                createImageFile()
                            } catch (ex: IOException) {

                                null
                            }
                            photoFile?.also {
                                val photoURI: Uri = FileProvider.getUriForFile(
                                    requireActivity().applicationContext,
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

                            var image= mCurrentPhotoPath

                            var day = ThirtyDays(0, image)

                         viewModelDay.addDay(day)

                            selectedImagePath = day.imgPath!!
                            imagePlace.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath))
                            imagePlace.visibility = View.VISIBLE
                        }
                    }
                }
            }


            @Throws(IOException::class)
            private fun createImageFile(): File {
                val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val imageFileName = "JPEG_" + timeStamp + "_"
                val storageDir = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val image = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir
                )
                mCurrentPhotoPath = image.absolutePath
                return image
            }}











