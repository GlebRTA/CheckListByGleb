package com.example.checklistbygleb.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.checklistbygleb.R
import com.example.checklistbygleb.databinding.FragmentCheckItemBinding
import com.example.checklistbygleb.domain.CheckItem

class CheckItemFragment : Fragment() {

    private lateinit var binding: FragmentCheckItemBinding
    private lateinit var onEditingFinishedListener: OnEditingFinishedListener
    private val viewModel: CheckItemViewModel by viewModels()
    private var screenMode: String = MODE_UNKNOWN
    private var checkItemId: Int = CheckItem.UNDEFINED_ID

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
            throw RuntimeException("Activity must implement OnEditingFinishedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseIntent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        viewModel.isClosable.observe(viewLifecycleOwner) {
            //activity?.onBackPressed()
            onEditingFinishedListener.editFinishedListener()
        }
    }

    private fun parseIntent() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode: $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(CHECK_ITEM_ID)) {
                throw RuntimeException("Param check item id is absent")
            }
            checkItemId = args.getInt(CHECK_ITEM_ID, CheckItem.UNDEFINED_ID)
        }
    }

    interface OnEditingFinishedListener {
        fun editFinishedListener()
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val CHECK_ITEM_ID = "extra_check_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): CheckItemFragment {
            return CheckItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(checkItemId: Int): CheckItemFragment {
            return CheckItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(CHECK_ITEM_ID, checkItemId)
                }
            }
        }
    }
}