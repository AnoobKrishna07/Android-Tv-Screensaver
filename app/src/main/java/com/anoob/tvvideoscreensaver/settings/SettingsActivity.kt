package com.anoob.tvvideoscreensaver.settings

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.anoob.tvvideoscreensaver.databinding.ActivitySettingsBinding
import com.anoob.tvvideoscreensaver.viewmodel.SettingsViewModel
import com.google.android.material.card.MaterialCardView
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

        // Card Focus
        setupCardFocus(binding.cardLoop)
        setupCardFocus(binding.cardMute)


        // Switch Focus
        setupFocus(binding.switchLoop)
        setupFocus(binding.switchMute)
        binding.switchLoop.requestFocus()

    }

    private fun observeSettings() {

        lifecycleScope.launch {

            viewModel.loop.collectLatest {

                binding.switchLoop.isChecked = it

            }
        }

        lifecycleScope.launch {

            viewModel.mute.collectLatest {

                binding.switchMute.isChecked = it

            }
        }
    }

    private fun setupListeners() {

        binding.switchLoop.setOnCheckedChangeListener { _, checked ->

            viewModel.saveLoop(checked)

        }

        binding.switchMute.setOnCheckedChangeListener { _, checked ->

            viewModel.saveMute(checked)

        }

    }

    /**
     * Animation for Material Cards
     */
    private fun setupCardFocus(card: MaterialCardView) {

        card.setOnFocusChangeListener { _, hasFocus ->

            if (hasFocus) {

                card.animate()
                    .scaleX(1.03f)
                    .scaleY(1.03f)
                    .setDuration(150)
                    .start()

                card.strokeWidth = 5
                card.cardElevation = 20f

            } else {

                card.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(150)
                    .start()

                card.strokeWidth = 2
                card.cardElevation = 8f
            }
        }
    }

    /**
     * Animation for Switches
     */
    private fun setupFocus(view: View) {

        view.setOnFocusChangeListener { _, hasFocus ->

            if (hasFocus) {

                view.animate()
                    .scaleX(1.15f)
                    .scaleY(1.15f)
                    .setDuration(150)
                    .start()

            } else {

                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(150)
                    .start()

            }
        }
    }
}