package com.example.declutteringapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.databinding.FragmentLogInBinding
import com.example.declutteringapp.databinding.FragmentProfileBinding
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var preferences: SharedPreferences


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

        val userEmail: TextView = view.findViewById(R.id.tvProfileEmail)

        preferences =
            this.requireActivity().getSharedPreferences("preference", Context.MODE_PRIVATE)
        val emailPref = preferences.getString("EMAIL", "")
        userEmail.text = emailPref
        val passwordPref = preferences.getString("PASSWORD", "")


        binding.btnSignOut.setOnClickListener{

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


                }


        }





