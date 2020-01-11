package com.aravindh.andriodbasesetup.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Logger.d("onCreate")

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this,
                R.layout.activity_main
            )



        drawerLayout = binding.drawerLayout
        navController = findNavController(R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    /*override fun onStart() {
        super.onStart()
        Logger.d("onStart")
    }

    override fun onResume() {
        super.onResume()
        Logger.d("onResume")
    }

    override fun onPause() {
        super.onPause()
        Logger.d("onPause")
    }

    override fun onStop() {
        super.onStop()
        Logger.d("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Logger.d("onRestart")
    }*/
}
