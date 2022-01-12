package com.example.declutteringapp.view

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.declutteringapp.databinding.FragmentEditSpaceBinding
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.R
import com.example.declutteringapp.model.Score
import com.example.declutteringapp.model.Space
import com.example.declutteringapp.model.ToDeclutter
import com.example.declutteringapp.viewmodel.ScoreViewModel
import com.example.declutteringapp.viewmodel.SpaceViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileDescriptor
import java.io.IOException
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

class EditSpaceFragment : Fragment() {


    private var isReadPermissionGranted = false
    private var isWriteingPermissionGranted = false
    private var isReadingPermissionGranted = false
    private lateinit var permissionLancher: ActivityResultLauncher<Array<String>>
    lateinit var scoreViewModel: ScoreViewModel

    lateinit var imageSelect: ImageButton
    lateinit var imageUpload: ImageButton
    lateinit var score: Score

    lateinit var editeRoomName: EditText
    lateinit var roomStutus: Spinner
    lateinit var roomNames: Spinner
    lateinit var saveBtn: Button
    lateinit var roomImage: ImageView
    lateinit var viewModel: SpaceViewModel
    lateinit var ImageCamera: ImageButton
    lateinit var ImageGallary: ImageButton
    private var photoFile: File? = null
    private var itemId = 0
    private var copiedImagePath: String? = null
    private var imageCollection: String? = null
    private var mCurrentPhotoPath: String? = null
    private var selectedImagePath = ""
    lateinit var adapterSpace: SpaceRvAdapter
    private lateinit var _binding: FragmentEditSpaceBinding
    private val binding get() = _binding!!
    var spaceID = -1
    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

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

        ImageCamera = binding.imgCameraSpace
        ImageGallary = binding.imgUploadSpace

        editeRoomName = binding.etRoomName
        roomStutus = binding.idRoomStatus
        roomNames = binding.idRoomName
        roomImage = binding.showRoomImage
        saveBtn = binding.idBtn

        permissionLancher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissoin ->

                isReadPermissionGranted =
                    permissoin[android.Manifest.permission.READ_EXTERNAL_STORAGE]
                        ?: isReadPermissionGranted
                isReadPermissionGranted =
                    permissoin[android.Manifest.permission.READ_EXTERNAL_STORAGE]
                        ?: isWriteingPermissionGranted

            }






        imageUpload = binding.imgCameraSpace

        imageUpload.setOnClickListener {
            captureImage()

        }


        initViews()

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(SpaceViewModel::class.java)


        scoreViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(ScoreViewModel::class.java)


        saveBtn.setOnClickListener {

            createSpace(it)

            findNavController().navigate(R.id.action_editSpaceFragment_to_mySpaceFragment22)

            Toast.makeText(requireContext(), "You Added a Room!", Toast.LENGTH_SHORT).show()


        }
/*
          score=Score(30)

                   scoreViewModel.updateScore(score)*/


/*        val score = sharedPref.getInt("Score", 0)


        var roomsImages=  Glide.with(this)
            .load(Uri.fromFile())
            .into(roomImage)
*/


        val pickImages = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            binding.showRoomImage.setImageURI(uri)
        }

        ImageGallary.setOnClickListener {
            pickImages.launch("image/*")
        }

        val roomState = resources.getStringArray(R.array.room_spinner)

        if (roomStutus != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item, roomState
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            roomStutus.adapter = adapter
            roomStutus.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        activity,
                        getString(R.string.selected_item) + " " +
                                "" + positionitem, Toast.LENGTH_SHORT
                    ).show()

                }

                var positionitem: String = roomStutus.getSelectedItemPosition().toString()

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }

        val roomNamesRes = resources.getStringArray(R.array.room_name_spinner)

        if (roomNames != null) {

            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item, roomNamesRes
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            roomNames.adapter = adapter

            roomNames.onItemSelectedListener = object :

                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    if (position == 0) {
                        Toast.makeText(
                            activity,
                            getString(R.string.selected_item) + " " +
                                    "" + positionitem, Toast.LENGTH_SHORT
                        ).show()
                    } else {
                    }


                }

                var positionitem: String = roomNames.getSelectedItemPosition().toString()

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")

                }
            }
        }
    }


    fun createSpace(it: View?) {

        val roomName = roomNames.selectedItem.toString()
        val spinnerText = roomStutus.selectedItem.toString()
        val roomImage = mCurrentPhotoPath

        /*viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                context?.let {*/
        val data = Space(
            status = spinnerText,
            roomName = roomName,
            imgPath = roomImage, subData = ArrayList<ToDeclutter>()
        )

        viewModel.addSpace(data)


    }


    fun initViews() = binding.apply {
        if (spaceID != -1) {

            var roomName = roomNames.selectedItem.toString()
            //    val roomStatus= binding.idRoomStatus.toString()
            var spinnerText = roomStutus.selectedItem.toString()
//var roomImages=binding.showRoomImage.setImageURI(photoFile!!.toUri())
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                context?.let {

                    val data = Space(
                        status = spinnerText,
                        roomName = roomName,
                        imgPath = mCurrentPhotoPath, subData = ArrayList<ToDeclutter>()
                    )
                    // , subData = arrayListOf(String())
                    selectedImagePath = data.imgPath!!
                    showRoomImage.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath))
                    showRoomImage.visibility = View.VISIBLE
                }
            }
        }
    }

    private val getActionTakePicture =

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data
                binding.showRoomImage.setImageURI(mCurrentPhotoPath!!.toUri())

                // binding.showRoomImage.setImageURI(photoFile!!.toUri())
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
                    photoFile = try {
                        createImageFile()
                    } catch (ex: IOException) {

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

    @Throws(IOException::class)
    fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var image = File.createTempFile(
            imageFileName, //* prefix *//*
            ".jpg", //* suffix *//*
            storageDir      //* directory *//*
        )

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.absolutePath

        return image
    }

}

/*    registerForActivityResult(ActivityResultContracts.GetContent(),ActivityResultCallback{

}*/

