package com.example.toyawair.ext

import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.setupActionBar(@IdRes toolbarId : Int, action : ActionBar.() -> Unit){
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {
        action()
    }
}