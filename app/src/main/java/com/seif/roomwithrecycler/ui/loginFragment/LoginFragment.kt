package com.seif.roomwithrecycler.ui.loginFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.seif.roomwithrecycler.R
import com.seif.roomwithrecycler.databinding.FragmentLoginBinding
import com.seif.roomwithrecycler.model.entity.User
import com.seif.roomwithrecycler.ui.mainFragment.MainFragmentDirections
import com.seif.roomwithrecycler.util.CommonFunctions.Companion.showSnackBar
import com.seif.roomwithrecycler.viewModels.UserViewModel

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = UserViewModel(requireContext())

        binding.btnSignIn.setOnClickListener {
            if (checkUserInput()) {
                val userEmail = binding.editEmail.text.toString()
                val userPassword = binding.editPassword.text.toString()
                Log.d("login", userPassword)
                validateUserLogin(userEmail, userPassword)
            } else {
                showSnackBar(binding.root, "please enter all fields!")
            }
        }
    }

    private fun validateUserLogin(userEmail: String, userPassword: String) {
        userViewModel.getUserByEmailAndPassword(userEmail, userPassword)
        userViewModel.userFoundLiveData.observe(viewLifecycleOwner) {
            Log.d("login", it.toString())
            if (it == null) {
                showSnackBar(binding.root, "Invalid Email Address or Password")
            } else {
                passUser(it)
            }
        }
    }

    private fun passUser(user: User) {
        showSnackBar(binding.root, "Welcome")
        val action =
            LoginFragmentDirections.actionLoginFragmentToSecondFragment(
                user.userName,
                user.email
            )
        Log.d("login", user.toString())
        findNavController().navigate(action)
    }

    private fun checkUserInput(): Boolean {
        return binding.editEmail.text.isNotEmpty() && binding.editPassword.text.isNotEmpty()
    }
}