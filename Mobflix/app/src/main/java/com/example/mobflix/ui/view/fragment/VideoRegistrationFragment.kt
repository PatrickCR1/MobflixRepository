package com.example.mobflix.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import coil.compose.rememberAsyncImagePainter
import com.example.compose.ui.theme.BlueEditText
import com.example.compose.ui.theme.LightBlue
import com.example.compose.ui.theme.White
import com.example.mobflix.R
import com.example.mobflix.service.listener.FABListener
import com.example.mobflix.ui.components.RegistrationText
import com.example.mobflix.ui.components.image
import com.example.mobflix.ui.screens.HomeScreen
import com.example.mobflix.ui.screens.RegistrationScreen
import com.example.mobflix.ui.theme.*

class VideoRegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                RegistrationScreenLayout()
            }
        }
    }
}

@Composable
fun RegistrationScreenLayout() {
    RegistrationScreen()
}
