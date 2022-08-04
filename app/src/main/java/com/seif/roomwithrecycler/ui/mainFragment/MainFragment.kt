package com.seif.roomwithrecycler.ui.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.seif.roomwithrecycler.R
import com.seif.roomwithrecycler.databinding.FragmentMainBinding
import com.seif.roomwithrecycler.model.entity.User
import com.seif.roomwithrecycler.util.CommonFunctions.Companion.showSnackBar
import com.seif.roomwithrecycler.viewModels.UserViewModel


class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = UserViewModel(requireContext())

        binding.btnSignIn.setOnClickListener {
            if (checkUserInput()) {
                userViewModel.addUser(createNewUser())

                showSnackBar(binding.root, "created successfully!")

                findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
            }
            else {
                Toast.makeText(context, "Please enter all information", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }
    }

    private fun checkUserInput(): Boolean {
        return binding.editEmail.text.isNotEmpty() && binding.editPassword.text.isNotEmpty()
                && binding.editUserName.text.isNotEmpty()
    }

    private fun createNewUser(): User {
        val userName = binding.editUserName.text.toString()
        val userEmail = binding.editEmail.text.toString()
        val userPassword = binding.editPassword.text.toString()

        return User(0, userName, userEmail, userPassword)
    }
}