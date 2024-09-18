package com.antique.citra.onboarding

import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antique.citra.BuildConfig
import com.antique.citra.Common
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun OnboardingScreen_Five(pagerState: PagerState) {
    val storageAllowed = Environment.isExternalStorageManager()
    val canProceed = remember { mutableStateOf(storageAllowed) }
    val launcher = rememberLauncherForActivityResult(StartActivityForResult()) { canProceed.value = Environment.isExternalStorageManager() }

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
                text = "Storage",
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
                text = "Citra will use your storage to create and manage the 3DS file system",
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
                            launcher.launch(Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, Uri.parse("package:${BuildConfig.APPLICATION_ID}")))
                        }
                        true -> {
                            // TODO: set onboardingComplete=1, launch library screen
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