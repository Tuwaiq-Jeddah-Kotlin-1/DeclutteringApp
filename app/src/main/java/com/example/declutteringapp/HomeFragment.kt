package com.example.declutteringapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.declutteringapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser



class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
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
            goToStart()
        }

binding.lnkLogin.setOnClickListener{
    goToStart()


}


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

                            if (task.isSuccessful) {

                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                firestoreViewModel.insertUser(
                                    "${email}",
                                    "${name.toString()}",
                                            "${password.toString()}"
                                )

                                Toast.makeText(context, "You were registered succsessfuly", Toast.LENGTH_LONG)
                                    .show()

                                val emailPreference: String = email
                                val passwordPreference: String = password
                                val checked: Boolean = binding.rememberMeUp.isChecked

                                val editor: SharedPreferences.Editor =
                                    sharedPreferences.edit()
                                editor.putString("EMAIL", emailPreference)
                                editor.putString("PASSWORD", passwordPreference)
                                editor.putBoolean("CHECKBOX", checked)
                                editor.apply()


                                goToStart()

                            } else {
                                Toast.makeText(context, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()

                            }
                        }


                }}}}
    fun goToStart(){
        findNavController().navigate(R.id.action_homeFragment2_to_logInFragment)

    }
}





