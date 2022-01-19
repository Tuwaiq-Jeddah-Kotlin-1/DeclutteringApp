package com.example.declutteringapp.view

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.FragmentShowRoomBinding
import com.example.declutteringapp.model.ToDeclutter
import com.example.declutteringapp.view.adapters.ToDeclutterAdapter
import com.example.declutteringapp.viewmodel.SpaceViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class ShowRoomFragment : Fragment(), ToDeclutterAdapter.ClickDeleteInterface {

    val spaces by navArgs<ShowRoomFragmentArgs>()
    val items by navArgs<ShowRoomFragmentArgs>()

    private lateinit var _binding: FragmentShowRoomBinding
    private val binding get() = _binding!!
    lateinit var viewModelD: SpaceViewModel
    lateinit var ImageCamera: ImageButton
    lateinit var ImageGallary: ImageButton
    lateinit var toDeclutterRV: RecyclerView
    lateinit var addFAB: ImageButton
    private var photoFile: File? = null
    private var itemId=0
    private var mCurrentPhotoPath: String? = null
    private var selectedImagePath = ""
    private lateinit var sharedPreferences: SharedPreferences

    //val sharedViewModel by activityViewModels<ItemsSharedViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowRoomBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ImageCamera = binding.imgCameraSpace
        ImageGallary = binding.imgUploadSpace
        binding.ShowRoomName.setText(spaces.space.roomName)
        binding.ShowRoomStatus.setText(spaces.space.status)
        Glide.with(this)
            .load(spaces.space.imgPath)
            .into(binding.showRoomImage)



        sharedPreferences =
            this.requireActivity().getSharedPreferences("preference", Context.MODE_PRIVATE)

        toDeclutterRV = binding.toDeclutterRV
        toDeclutterRV.layoutManager = GridLayoutManager(context, 1)

        val spaceAdapter = ToDeclutterAdapter(requireContext(),this)

        toDeclutterRV.adapter = spaceAdapter
        addFAB=binding.idFAB


        viewModelD = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(SpaceViewModel::class.java)

        initViews()


        viewModelD.allItems(spaces.space.roomId).observe(viewLifecycleOwner,  { list ->
            list?.let {

                spaceAdapter.updateList(it)
            }
        })


        addFAB.setOnClickListener {
            itemDialog()
        }

        ImageCamera.setOnClickListener {
            captureImage()

        }

        val pickImages = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { binding.showRoomImage.setImageURI(uri) }
        }




        ImageGallary.setOnClickListener {

            pickImages.launch("image/*")


        }

    }
    fun itemDialog() {
        val view: View = layoutInflater.inflate(R.layout.edittext_with_button, null)

        val builder = BottomSheetDialog(requireView()?.context)
        builder.setTitle("Add Item")

        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)


        btnClose.setOnClickListener {

            var itemsDeclutterr = view.findViewById<EditText>(R.id.etAddItem)

            var  itemsD=   itemsDeclutterr.text.toString()

            var itemSpace= ToDeclutter(itemsD,spaces.space.roomId)

                viewModelD.addItem(itemSpace)

            Toast.makeText(requireContext(), "You Added an Item!", Toast.LENGTH_SHORT).show()

            builder.dismiss()

        }


        builder.setCancelable(true)

        builder.setContentView(view)

            builder.show()
        }





    fun initViews() = binding.apply {
       if (itemId != -1) {
           viewLifecycleOwner.lifecycleScope.launchWhenCreated {

            val itemsDeclutterr = view?.findViewById<EditText>(R.id.etAddItem)

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                context?.let {
                    var  itemsD=   itemsDeclutterr?.text.toString()


                    val data = ToDeclutter(
                            items = itemsD,spaces.space.roomId
                        )

                    viewModelD.addItem(data)


                    binding.showRoomImage.visibility = View.VISIBLE

          }}}}}
    private  val getActionTakePicture =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                binding.showRoomImage.setImageURI(photoFile!!.toUri())


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

    override fun onDeleteIconClick(item: ToDeclutter) {

        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to Delete Item?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                viewModelD.deleteItem(item)
                Toast.makeText(this.activity, "item Deleted", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()



    }
}







//  var itemSpace=ToDeclutter(data)

//  selectedImagePath = data.roomImages!!
//   binding.showRoomImage.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath))





/*

               var images=ImageR.setImageBitmap(BitmapFactory.decodeStream(ContentResolver().openInputStream())
                       ImageR!!.setImageBitmap(Bitmap)

*/










