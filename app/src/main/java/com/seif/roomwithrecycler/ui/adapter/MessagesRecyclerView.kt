package com.seif.roomwithrecycler.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seif.roomwithrecycler.R
import com.seif.roomwithrecycler.model.entity.Message
import com.seif.roomwithrecycler.ui.fragments.OnListItemClick

class MessagesRecyclerView(var userlist: List<Message>) : RecyclerView.Adapter<MessagesRecyclerView.MyViewHolder>() {
    var onListItemClick : OnListItemClick? = null
  

   inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtUserName: TextView = itemView.findViewById(R.id.txt_Name)
        private val txtMessage: TextView = itemView.findViewById(R.id.txt_Message)

        fun bind(message: Message) {
            txtMessage.text = message.message
            txtUserName.text = message.userName
            itemView.setOnClickListener {
                onListItemClick?.onListItemClick(message)
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