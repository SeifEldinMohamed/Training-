package com.seif.roomwithrecycler.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.seif.roomwithrecycler.databinding.FragmentSecondBinding
import com.seif.roomwithrecycler.model.entity.User
import com.seif.roomwithrecycler.ui.adapter.MessagesRecyclerView
import com.seif.roomwithrecycler.ui.viewModels.UserViewModel
import com.seif.roomwithrecycler.util.CommonFunctions.Companion.showSnackBar
import kotlinx.coroutines.*


class SecondFragment : Fragment(), OnListItemClick {
    lateinit var binding: FragmentSecondBinding
    lateinit var messagesRecyclerView: MessagesRecyclerView
    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = arguments?.getString("userName")
        val email = arguments?.getString("userEmail")

        userViewModel = UserViewModel(view.context)
        // userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        getAllUsers()
        binding.btnAdd.setOnClickListener {
            val msg: String = binding.editMessage.text.toString()
            userViewModel.addUser(
                User(
                    0,
                    userName.toString(),
                    msg,
                    email.toString()
                )
            )

            getAllUsers()
            binding.editMessage.setText("")

        }

        userViewModel.userLiveData.observe(viewLifecycleOwner,
            Observer {
                if (it != null) {
                    binding.txtNotasks.visibility = View.GONE
                    messagesRecyclerView = MessagesRecyclerView(it)
                    messagesRecyclerView.onListItemClick = this@SecondFragment
                    binding.progressBar.visibility = View.GONE
                    binding.rvShoeMessages.adapter = messagesRecyclerView
                }
                if (it.isEmpty()) {
                    binding.txtNotasks.visibility = View.VISIBLE
                }
            })
    }

    private fun getAllUsers() {
        userViewModel.getAllUsers()
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onListItemClick(user: User) {
        userViewModel.deleteUser(user)
        showSnackBar(binding.root,"userName: ${user.userName}\n userMessage: ${user.message} deleted successfully")
        getAllUsers()
    }

}