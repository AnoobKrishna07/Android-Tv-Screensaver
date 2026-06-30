package com.anoob.tvvideoscreensaver.settings

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.anoob.tvvideoscreensaver.databinding.ActivitySettingsBinding
import com.anoob.tvvideoscreensaver.viewmodel.SettingsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeSettings()

        setupListeners()
    }

    private fun observeSettings() {

        lifecycleScope.launch {

            viewModel.videoSource.collectLatest {

                when (it) {

                    "BUILTIN" -> binding.radioBuiltin.isChecked = true
                    "INTERNAL" -> binding.radioInternal.isChecked = true
                    "USB" -> binding.radioUsb.isChecked = true
                    "NETWORK" -> binding.radioNetwork.isChecked = true
                }
            }
        }

        lifecycleScope.launch {

            viewModel.loop.collectLatest {

                binding.checkLoop.isChecked = it

            }
        }
    }

    private fun setupListeners() {

        binding.radioBuiltin.setOnClickListener {

            viewModel.saveVideoSource("BUILTIN")

        }

        binding.radioInternal.setOnClickListener {

            viewModel.saveVideoSource("INTERNAL")

        }

        binding.radioUsb.setOnClickListener {

            viewModel.saveVideoSource("USB")

        }

        binding.radioNetwork.setOnClickListener {

            viewModel.saveVideoSource("NETWORK")

        }

        binding.checkLoop.setOnCheckedChangeListener { _, checked ->

            viewModel.saveLoop(checked)

        }
    }
}