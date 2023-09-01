package com.deanuharatinu.moviedatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.deanuharatinu.moviedatabase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initNavBottom()
  }

  private fun initNavBottom() {
    setSupportActionBar(binding.toolbar)

    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
    val navController = navHostFragment.navController
    binding.navView.setupWithNavController(navController)
    val appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
      )
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
  }
}