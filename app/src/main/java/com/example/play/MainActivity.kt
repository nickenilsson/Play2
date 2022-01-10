package com.example.play

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.play.databinding.ActivityMainBinding

interface MainActivityViewModel {
    val title: String
    val subtitle: String
}

class MockedMainActivityViewModel: MainActivityViewModel {
    override val title: String = "TestTitle"
    override val subtitle: String = "TestSubtitle"
}

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textViewTitle.setText(viewModel.title)

        binding.textViewSubtitle.setText(viewModel.subtitle)


    }
}