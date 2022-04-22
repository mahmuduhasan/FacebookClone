package com.example.facebookclone

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.facebookclone.databinding.FragmentRegisterBinding
import com.example.facebookclone.dialogs.DatePicker
import com.example.facebookclone.entities.User
import com.example.facebookclone.models.UserModel
import com.example.facebookclone.prefdata.LoginPreference
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RegisterFragment : Fragment() {
    private lateinit var binding : FragmentRegisterBinding
    private var gender : String = "Male"
    private val modelView : UserModel by activityViewModels()
    private lateinit var loginPreference: LoginPreference
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginPreference = LoginPreference(requireContext())
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        genderSelectSpinner()

        binding.selectBirthDate.setOnClickListener {
            DatePicker{
                binding.selectBirthDate.text = it
            }.show(childFragmentManager, null)
        }

        binding.registerButton.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.LastName.text.toString()
            val email = binding.emailRegisterInput.text.toString()
            val birthday = binding.selectBirthDate.text.toString()
            val password = binding.createPassword.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()
            if (password != confirmPassword){
                Toast.makeText(requireActivity(), "Password didn't match.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val pickCurrentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")
            val userDateTime = pickCurrentDateTime.format(formatter)
            val newUser = User(
                firstName = firstName,
                lastName = lastName,
                email = email,
                gender = gender,
                birthday = birthday,
                password = password,
                createdDateTime = userDateTime
            )
            modelView.register(newUser){
                lifecycle.coroutineScope.launch {
                    loginPreference.setLoginStatus(true, it, requireContext())
                    findNavController().popBackStack()
                }
            }
        }

        return binding.root
    }

    private fun genderSelectSpinner() {
        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            genderList
        )

        binding.genderSpinner.adapter = adapter

        binding.genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                gender = p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}

val genderList = listOf("Male","Female","Others")