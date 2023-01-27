package com.stephenleedev.neighborandroid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.stephenleedev.neighborandroid.R
import com.stephenleedev.neighborandroid.databinding.ActivityMainBinding
import com.stephenleedev.neighborandroid.ui.main.chat.ChatFragment
import com.stephenleedev.neighborandroid.ui.main.map.MapFragment
import com.stephenleedev.neighborandroid.ui.main.myinfo.MyInfoFragment
import com.stephenleedev.neighborandroid.viewmodel.navigation.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navigationViewModel: NavigationViewModel by viewModels()

    private val mapFragment = MapFragment()
    private val chatFragment = ChatFragment()
    private val myInfoFragment = MyInfoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        initObservers()
    }

    private fun setUpBottomNavigation() {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.fragmentContainer, mapFragment)
        transaction.add(R.id.fragmentContainer, chatFragment)
        transaction.add(R.id.fragmentContainer, myInfoFragment)
        transaction.commit()

        moveToMapFragment()

        binding.bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigation_map -> {
                    moveToMapFragment()
                }
                R.id.navigation_chat -> {
                    moveToChatFragment()
                }
                R.id.navigation_myinfo -> {
                    moveToMyInfoFragment()
                }
            }
            true
        }
    }

    private fun initViews() {
        setUpBottomNavigation()
    }

    private fun initObservers() {
        navigationViewModel.navigationTabState.observe(this@MainActivity) {
            when (it) {
                NavigationTabItemType.NAVIGATION_CHAT.type -> {
                    moveToMyInfoFragment()
                }
            }

        }
    }

    private fun hideAllFragment() {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.hide(mapFragment)
        transaction.hide(chatFragment)
        transaction.hide(myInfoFragment)
        transaction.commitAllowingStateLoss()
    }

    private fun moveToMapFragment() {
        hideAllFragment()
        supportFragmentManager.beginTransaction().show(mapFragment).commitAllowingStateLoss()
        binding.bottomNavigation.menu.findItem(R.id.navigation_map).isChecked = true
    }

    private fun moveToChatFragment() {
        hideAllFragment()
        supportFragmentManager.beginTransaction().show(chatFragment).commitAllowingStateLoss()
        binding.bottomNavigation.menu.findItem(R.id.navigation_chat).isChecked = true
    }

    private fun moveToMyInfoFragment() {
        hideAllFragment()
        supportFragmentManager.beginTransaction().show(myInfoFragment).commitAllowingStateLoss()
        binding.bottomNavigation.menu.findItem(R.id.navigation_myinfo).isChecked = true
    }

}

enum class NavigationTabItemType(val type: String) {
    NAVIGATION_MAP("NAVIGATION_MAP"),
    NAVIGATION_CHAT("NAVIGATION_CHAT"),
    NAVIGATION_MY_INFO("NAVIGATION_MY_INFO")
}