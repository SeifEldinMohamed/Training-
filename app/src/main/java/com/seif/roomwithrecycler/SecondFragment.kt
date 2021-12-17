 package com.seif.roomwithrecycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seif.roomwithrecycler.databinding.FragmentSecondBinding
import com.seif.roomwithrecycler.model.entity.User
import com.seif.roomwithrecycler.model.local.LocalRepositoryImplementation
import com.seif.roomwithrecycler.model.local.UserDatabase
import com.seif.roomwithrecycler.ui.OnListItemClick
import com.seif.roomwithrecycler.ui.adapter.MessagesRecyclerView
import kotlinx.coroutines.*


class SecondFragment : Fragment(),OnListItemClick {
    lateinit var binding: FragmentSecondBinding
    var userList: List<User> = ArrayList()
    lateinit var messagesRecyclerView: MessagesRecyclerView

    lateinit var localRepositoryImplementation: LocalRepositoryImplementation

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

        Toast.makeText(context, "Welcome $userName", Toast.LENGTH_SHORT).show()
        // database
        val db = UserDatabase.getInstance(view.context)
        localRepositoryImplementation = LocalRepositoryImplementation(db)

        getAllUsers()
        binding.btnAdd.setOnClickListener {
            val msg:String = binding.editMessage.text.toString()
            // we have to use coroutine to access the database .
           GlobalScope.launch (Dispatchers.IO){ // launch -> used when I don't need function to return thing
               localRepositoryImplementation.addUser(
                   User(
                   0,
                   userName.toString(),
                   msg,
                   email.toString()
               )
               )
           }

            // async and wait part + progress bar to notify user
            getAllUsers()
            //messagesRecyclerView.notifyDataSetChanged()
            binding.editMessage.setText("")
        }
        //messagesRecyclerView = MessagesRecyclerView(userList)

    }
   private fun getAllUsers(){
       GlobalScope.launch(Dispatchers.IO) {
          val returnedUsers = async {
              localRepositoryImplementation.getData()
          }
           withContext(Dispatchers.Main){
               binding.progressBar.visibility = View.VISIBLE
               userList = returnedUsers.await() // await is waiting the getData fun to return all data then assign it to the userList
               binding.progressBar.visibility = View.GONE
               messagesRecyclerView = MessagesRecyclerView(userList)
               messagesRecyclerView.onListItemClick = this@SecondFragment
               binding.rvShoeMessages.adapter = messagesRecyclerView
           }
       }
   }

    override fun onListItemClick(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            localRepositoryImplementation.deleteUser(user)
        }
        Toast.makeText(context,
            "userName: ${user.userName}\n userMessage: ${user.message} deleted successfully",
            Toast.LENGTH_LONG).show()
        getAllUsers()
    }

}