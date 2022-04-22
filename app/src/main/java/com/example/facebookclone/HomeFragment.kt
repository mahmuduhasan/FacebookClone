package com.example.facebookclone

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.facebookclone.adapters.PostAdapter
import com.example.facebookclone.databinding.FragmentHomeBinding
import com.example.facebookclone.entities.Post
import com.example.facebookclone.models.PostModel
import com.example.facebookclone.prefdata.LoginPreference
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val modelView : PostModel by activityViewModels()
    private lateinit var loginPreference: LoginPreference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginPreference = LoginPreference(requireContext())
        lifecycle.coroutineScope.launch {
            loginPreference.isLoggedInFlow.collect(){
                if(!it){
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                }
            }
        }
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val adapter = PostAdapter()
        binding.postRecycleView.layoutManager = LinearLayoutManager(requireActivity())
        binding.postRecycleView.adapter = adapter
        modelView.getAllPost().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.postButton.setOnClickListener {
            val post = binding.postInput.text.toString()
            val pickCurrentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")
            val postDateTime = pickCurrentDateTime.format(formatter)
            val newPost = Post(
                post = post,
                createdDateTime = postDateTime
            )
            modelView.insertPost(newPost)
        }
        return binding.root
    }

}