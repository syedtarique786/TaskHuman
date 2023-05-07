package com.syed.humantask

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.syed.humantask.databinding.ActivityMainBinding
import com.syed.humantask.ui.main.MainFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbar()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        setUpNavigationBar()
    }

    private fun setUpNavigationBar() {
        binding.navigation.setOnItemSelectedListener { return@setOnItemSelectedListener true }

        binding.navigation.setOnItemReselectedListener {
            //yet to implement
        }
    }

    /**
     * Setting properties for Toolbar
     * */
    private fun setUpToolbar() {
        val toolbar = binding.toolbar.toolBar
        setSupportActionBar(toolbar)
        //Disable toolbar icon
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        toolbar.title = ""
        binding.toolbar.tvBack.setOnClickListener { onBackPressed() }
    }
}