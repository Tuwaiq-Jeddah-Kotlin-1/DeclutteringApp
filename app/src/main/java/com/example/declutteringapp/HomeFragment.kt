package com.example.declutteringapp

import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.databinding.FragmentHomeBinding
import com.example.declutteringapp.databinding.FragmentViewPagerBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class HomeFragment : Fragment() {


    private lateinit var _binding: FragmentHomeBinding

    private val binding get() = _binding

    private lateinit var auth: FirebaseAuth

    private lateinit var firestoreViewModel: FirestoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var firestoreViewModel = ViewModelProvider(this)
            .get(FirestoreViewModel::class.java)




        binding.btnSignUp.setOnClickListener {
            var email = binding.txtEmail.text.toString()
            var name = binding.txtName.text.toString()
            var password = binding.txtPwd.text.toString()


            when {

                TextUtils.isEmpty(binding.txtEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(context, "Please Enter Email", Toast.LENGTH_LONG).show()
                }

                TextUtils.isEmpty(binding.txtName.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(context, "Please Enter Full Name", Toast.LENGTH_LONG).show()


                }
                TextUtils.isEmpty(binding.txtPwd.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(context, "Please Enter Password", Toast.LENGTH_LONG).show()


                }
                else -> {
                    var userInfo = UserInfoModel()
                    firestoreViewModel.saveUserToFirebase(userInfo)

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            // if the registration is sucessfully done
                            if (task.isSuccessful) {
                                //firebase register user
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                firestoreViewModel.insertUser(
                                    "${email}",
                                    "${name.toString()}",
                                            "${password.toString()}"
                                )


                            }}}}}
                            //----------------------------------------------------
                        }

                }




