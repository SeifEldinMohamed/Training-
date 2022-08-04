package com.seif.roomwithrecycler.ui.messagesFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seif.roomwithrecycler.databinding.FragmentMessagesBinding
import com.seif.roomwithrecycler.model.entity.Message
import com.seif.roomwithrecycler.ui.adapter.MessagesRecyclerView
import com.seif.roomwithrecycler.ui.fragments.OnListItemClick
import com.seif.roomwithrecycler.viewModels.MessageViewModel
import com.seif.roomwithrecycler.util.CommonFunctions.Companion.showSnackBar


class MessagesFragment : Fragment(), OnListItemClick {
    lateinit var binding: FragmentMessagesBinding
    lateinit var messagesRecyclerView: MessagesRecyclerView
    lateinit var messageViewModel: MessageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMessagesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = arguments?.getString("userName")
        val email = arguments?.getString("userEmail")

        messageViewModel = MessageViewModel(view.context)
        // userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        getAllUsers()
        binding.btnAdd.setOnClickListener {
            val msg: String = binding.editMessage.text.toString()
            messageViewModel.addUser(
                Message(
                    0,
                    userName.toString(),
                    msg,
                    email.toString()
                )
            )

            getAllUsers()
            binding.editMessage.setText("")

        }

        messageViewModel.messageLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.txtNotasks.visibility = View.GONE
                messagesRecyclerView = MessagesRecyclerView(it)
                messagesRecyclerView.onListItemClick = this@MessagesFragment
                binding.progressBar.visibility = View.GONE
                binding.rvShoeMessages.adapter = messagesRecyclerView
            }
            if (it.isEmpty()) {
                binding.txtNotasks.visibility = View.VISIBLE
            }
        }
    }

    private fun getAllUsers() {
        messageViewModel.getAllUsers()
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onListItemClick(message: Message) {
        messageViewModel.deleteUser(message)
        showSnackBar(binding.root,"userName: ${message.userName}\n userMessage: ${message.message} deleted successfully")
        getAllUsers()
    }

}