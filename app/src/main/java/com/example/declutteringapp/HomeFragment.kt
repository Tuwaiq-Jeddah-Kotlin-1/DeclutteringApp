package com.example.declutteringapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.declutteringapp.databinding.FragmentHomeBinding
import com.example.declutteringapp.databinding.FragmentViewPagerBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


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

        firestoreViewModel = FirestoreViewModel()

        firestoreViewModel.getUserInfo().observe(requireActivity(), Observer { it ->
            savedUser = it

        })
        auth = Firebase.auth

        binding.btnSignUp.setOnClickListener {

            firestoreViewModel.saveUserToFirebase(UserInfoModel("","","",""))

        }
    }
        private fun registerUser () {
            val emailTxt = binding.txtEmail
            val passwordTxt = binding.txtPwd
            val nameTxt = binding.txtName

            var email = emailTxt.text.toString()
            var password = passwordTxt.text.toString()
            var name = nameTxt.text.toString()

            if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
             firestoreViewModel.saveUserToFirebase(user)

                        Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_LONG).show()
                    }else {
                        Toast.makeText(this, "Error registering, try again later :(", Toast.LENGTH_LONG).show()
                    }
                })
            }else {
                Toast.makeText(this,"Please fill up the Credentials :|", Toast.LENGTH_LONG).show()
            }
        }
        private fun sendVerificationEmail(user: FirebaseUser?) {
            user?.sendEmailVerification()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(FirebaseUtils.TAG, "Email sent.")
                }else{
                    Toast.makeText(
                        this, "Couldn't Send Verification Email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }



    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }


    }
