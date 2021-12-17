package com.seif.roomwithrecycler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seif.roomwithrecycler.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)



    }
}