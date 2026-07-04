package com.anoob.tvvideoscreensaver.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anoob.tvvideoscreensaver.MainActivity
import com.anoob.tvvideoscreensaver.databinding.ActivityDashboardBinding
import com.anoob.tvvideoscreensaver.settings.SettingsActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPreview.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnLibrary.setOnClickListener {
            startActivity(Intent(this, VideoLibraryActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }
}