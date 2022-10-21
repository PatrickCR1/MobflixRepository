package com.example.mobflix.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.BlueAppName
import com.example.mobflix.R
import com.example.mobflix.ui.view.fragment.bebasNeueFamily

@Composable
fun AppName() {
    Column(
        Modifier
            .fillMaxWidth()
            .testTag(stringResource(id = R.string.app_name_full_column)),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(
            modifier = Modifier
                .height(38.dp)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .fillMaxWidth(),
                color = BlueAppName,
                fontFamily = bebasNeueFamily,
                fontWeight = FontWeight(400),
                fontSize = 32.sp,
                lineHeight = 38.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun AppNamePreview() {
    AppName()
}