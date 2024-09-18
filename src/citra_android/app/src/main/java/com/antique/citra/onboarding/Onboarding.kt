package com.antique.citra.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antique.citra.Common
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun OnboardingStart() {
    val pagerState = rememberPagerState(pageCount = { 5 })

    HorizontalPager(pagerState) {
        when (it) {
            0 -> OnboardingScreen_One(pagerState) // Welcome
            1 -> OnboardingScreen_Two(pagerState) // Notifications
            2 -> OnboardingScreen_Three(pagerState) // Camera
            3 -> OnboardingScreen_Four(pagerState) // Microphone
            4 -> OnboardingScreen_Five(pagerState)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun OnboardingScreen_One(pagerState: PagerState) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(
                text = "Welcome",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp),
                fontSize = Common.titleFontSize.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "Welcome to Citra, a Nintendo 3DS emulator for Android, Linux, macOS and Windows",
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Gray,
                fontSize = Common.bodyFontSize.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier
                .weight(weight = 1.0f)
            )
            Button(
                onClick = {
                    GlobalScope.launch(Dispatchers.Main) {
                        pagerState.scrollToPage(page = pagerState.currentPage + 1)
                    }
                          },
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 50.dp)
            ) {
                Text(text = "Continue")
            }
        }
    }
}