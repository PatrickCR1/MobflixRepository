package com.example.mobflix.ui.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics.composable
import com.example.mobflix.R
import com.example.mobflix.ui.view.fragment.HomeScreenLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}



