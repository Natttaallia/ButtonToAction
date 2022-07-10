package com.android.study.buttontoaction.app.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.study.buttontoaction.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        lifecycleScope.launchWhenCreated {
            viewModel.stateFlow.collectLatest {
                binding.actionPb.visibility =
                    if (it == MainViewModel.Companion.State.LOADING) View.VISIBLE else View.GONE
                binding.actionButton.visibility =
                    if (it == MainViewModel.Companion.State.LOADING) View.GONE else View.VISIBLE
            }
        }
        binding.actionButton.setOnClickListener {
            viewModel.invokeAction(this@MainActivity, binding.actionButton)
        }
    }
}
