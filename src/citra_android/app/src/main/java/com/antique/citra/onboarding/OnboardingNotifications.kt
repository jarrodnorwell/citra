package com.antique.citra.onboarding

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antique.citra.Common
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun OnboardingScreen_Two(pagerState: PagerState) {
    val notificationsAllowed = LocalContext.current.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
    val canProceed = remember { mutableStateOf(notificationsAllowed) }
    val launcher = rememberLauncherForActivityResult(RequestPermission()) { canProceed.value = it }

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
                text = "Notifications",
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
                text = "Citra will use notifications to present you with information when needed",
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Gray,
                fontSize = Common.bodyFontSize.sp,
                textAlign = TextAlign.Center
            )
            if (canProceed.value) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 20.dp)
                )
                Text(
                    text = "Already Granted",
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.Green,
                    fontSize = Common.calloutFontSize.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(weight = 1.0f)
            )
            Button(
                onClick = {
                    when (canProceed.value) {
                        false -> {
                            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                        true ->
                            GlobalScope.launch(Dispatchers.Main) {
                                pagerState.scrollToPage(page = pagerState.currentPage + 1)
                            }
                    }
                },
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 50.dp)
            ) {
                Text(
                    text = when (canProceed.value) {
                        false -> "Grant Access"
                        true -> "Continue"
                    }
                )
            }
        }
    }
}