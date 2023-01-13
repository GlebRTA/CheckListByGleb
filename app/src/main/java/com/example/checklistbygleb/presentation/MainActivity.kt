package com.example.checklistbygleb.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.checklistbygleb.databinding.ActivityMainBinding

private lateinit var viewModel: MainViewModel
private lateinit var binding: ActivityMainBinding
private lateinit var adapter: CheckListAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.checkList.observe(this) {
            adapter.checkList = it
        }

    }

    private fun initAdapter() {
        adapter = CheckListAdapter()
        with(binding.rvCheckList) {
            adapter = adapter
            recycledViewPool.setMaxRecycledViews(
                CheckListAdapter.VIEW_TYPE_ENABLED,
                CheckListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                CheckListAdapter.VIEW_TYPE_DISABLED,
                CheckListAdapter.MAX_POOL_SIZE
            )
        }
    }
}