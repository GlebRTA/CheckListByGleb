package com.example.checklistbygleb.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.checklistbygleb.R
import com.example.checklistbygleb.databinding.ActivityCheckItemBinding
import com.example.checklistbygleb.databinding.FragmentCheckItemBinding
import com.example.checklistbygleb.domain.CheckItem

class CheckItemFragment : Fragment() {

    private lateinit var binding: FragmentCheckItemBinding
    private val viewModel: CheckItemViewModel by viewModels()

    private var screenMode = MODE_UNKNOWN
    private var checkItemId = CheckItem.UNDEFINED_ID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parseIntent()
        //viewModel = ViewModelProvider(this)[CheckItemViewModel::class.java]
        launchRightMode()
        initAddTextChangerListener()
        initEditTextChangerListener()
        setErrorInputCount()
        setErrorInputName()

    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun initAddTextChangerListener() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {  }
        })
    }

    private fun initEditTextChangerListener() {
        binding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {  }
        })
    }

    private fun launchEditMode() = with(binding) {
        viewModel.getCheckItem(checkItemId)
        viewModel.checkItem.observe(viewLifecycleOwner) {
            etName.setText(it.name)
            etCount.setText(it.count.toString())
        }
        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val count = etCount.text.toString()
            viewModel.editCheckItem(name, count)
        }
        closeActivity()
    }

    private fun launchAddMode() = with(binding) {
        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val count = etCount.text.toString()
            viewModel.addCheckItem(name, count)
        }
        closeActivity()
    }

    private fun setErrorInputName() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            binding.tilName.error = message
        }
    }

    private fun setErrorInputCount() {
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            binding.tilCount.error = message
        }
    }

    private fun closeActivity() {
        /*viewModel.isClosable.observe(viewLifecycleOwner) {
            finish()
        }*/
    }

    private fun parseIntent() {
        /*if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
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
        }*/
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