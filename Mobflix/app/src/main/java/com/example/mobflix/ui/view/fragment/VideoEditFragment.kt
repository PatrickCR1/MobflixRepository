package com.example.mobflix.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobflix.toVideoModel
import com.example.mobflix.ui.screens.VideoEditScreen
import com.example.mobflix.ui.viewmodel.VideoEditViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoEditFragment : Fragment() {

    // ViewModel
    private val viewModel: VideoEditViewModel by viewModel()

    // Args
    private val args by navArgs<VideoEditFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Observer
        observe()

        // Load Args
        val video = args.videoModel.toVideoModel(Color.Cyan)
        viewModel.setVideoModel(video)

        return ComposeView(requireContext()).apply {
            setContent {
                VideoEditLayout()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getVideoPreview()
    }

    private fun observe() {
        viewModel.editButtonClick.observe(viewLifecycleOwner) {
            if (it) {
                if (viewModel.checkImage()) {
                    findNavController().popBackStack()
                    viewModel.clickEditComplete()
                } else {
                    viewModel.showSnackBar()
                }
            }
        }
        viewModel.deleteButtonClick.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
                viewModel.clickDeleteComplete()
            }
        }
    }
}

@Composable
fun VideoEditLayout() {
    VideoEditScreen()
}
