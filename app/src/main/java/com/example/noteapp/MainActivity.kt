package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(MainFragment.newInstance(),true)
    }
    fun replaceFragment(fragment:Fragment,transaction:Boolean){
        val fragmentTransiction=supportFragmentManager.beginTransaction()
        if(transaction){
               fragmentTransiction.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransiction.replace(R.id.container,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }
}