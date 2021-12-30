package com.example.declutteringapp

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.declutteringapp.databinding.FragmentEditSpaceBinding
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class EditSpaceFragment : Fragment() {


    lateinit var editeRoomName: EditText
    lateinit var roomStutus: Spinner
    lateinit var saveBtn: Button
    lateinit var roomImage:ImageView
    lateinit var viewModel: SpaceViewModel
    lateinit var ImageCamera: ImageButton
    lateinit var ImageGallary: ImageButton
    private var photoFile: File? = null
    private var mCurrentPhotoPath: String? = null
    private var selectedImagePath = ""
    lateinit var  adapterSpace:SpaceRvAdapter
    private lateinit var _binding: FragmentEditSpaceBinding
    private val binding get() = _binding!!
    var spaceID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditSpaceBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editeRoomName = binding.etRoomName
        roomStutus = binding.idRoomStatus
roomImage=binding.ivSpaceImage
        saveBtn = binding.idBtn

        ImageCamera = binding.imgCameraSpace
        ImageGallary = binding.imgUploadSpace

        ImageCamera.setOnClickListener {
            captureImage()
        }

        val pickImages = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { binding.ivSpaceImage.setImageURI(uri) }
        }
        ImageGallary.setOnClickListener {

            pickImages.launch("image/*")

        }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(SpaceViewModel::class.java)


        fun createSpace(it: View?) {

            val roomName= binding.etRoomName.text.toString()
            //    val roomStatus= binding.idRoomStatus.toString()
            var spinnerText=roomStutus.selectedItem.toString()
            roomImage=binding.ivSpaceImage

            Glide.with(this)
                .load(selectedImagePath)
                .into(roomImage)

            val data =Space(status = spinnerText, roomName = roomName, imgPath = mCurrentPhotoPath)
            viewModel.addSpace(data)
            Toast.makeText(requireContext(),"You Added a Room!", Toast.LENGTH_SHORT).show()
        }




        saveBtn.setOnClickListener {
         createSpace(it)

            findNavController().navigate(R.id.action_editSpaceFragment_to_mySpaceFragment22)
            initViews()

        }


        val roomState = resources.getStringArray(R.array.room_spinner)


        if (roomStutus != null) {
            val adapter = ArrayAdapter(requireContext(),
                R.layout.support_simple_spinner_dropdown_item, roomState)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            roomStutus.adapter = adapter
            roomStutus.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(requireContext(),
                        getString(R.string.selected_item) + " " +
                                "" + positionitem, Toast.LENGTH_SHORT).show()
                }
                var positionitem: String = roomStutus.getSelectedItemPosition().toString()

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }}}

   private  val getActionTakePicture =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {

           binding.ivSpaceImage.setImageURI(photoFile!!.toUri())

/*
                if (RESULT_OK === RESULT_LOAD_IMAGE && resultCode === RESULT_OK && data != null) {
                    val selectedImageURI: Uri = android.R.attr.data.getData()
                    val placeWorkModel = Place() // the model between activity and adapter
                    placeWorkModel.setPhoto(convertImage2Base64().toInt()) // here i pass the photo
                    picturesList.add(placeWorkModel)
                    adapter.updateList(picturesList) // add this
                    mAdapter.notifyDataSetChanged()
                }
                */


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
                            "com.group2.closestevent.contentprovider",
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
        if (spaceID != -1) {

            val roomName= binding.etRoomName.text.toString()
            //    val roomStatus= binding.idRoomStatus.toString()
            var spinnerText=roomStutus.selectedItem.toString()
      var   roomImages= binding.ivSpaceImage.toString()
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                context?.let {
                    var space = Space(roomName, spinnerText, roomImages)
                    selectedImagePath = space.imgPath!!
                    binding.ivSpaceImage.setImageBitmap(BitmapFactory.decodeFile(space.imgPath))
                    binding.ivSpaceImage.visibility = View.VISIBLE


                }
            }
        }
    }

    @Throws(IOException::class)
     fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.absolutePath
        return image
    }



    /*private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) {bitmap ->
      bitmap?.let {
        bi.imgCameraPic.setImageBitmap(it)
        bi.imgCameraPic.visibility = View.VISIBLE
      }
    }*/

/*    private val askLocationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Log.e("TAG", "Permission is granted")
            } else {
                Log.e("TAG", "No Permission")
            }
        }

    private val askMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
            for (entry in map.entries) {
                Toast.makeText(context, "${entry.key} = ${entry.value}", Toast.LENGTH_SHORT).show()
            }
        }*/

/*
private val takePicture =
    registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let { binding.ivSpaceImage.setImageBitmap(bitmap) }
    }*/




}





