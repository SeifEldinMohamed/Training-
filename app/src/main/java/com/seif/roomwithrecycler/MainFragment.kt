package com.seif.roomwithrecycler

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.seif.roomwithrecycler.databinding.FragmentMainBinding
import com.seif.roomwithrecycler.ui.SecondActivity


class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            if(binding.editEmail.text.isNotEmpty() && binding.editPassword.text.isNotEmpty()
                && binding.editUserName.text.isNotEmpty()){
                val userName = binding.editUserName.text.toString()
                val userEmail = binding.editEmail.text.toString()

                val action = MainFragmentDirections.actionMainFragmentToSecondFragment(userName,userEmail)
                findNavController().navigate(action)
            }
            else{
                Toast.makeText(context, "Please enter all information", Toast.LENGTH_SHORT).show()
            }
        }

    }

}