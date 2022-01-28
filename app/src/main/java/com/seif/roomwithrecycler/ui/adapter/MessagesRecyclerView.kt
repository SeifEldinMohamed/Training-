package com.seif.roomwithrecycler.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seif.roomwithrecycler.R
import com.seif.roomwithrecycler.model.entity.User
import com.seif.roomwithrecycler.ui.fragments.OnListItemClick

class MessagesRecyclerView(var userlist: List<User>) : RecyclerView.Adapter<MessagesRecyclerView.MyViewHolder>() {
    var onListItemClick : OnListItemClick? = null
  

   inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txt_userName: TextView = itemView.findViewById(R.id.txt_Name)
        private val txt_message: TextView = itemView.findViewById(R.id.txt_Message)

        fun bind(user: User) {
            txt_message.text = user.message
            txt_userName.text = user.userName
            itemView.setOnClickListener {
                onListItemClick?.onListItemClick(user)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_list, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userlist[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

}