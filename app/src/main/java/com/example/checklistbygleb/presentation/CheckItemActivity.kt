package com.example.checklistbygleb.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.checklistbygleb.R
import com.example.checklistbygleb.databinding.ActivityCheckItemBinding
import com.example.checklistbygleb.domain.CheckItem


class CheckItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckItemBinding

    private var screenMode = MODE_UNKNOWN
    private var checkItemId = CheckItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        parseIntent()
        launchRightMode()
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> CheckItemFragment.newInstanceEditItem(checkItemId)
            MODE_ADD -> CheckItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager
            .beginTransaction()
            .add(R.id.check_item_container, fragment)
            .commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode: $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_CHECK_ITEM_ID)) {
                throw RuntimeException("Param check item id is absent")
            }
            checkItemId = intent.getIntExtra(EXTRA_CHECK_ITEM_ID, CheckItem.UNDEFINED_ID)
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_CHECK_ITEM_ID = "extra_check_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, CheckItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, itemId: Int): Intent {
            val intent = Intent(context, CheckItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_CHECK_ITEM_ID, itemId)
            return intent
        }
    }
}