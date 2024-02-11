package com.ilizma.menu.view.compose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ilizma.menu.presentation.model.MenuNavigationAction
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.resources.R
import kotlinx.coroutines.flow.Flow

@Composable
fun MenuScreen(
    viewModel: MenuScreenViewModel,
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        SimpleRow(
            iconRes = R.drawable.ic_instagram,
            iconDescription = "Instagram",
            title = R.string.instagram,
            onClick = { viewModel.onInstagram() },
        )
        SimpleRow(
            iconRes = R.drawable.ic_twitter,
            iconDescription = "Twitter",
            title = R.string.twitter,
            onClick = { viewModel.onTwitter() },
        )
        SimpleRow(
            iconRes = R.drawable.ic_facebook,
            iconDescription = "Facebook",
            title = R.string.facebook,
            onClick = { viewModel.onFacebook() },
        )
        SimpleRow(
            iconRes = R.drawable.ic_web,
            iconDescription = "Web",
            title = R.string.web,
            onClick = { viewModel.onWeb() },
        )
        DoubleRow(
            iconRes = R.drawable.ic_phone,
            iconRes2 = R.drawable.ic_whatsapp,
            iconDescription = "Phone",
            iconDescription2 = "Whatsapp",
            title = R.string.phone,
            title2 = R.string.whatsapp,
            onClick = { viewModel.onPhone() },
        )
    }
}

@Composable
private fun SimpleRow(
    @DrawableRes iconRes: Int,
    iconDescription: String,
    @StringRes title: Int,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            )
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = iconDescription,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(title),
            )
        }
    }
}

@Composable
private fun DoubleRow(
    @DrawableRes iconRes: Int,
    @DrawableRes iconRes2: Int,
    iconDescription: String,
    iconDescription2: String,
    @StringRes title: Int,
    @StringRes title2: Int,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            )
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = iconDescription,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(title),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(iconRes2),
                contentDescription = iconDescription2,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(title2),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuScreenPreview(
    @PreviewParameter(MenuScreenPreviewProvider::class) viewModel: MenuScreenViewModel,
    paddingValues: PaddingValues = PaddingValues(),
) {
    MenuScreen(
        viewModel = viewModel,
        paddingValues = paddingValues,
    )
}

private class MenuScreenPreviewProvider : PreviewParameterProvider<MenuScreenViewModel> {
    override val values: Sequence<MenuScreenViewModel> = sequenceOf(FakeViewModel())

    class FakeViewModel : MenuScreenViewModel() {
        override val navigationAction: Flow<MenuNavigationAction>
            get() = TODO("Not yet implemented")

        override fun onInstagram() {
        }

        override fun onTwitter() {
        }

        override fun onFacebook() {
        }

        override fun onPhone() {
        }

        override fun onWeb() {
        }

        override fun onBack() {
        }

    }
}
