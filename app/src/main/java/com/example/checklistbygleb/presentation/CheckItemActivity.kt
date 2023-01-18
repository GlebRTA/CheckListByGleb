package com.example.checklistbygleb.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.checklistbygleb.databinding.ActivityCheckItemBinding


class CheckItemActivity : AppCompatActivity() {

    private lateinit var viewModel: CheckItemViewModel
    private lateinit var binding: ActivityCheckItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_CHECK_ITEM_ID = "extra_check_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

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