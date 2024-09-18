package com.antique.citra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.antique.citra.onboarding.OnboardingStart
import com.antique.citra.ui.theme.CitraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CitraTheme {
                OnboardingStart()
            }
        }
    }
}