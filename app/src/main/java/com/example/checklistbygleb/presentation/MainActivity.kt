package com.example.checklistbygleb.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistbygleb.databinding.ActivityMainBinding

private lateinit var viewModel: MainViewModel
private lateinit var binding: ActivityMainBinding
private lateinit var checkAdapter: CheckListAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.checkList.observe(this) {
            checkAdapter.checkList = it
        }

    }

    private fun initAdapter() {
        checkAdapter = CheckListAdapter()
        with(binding.rvCheckList) {
            adapter = checkAdapter
            recycledViewPool.setMaxRecycledViews(
                CheckListAdapter.VIEW_TYPE_ENABLED,
                CheckListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                CheckListAdapter.VIEW_TYPE_DISABLED,
                CheckListAdapter.MAX_POOL_SIZE
            )

        }
        setupClickListener()
        setupLongClickListener()
        setupSwipedListener(binding.rvCheckList)
    }

    private fun setupLongClickListener() {
        checkAdapter.onCheckItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    private fun setupClickListener() {
        checkAdapter.onCheckItemClickListener = {
            Log.d("MyLog", "Info = $it")
        }
    }

    private fun setupSwipedListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = checkAdapter.checkList[viewHolder.adapterPosition]
                viewModel.deleteCheckItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}