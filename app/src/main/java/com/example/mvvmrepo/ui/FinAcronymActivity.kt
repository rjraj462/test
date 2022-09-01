package com.example.mvvmrepo.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmrepo.R
import com.example.mvvmrepo.databinding.ActivityMainBinding


class FinAcronymActivity : AppCompatActivity() {
    lateinit var dictionaryAdapter: DictionaryAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DictionaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModel()
        initViews()
        initObservers()
        binding.btnSearch.setOnClickListener {
            if (binding.edtVal.text.toString().isNullOrEmpty()) {
                Toast.makeText(
                    this@FinAcronymActivity,
                    "Please enter something...",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            this.hideKeyboard(it)
            viewModel.getDictionaryData(binding.edtVal.text.toString())
        }
    }

    private fun initObservers() {
        viewModel.dictionaryResponseLiveData.observe(this, Observer {
            if (it.size > 0) {
                dictionaryAdapter.setItems(it.get(0).lfs)
            } else {
                dictionaryAdapter.setItems(emptyList())
                Toast.makeText(
                    this@FinAcronymActivity,
                    "No Achroyn found..",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.progressLiveData.observe(this, Observer {
            if (it) {
                binding.rlProgress.visibility = View.VISIBLE
                binding.rvList.visibility = View.GONE
            } else {
                binding.rlProgress.visibility = View.GONE
                binding.rvList.visibility = View.VISIBLE
            }
        })

        viewModel.showAlertLiveData.observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initViews() {
        dictionaryAdapter = DictionaryAdapter()
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.rvList.layoutManager = llm
        binding.rvList.adapter = dictionaryAdapter
    }

    private fun initViewModel() {
        //initialize viewModel
        viewModel = ViewModelProvider(this).get(DictionaryViewModel::class.java)
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}