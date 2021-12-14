package com.example.declutteringapp

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestoreViewModel: FirestoreViewModel
    private lateinit var sharedPreferences: SharedPreferences
    var isRemembered = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        firestoreViewModel = ViewModelProvider(this)
            .get(FirestoreViewModel::class.java)

        sharedPreferences =
            this.requireActivity().getSharedPreferences("preference", Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false)

        if (isRemembered) {
            goToHome()
        }

        binding.lnkForgotPassword.setOnClickListener {
            forgotPasswordDialog()

        }

        binding.btnLogin.setOnClickListener {

            logInAuthentication()

        }
    }


    fun logInAuthentication() {

        var email = binding.etEmail.text.toString()
        var password = binding.etPwd.text.toString()

        when {
            TextUtils.isEmpty(binding.etEmail.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(context, "Please Enter Email", Toast.LENGTH_LONG).show()
            }

            TextUtils.isEmpty(binding.etPwd.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(context, "Please Enter Password", Toast.LENGTH_LONG).show()
            }

            else -> {
                var userInfo = UserInfoModel()
                firestoreViewModel.getUserInfo()

                when {
                    TextUtils.isEmpty(binding.etEmail.text.toString().trim { it <= ' ' }) -> {
                        val toastMessageEmail: String =
                            this@LogInFragment.getResources()
                                .getString(R.string.please_enter_email)

                        Toast.makeText(
                            context,
                            toastMessageEmail,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    TextUtils.isEmpty(binding.etPwd.text.toString().trim { it <= ' ' }) -> {
                        val toastMessagePassword: String =
                            this@LogInFragment.getResources()
                                .getString(R.string.please_enter_password)
                        Toast.makeText(context, toastMessagePassword, Toast.LENGTH_LONG).show()

                    }
                    else -> {
                        val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
                        val password: String = binding.etPwd.text.toString().trim { it <= ' ' }

                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->

                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    val toastMessageWelcome: String =
                                        this@LogInFragment.getResources()
                                            .getString(R.string.welcome)
                                    Toast.makeText(
                                        context,
                                        "$toastMessageWelcome ${email.toString()}",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                    val emailPreference: String = email
                                    val passwordPreference: String = password
                                    val checked: Boolean = binding.rememberMe.isChecked

                                    val editor: SharedPreferences.Editor =
                                        sharedPreferences.edit()
                                    editor.putString("EMAIL", emailPreference)
                                    editor.putString("PASSWORD", passwordPreference)
                                    editor.putBoolean("CHECKBOX", checked)
                                    editor.apply()

                                    val toastMessageInfoSaved: String =
                                        this@LogInFragment.getResources()
                                            .getString(R.string.info_saved)
                                    Toast.makeText(
                                        context,
                                        toastMessageInfoSaved,
                                        Toast.LENGTH_LONG
                                    ).show()
                                    firestoreViewModel.readUserData()
                                    goToHome()
                                } else {
                                    Toast.makeText(
                                        context,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    }
                }
            }

        }
    }

    fun goToHome() {
        findNavController().navigate(R.id.action_logInFragment_to_startFragment2)
    }

    fun forgotPasswordDialog() {

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Forgot Password")
        val view: View = layoutInflater.inflate(R.layout.forgot_password_layout, null)
        val userEmail: EditText = view.findViewById(R.id.etForgotEmailDialog)
        builder.setView(view)
        builder.setPositiveButton("Rest", { _, _ ->
            forgotPassword(userEmail)
        })
        builder.setNegativeButton("Close", { _, _ -> })
        builder.show()
    }

    private fun forgotPassword(userEmail: EditText) {
        auth= FirebaseAuth.getInstance()
        if (userEmail.text.toString().isEmpty()) {
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail.text.toString()).matches()) {
            return
        }
        auth.sendPasswordResetEmail(userEmail.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Send password Reset Email", Toast.LENGTH_SHORT).show()
                }
            }


    }
}
