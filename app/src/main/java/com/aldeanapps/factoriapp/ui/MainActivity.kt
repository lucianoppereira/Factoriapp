package com.aldeanapps.factoriapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldeanapps.factoriapp.databinding.ActivityMainBinding
import com.aldeanapps.factoriapp.domain.model.TimeStampModel
import com.aldeanapps.factoriapp.ui.listener.HistoryItemClickListener
import com.aldeanapps.factoriapp.ui.view.adapter.TimeStampListAdapter
import com.aldeanapps.factoriapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), HistoryItemClickListener {

    private val vm: MainViewModel by viewModels()
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm.updateTimeStamps()

        configUI()
    }

    private fun configUI() {
        setRecyclerView()
        configFactorialButton()
        configTimeStampButton()
    }

    private fun configTimeStampButton() {
        binding.timeStampButton.setOnClickListener {
            vm.getTimeStamp { message(it) }
        }
    }

    private fun configFactorialButton() {
        binding.factorialButton.setOnClickListener {
            vm.getFactorial(
                binding.inputET.text.toString(),
                onLoading = { isLoading -> setLoadingState(isLoading) },
                action = { result -> message(result) }
            )
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        vm.timeStamps.observe(this) { stampList ->
            binding.recyclerView.adapter =
                TimeStampListAdapter(stampList ?: emptyList(), this)
        }
    }

    private fun message(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClickListener(item: TimeStampModel) {
        vm.removeTimeStamp(item)
    }

    private fun setLoadingState(visible: Boolean) {
        runOnUiThread {
            binding.progressBar.visibility = if (visible) View.VISIBLE else View.GONE
            binding.factorialButton.isEnabled = !visible
        }
    }
}
