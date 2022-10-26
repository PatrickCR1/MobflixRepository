package com.example.mobflix.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.BlueEditText
import com.example.compose.ui.theme.White
import com.example.mobflix.R
import com.example.mobflix.ui.theme.*
import com.example.mobflix.ui.viewmodel.VideoRegistrationViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun RegistrationFields(viewModel: VideoRegistrationViewModel = getViewModel()) {
    val urlText = viewModel.urlText.observeAsState().value
    val categoryText = viewModel.categoryText.observeAsState().value
    Column() {
        RegistrationFieldText(name = stringResource(id = R.string.url_registration))
        RegistrationFieldEditText(stringResource(id = R.string.url_text_hint), urlText!!) {
            viewModel.onUrlTextChanged(it)
        }
        Spacer(modifier = Modifier.height(smallSpacer))
        RegistrationFieldText(name = stringResource(id = R.string.category_registration))
        RegistrationFieldEditText(
            stringResource(id = R.string.category_text_hint),
            categoryText!!
        ) {
            viewModel.onCategoryChanged(it)
        }
    }
}

@Composable
@Preview
private fun RegistrationFieldsPreview() {
    RegistrationFields()
}

@Composable
fun RegistrationFieldText(name: String) {
    Text(
        text = name,
        modifier = Modifier
            .testTag(stringResource(id = R.string.registration_field_text))
            .padding(start = mediumPadding)
            .heightIn(16.dp)
            .widthIn(30.dp),
        color = White,
        fontFamily = robotoFamily,
        fontWeight = FontWeight(700),
        fontSize = 14.sp,
        lineHeight = 16.sp,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun RegistrationFieldTextPreview() {
    RegistrationFieldText(name = stringResource(id = R.string.url_registration))

}

@Composable
fun RegistrationFieldEditText(hint: String, text: String, function: (String) -> Unit) {
    Surface(
        shape = RoundedCornerShape(smallCornerShape),
        modifier = Modifier.padding(
            start = mediumPadding,
            end = mediumPadding,
            top = verySmallPadding
        )
    ) {
        TextField(
            value = text,
            onValueChange = function,
            placeholder = { Text(hint) },
            modifier = Modifier
                .testTag(stringResource(id = R.string.registration_field_edit_text))
                .heightIn(48.dp)
                .fillMaxWidth()
                .background(BlueEditText)
        )
    }
}

@Preview
@Composable
private fun RegistrationFieldEditTextPreview() {
    RegistrationFieldEditText(stringResource(id = R.string.url_text_hint), "") {
        exampleFun()
    }
}