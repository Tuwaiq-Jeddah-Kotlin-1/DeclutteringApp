
package com.example.declutteringapp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.declutteringapp.R.layout.support_simple_spinner_dropdown_item

import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.FragmentLogInBinding
import com.example.declutteringapp.databinding.FragmentProfileBinding
import com.example.declutteringapp.view.MainActivity
import com.example.declutteringapp.viewmodel.FirestoreViewModel
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class ProfileFragment : Fragment() {

    private lateinit var preferences: SharedPreferences
    private lateinit var tvName: TextView

    private lateinit var binding:FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        preferences =
            this.requireActivity().getSharedPreferences("preference", Context.MODE_PRIVATE)


        binding.btnSignOut.setOnClickListener {

            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()
            findNavController().navigate(R.id.action_profileFragment2_to_logInFragment)

            val toastMessageSignOut: String =
                this@ProfileFragment.getResources()
                    .getString(R.string.signout)
            Toast.makeText(
                context,
                "$toastMessageSignOut",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.langChange.setOnClickListener{

       changeLangeDialog()

        }

        binding.modeToggle.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            }else{

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } }


        displayName()

        tvName = binding.tvName


        tvName.setText("${tvName.text}")



    }


    fun changeLangeDialog() {

        val builder = AlertDialog.Builder(context)
        val view: View = layoutInflater.inflate(R.layout.change_lang_dialog, null)

        var spinner: Spinner =view.findViewById(R.id.spinner)
        val list = ArrayList<String>()
        list.add("Select Language")
        list.add("English")
        list.add("Arabic")
        val adapter = ArrayAdapter(requireContext(), support_simple_spinner_dropdown_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                    }
                    1 -> setLocale("en")
                    2 -> setLocale("ar")
                }
            }


            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        builder.setView(view)
        builder.show()    }


    private fun setLocale(localeName: String) {
        lateinit var locale: Locale
        var currentLanguage = "en"
        var currentLang: String? = null
        var bundle = Bundle()
        currentLanguage = bundle.getString(currentLang).toString()
        if (localeName != currentLanguage) {
            locale = Locale(localeName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            res.updateConfiguration(conf, dm)
            val refresh = Intent(
                context,
                MainActivity::class.java
            )
            refresh.putExtra(currentLang, localeName)
            startActivity(refresh)
        } else {
            Toast.makeText(
                this.context, "Language, , already, , selected)!", Toast.LENGTH_SHORT
            ).show();
        }
    }


    private  fun displayName() = CoroutineScope(Dispatchers.IO).launch {

        val uId = FirebaseAuth.getInstance().currentUser!!.uid
        try {
            val db = FirebaseFirestore.getInstance()
            db.collection("users")
                .document("$uId")
                .get().addOnCompleteListener {
                    it

                    if (it.getResult()?.exists()!!) {
                        var name = it.getResult()!!.getString("name")
                        tvName.setText(name)

                        Log.e("error \n", "name $name")
                    } else {
                        Log.e("error \n", "erorr")
                    }
                }

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Log.e("FUNCTION createUserFirestore", "${e.message}")
            }
        }


    }


}
