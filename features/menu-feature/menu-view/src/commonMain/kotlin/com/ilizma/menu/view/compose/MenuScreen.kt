package com.ilizma.menu.view.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.unit.dp
import com.ilizma.menu.presentation.model.MenuScreenIntent
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.resources.Res
import com.ilizma.resources.facebook
import com.ilizma.resources.ic_facebook
import com.ilizma.resources.ic_instagram
import com.ilizma.resources.ic_phone
import com.ilizma.resources.ic_web
import com.ilizma.resources.ic_whatsapp
import com.ilizma.resources.ic_x
import com.ilizma.resources.instagram
import com.ilizma.resources.phone
import com.ilizma.resources.twitter
import com.ilizma.resources.ui.theme.EztandaIrratiappTheme
import com.ilizma.resources.web
import com.ilizma.resources.whatsapp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MenuScreen(
    viewModel: MenuScreenViewModel,
    paddingValues: PaddingValues,
) {
    BackHandler { viewModel.onIntent(MenuScreenIntent.Back) }
    MenuScreenContent(
        paddingValues = paddingValues,
        onIntent = { viewModel.onIntent(it) },
    )
}

@Composable
internal fun MenuScreenContent(
    paddingValues: PaddingValues,
    onIntent: (MenuScreenIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        SimpleRow(
            iconRes = Res.drawable.ic_instagram,
            iconDescription = "Instagram",
            title = Res.string.instagram,
            onClick = { onIntent(MenuScreenIntent.Instagram) },
        )
        SimpleRow(
            iconRes = Res.drawable.ic_x,
            iconDescription = "Twitter",
            title = Res.string.twitter,
            onClick = { onIntent(MenuScreenIntent.Twitter) },
        )
        SimpleRow(
            iconRes = Res.drawable.ic_facebook,
            iconDescription = "Facebook",
            title = Res.string.facebook,
            onClick = { onIntent(MenuScreenIntent.Facebook) },
        )
        SimpleRow(
            iconRes = Res.drawable.ic_web,
            iconDescription = "Web",
            title = Res.string.web,
            onClick = { onIntent(MenuScreenIntent.Web) },
        )
        SimpleRow(
            iconRes = Res.drawable.ic_phone,
            iconDescription = "Phone",
            title = Res.string.phone,
            onClick = { onIntent(MenuScreenIntent.Phone) },
        )
        SimpleRow(
            iconRes = Res.drawable.ic_whatsapp,
            iconDescription = "Whatsapp",
            title = Res.string.whatsapp,
            onClick = { onIntent(MenuScreenIntent.WhatsApp) },
        )
    }
}

@Composable
private fun SimpleRow(
    iconRes: DrawableResource,
    iconDescription: String,
    title: StringResource,
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
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(iconRes),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = iconDescription,
            )
            Text(
                text = stringResource(title),
            )
        }
    }
}

@Preview
@Composable
private fun MenuScreenPreview() {
    EztandaIrratiappTheme(dynamicColor = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { paddingValues ->
            MenuScreenContent(
                paddingValues = paddingValues,
                onIntent = {},
            )
        }
    }
}