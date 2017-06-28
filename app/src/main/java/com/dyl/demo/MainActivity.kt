package com.dyl.demo

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val fs: MutableList<Class<out Fragment>> by lazy { mutableListOf<Class<out Fragment>>() }
    private lateinit var transaction:FragmentTransaction
    private var currentFragment:Fragment?=null
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setCurrentFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                setCurrentFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                setCurrentFragment(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        addFragment(HomeFragment::class.java)
        addFragment(AllFragment::class.java)
        addFragment(MyFragment::class.java)
        setCurrentFragment(0)
    }

    fun addFragment(fragment: Class<out Fragment>) {
        fs.add(fragment)
    }

    fun commitFragment(position: Int = 0) {
        currentFragment=fs[position].newInstance()
        transaction.add(R.id.content,currentFragment , fs[position].simpleName)
    }

    fun setCurrentFragment(position: Int) {
        transaction=supportFragmentManager.beginTransaction()
        if(currentFragment!=null){transaction.hide(currentFragment)}
        currentFragment = supportFragmentManager.findFragmentByTag(fs[position].simpleName)
        when (currentFragment) {
            null -> commitFragment(position)
            else -> transaction.show(currentFragment)
        }
        transaction.commit()
    }
}