package com.seif.roomwithrecycler.ui.fragments

import com.seif.roomwithrecycler.model.entity.User

interface OnListItemClick {
    fun onListItemClick(user: User)
}