package com.example.dognutritionapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Set up default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
        }

        // Handle bottom navigation item clicks
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            val selectedFragment: Fragment? = when (menuItem.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_food -> FoodFragment()
                R.id.nav_add -> AddFragment()
                R.id.nav_pet -> PetFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> null
            }
            selectedFragment?.let {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, it).commit()
            }
            true
        }
    }
}
