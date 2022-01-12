package com.example.play

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.play.databinding.ActivityMainHostBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainHostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

}