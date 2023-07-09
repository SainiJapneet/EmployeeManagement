package com.example.employeemanagement

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var fab: FloatingActionButton

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //For making Humber icon functional
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        //For making menu options functional
        when(item.itemId){

            R.id.addEmployee -> {
                var intent = Intent(this@MainActivity,AddEmployee :: class.java)
                startActivity(intent)
            }

            R.id.employeeList -> {
                var intent = Intent(this@MainActivity,EmployeeList :: class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationLayout)
        toggle = ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener {
            var intent = Intent(this@MainActivity, AddEmployee :: class.java)
            startActivity(intent)
        }

        navigationView.setNavigationItemSelectedListener{
            when(it.itemId){

                R.id.addEmployee -> {
                    var intent = Intent(this@MainActivity,AddEmployee :: class.java)
                    startActivity(intent)
                }

                R.id.employeeList -> {
                    var intent = Intent(this@MainActivity,EmployeeList :: class.java)
                    startActivity(intent)
                }
            }
            true
        }

    }
}