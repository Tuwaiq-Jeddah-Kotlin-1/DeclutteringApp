package com.example.declutteringapp.view

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.declutteringapp.databinding.FragmentEditSpaceBinding
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.R
import com.example.declutteringapp.model.Space
import com.example.declutteringapp.model.ToDeclutter
import com.example.declutteringapp.viewmodel.SpaceViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class EditSpaceFragment : Fragment() {


    lateinit var editeRoomName: EditText
    lateinit var roomStutus: Spinner
    lateinit var roomNames: Spinner
    lateinit var saveBtn: Button
    lateinit var roomImage:ImageView
    lateinit var viewModel: SpaceViewModel
    lateinit var ImageCamera: ImageButton
    lateinit var ImageGallary: ImageButton
    private var photoFile: File? = null
    private var itemId=0
    private var copiedImagePath:String?=null
    private var mCurrentPhotoPath: String? = null
    private var selectedImagePath = ""
    lateinit var  adapterSpace:SpaceRvAdapter
    private lateinit var _binding: FragmentEditSpaceBinding
    private val binding get() = _binding!!
    var spaceID = -1
    val sharedPref =activity?.getPreferences(Context.MODE_PRIVATE)

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

        var score = sharedPref?.getInt("Score", 0)


        /*   var myDrawable = resources.getIdentifier(
            "imagename", "drawable", "com.example.decluttering"
        )*/

        initViews()

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(SpaceViewModel::class.java)

        ImageCamera.setOnClickListener {
            captureImage()

        }

        val pickImages = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { binding.showRoomImage.setImageURI(uri) }
        }




        ImageGallary.setOnClickListener {

            pickImages.launch("image/*")


        }
        saveBtn.setOnClickListener {

            createSpace(it)

            findNavController().navigate(R.id.action_editSpaceFragment_to_mySpaceFragment22)

            Toast.makeText(requireContext(), "You Added a Room!", Toast.LENGTH_SHORT).show()

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
            }}

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
                ) { if(position==0){
                    //   im=R.drawable.ic_bag
                }else{}

                    Toast.makeText(
                        requireContext(),
                        getString(R.string.selected_item) + " " +
                                "" + positionitem, Toast.LENGTH_SHORT
                    ).show()
                }

                var positionitem: String = roomNames.getSelectedItemPosition().toString()

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")

                }
            }}
    }


fun createSpace(it: View?) {

    var roomName = roomNames.selectedItem.toString()
    var spinnerText = roomStutus.selectedItem.toString()




            val data = com.example.declutteringapp.model.Space(
                status = spinnerText,
                roomName = roomName,
                imgPath = mCurrentPhotoPath)

            viewModel.addSpace(data)


   }


    private  val getActionTakePicture =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                binding.showRoomImage.setImageURI(photoFile!!.toUri())
            }}

     fun initViews() = binding.apply {
        if (spaceID != -1) {

            var roomName= roomNames.selectedItem.toString()
            //    val roomStatus= binding.idRoomStatus.toString()
            var spinnerText=roomStutus.selectedItem.toString()
//var roomImages=binding.showRoomImage.setImageURI(photoFile!!.toUri())
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                context?.let {

                    val data = Space(
                        status = spinnerText,
                        roomName = roomName,
                        imgPath = mCurrentPhotoPath
                    )
                    viewModel.addSpace(data)
                }
                    }}}





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
                }}}}

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



/*   val copiedImage = File(storageDir, UUID.randomUUID().toString() + ".jpg")
      val copiedImageUri = Uri.fromFile(photoFile.toURI())

      image.path?.let {
          File(it).copyTo(copiedImage)
      }
      copiedImageUri.path.let{
          val copiedImagePath = "file://$it"
          //now save this path to your database
      }*/


//getData(data)

/*   fun getData(data:SpaceItem){
       data.space.status=spinnerText
       data.space.roomName=roomName
       data.space.imgPath = R.drawable.ic_house_ouline_two
       viewModel.addSpace(data)
   }*/

//  var sSpace=SpaceItem()
// getData(SpaceItem(space))



                  /*  var space = Space(spin)
                    spinnerText=space.status
                    roomName=space.roomName
                   roomImages=space.imgPath*/


                      /*  selectedImagePath = space.imgPath!!
              binding.ivSpaceImage.setImageBitmap(BitmapFactory.decodeFile(space.imgPath!!.toString()))
                   binding.ivSpaceImage.visibility = View.VISIBLE
*/
/* if (roomNames.selectedItem==0){
                       fun getData(data:SpaceItem){
                           data.space.status=spinnerText
                           data.space.roomName=roomName
                           data.space.imgPath = R.drawable.ic_house_ouline_two*/
//     viewModel.addSpace(data)
// adapterSpace.submitList()








/*


    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) {bitmap ->
      bitmap?.let {
        bi.imgCameraPic.setImageBitmap(it)
        bi.imgCameraPic.visibility = View.VISIBLE
      }
    }

   private val askLocationPermission =
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
        }


private val takePicture =
    registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let { binding.ivSpaceImage.setImageBitmap(bitmap) }


*/