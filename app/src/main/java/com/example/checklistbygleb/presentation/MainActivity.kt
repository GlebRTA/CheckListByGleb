package com.example.checklistbygleb.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.checklistbygleb.databinding.ActivityMainBinding

private lateinit var viewModel: MainViewModel
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.checkList.observe(this) {
            Log.d("MyLog", it.toString())
            if (count == 0) {
                count++
                viewModel.changeEnableState(it[0])
            }
        }
    }
}