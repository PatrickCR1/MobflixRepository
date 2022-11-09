package com.example.mobflix.ui.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.mobflix.R

@Composable
fun FabIcon(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick, Modifier.testTag(stringResource(id = R.string.floating_action_button_tag))) {
        Icon(Icons.Default.Add, contentDescription = null)
    }
}
