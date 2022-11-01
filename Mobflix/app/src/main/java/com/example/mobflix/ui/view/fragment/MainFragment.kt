package com.example.mobflix.ui.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobflix.toVideoDatabaseModel
import com.example.mobflix.ui.screens.HomeScreen
import com.example.mobflix.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    // ViewModel
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Observers
        observe()

        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreenLayout()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getVideoList()
        viewModel.getCategoryList()
    }

    private fun observe() {
        viewModel.fabClick.observe(viewLifecycleOwner) {
            if (it) {
                val direction =
                    MainFragmentDirections.actionMainFragmentToVideoRegistrationFragment()
                findNavController().navigate(direction)
                viewModel.navigationRegistrationScreenComplete()
            }
        }
        viewModel.videoClick.observe(viewLifecycleOwner) {
            if (it.url != "") {
                val direction =
                    MainFragmentDirections.actionMainFragmentToVideoEditFragment(it.toVideoDatabaseModel())
                findNavController().navigate(direction)
                viewModel.navigationEditScreenComplete()
            }
        }
        viewModel.urlYoutubeNavigation.observe(viewLifecycleOwner) {
            if (it != "") {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(intent)
                viewModel.navigationYoutubeComplete()
            }
        }
    }
}

@Composable
fun HomeScreenLayout() {
    HomeScreen()
}
