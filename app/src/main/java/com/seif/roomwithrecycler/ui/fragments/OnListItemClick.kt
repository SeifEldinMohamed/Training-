package com.seif.roomwithrecycler.ui.fragments

import com.seif.roomwithrecycler.model.entity.Message

interface OnListItemClick {
    fun onListItemClick(message: Message)
}