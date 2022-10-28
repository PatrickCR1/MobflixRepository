package com.example.mobflix.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.BlackBackground
import com.example.compose.ui.theme.LightBlue
import com.example.compose.ui.theme.White
import com.example.mobflix.R
import com.example.mobflix.service.listener.RegistrationButtonListener
import com.example.mobflix.ui.theme.*
import com.example.mobflix.ui.viewmodel.VideoRegistrationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun VideoRegistrationButton(viewModel: VideoRegistrationViewModel = getViewModel()) {
    val snackBarValue = viewModel.snackBar.observeAsState().value
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        Modifier
            .fillMaxSize()
            .background(BlackBackground)
    ) {
        Surface(
            shape = RoundedCornerShape(smallCornerShape),
            modifier = Modifier
                .padding(
                    top = smallPadding
                )
        ) {
            Box(
                Modifier
                    .testTag(stringResource(id = R.string.registration_button))
                    .background(LightBlue)
                    .height(48.dp)
                    .fillMaxWidth()
                    .clickable {
                        viewModel.videoRegistration()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.registration),
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight(normalLightFontWeight),
                    fontSize = 18.sp,
                    lineHeight = 21.sp,
                    textAlign = TextAlign.Center,
                    color = White,
                )
            }
        }
    }
}


@Preview
@Composable
private fun VideoRegistrationButtonPreview() {

}