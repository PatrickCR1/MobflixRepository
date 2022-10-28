package com.example.mobflix.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobflix.ui.screens.RegistrationScreen
import com.example.mobflix.ui.viewmodel.VideoRegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoRegistrationFragment : Fragment() {

    // ViewModel
    private val viewModel: VideoRegistrationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Observer
        observe()

        return ComposeView(requireContext()).apply {
            setContent {
                RegistrationScreenLayout()
            }
        }
    }

    private fun observe() {
        viewModel.registrationClick.observe(viewLifecycleOwner) {
            if (it) {
                if (viewModel.checkImage()) {
                    findNavController().popBackStack()
                    viewModel.clickComplete()
                } else {
                    viewModel.showSnackBar()
                }
            }
        }
    }
}


@Composable
fun RegistrationScreenLayout() {
    RegistrationScreen()
}
