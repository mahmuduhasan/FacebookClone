package com.example.facebookclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.facebookclone.databinding.FragmentLoginBinding
import com.example.facebookclone.models.UserModel
import com.example.facebookclone.prefdata.LoginPreference
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val modelView : UserModel by activityViewModels()
    private lateinit var loginPreference: LoginPreference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginPreference = LoginPreference(requireContext())
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        modelView.errorMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }
        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            modelView.login(email,password){
                lifecycle.coroutineScope.launch {
                    loginPreference.setLoginStatus(true,it,requireContext())
                    findNavController().popBackStack()
                }
            }
        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        return binding.root
    }

}