package com.example.mobflix.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.compose.rememberAsyncImagePainter
import com.example.compose.ui.theme.*
import com.example.mobflix.R
import com.example.mobflix.model.VideoModel
import com.example.mobflix.service.listener.FABListener
import com.example.mobflix.ui.screens.HomeScreen
import com.example.mobflix.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    // ViewModel
    private val viewModel: MainViewModel by viewModel()

    // Transition
    private var transition = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fabListener = object : FABListener {
            override fun onFABClick() {
                viewModel.navigation()
            }
        }

        // Observers
        observe()

        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreenLayout(fabListener)
            }
        }
    }

    private fun observe() {
        viewModel.fabClick.observe(viewLifecycleOwner) {
            if (it) {
                val direction = MainFragmentDirections.actionMainFragmentToVideoRegistrationFragment()
                findNavController().navigate(direction)
            }
        }
    }
}

@Composable
fun HomeScreenLayout(listener: FABListener) {
    HomeScreen(listener)
}
