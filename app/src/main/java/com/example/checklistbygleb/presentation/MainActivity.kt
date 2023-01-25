package com.example.checklistbygleb.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistbygleb.R
import com.example.checklistbygleb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var checkAdapter: CheckListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        viewModel.checkList.observe(this) {
            checkAdapter.submitList(it)
        }
        startInitHorizontal(savedInstanceState)
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
        setupAddBtn()
    }

    private fun startInitHorizontal(savedState: Bundle?) {
        if (savedState == null && !isVerticalOrientated()) launchFragment(CheckItemFragment.newInstanceAddItem())
    }

    private fun setupLongClickListener() {
        checkAdapter.onCheckItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    private fun setupClickListener() {
        checkAdapter.onCheckItemClickListener = {
            if (isVerticalOrientated()) {
                val intent = CheckItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(CheckItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setupAddBtn() {
        binding.btnAddCheckItem.setOnClickListener {
            if (isVerticalOrientated()) {
                val intent = CheckItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(CheckItemFragment.newInstanceAddItem())
            }
        }
    }

    private fun isVerticalOrientated(): Boolean {
        return binding.checkItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.check_item_container, fragment)
            .addToBackStack(null)
            .commit()
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
                val item = checkAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteCheckItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}