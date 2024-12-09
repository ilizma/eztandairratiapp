package com.ilizma.menu.view.compose

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ilizma.menu.presentation.model.MenuScreenIntent
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.resources.Res
import com.ilizma.resources.facebook
import com.ilizma.resources.ic_facebook
import com.ilizma.resources.ic_instagram
import com.ilizma.resources.ic_phone
import com.ilizma.resources.ic_twitter
import com.ilizma.resources.ic_web
import com.ilizma.resources.ic_whatsapp
import com.ilizma.resources.instagram
import com.ilizma.resources.phone
import com.ilizma.resources.twitter
import com.ilizma.resources.web
import com.ilizma.resources.whatsapp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
expect fun MenuScreen(
    viewModel: MenuScreenViewModel,
    paddingValues: PaddingValues,
)

@Composable
internal fun Content(
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
            iconRes = Res.drawable.ic_twitter,
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
        DoubleRow(
            iconRes = Res.drawable.ic_phone,
            iconRes2 = Res.drawable.ic_whatsapp,
            iconDescription = "Phone",
            iconDescription2 = "Whatsapp",
            title = Res.string.phone,
            title2 = Res.string.whatsapp,
            onClick = { onIntent(MenuScreenIntent.Phone) },
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
        ) {
            Icon(
                painter = painterResource(iconRes),
                tint = MaterialTheme.colorScheme.onPrimary,
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
    iconRes: DrawableResource,
    iconRes2: DrawableResource,
    iconDescription: String,
    iconDescription2: String,
    title: StringResource,
    title2: StringResource,
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
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = iconDescription,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(title),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(iconRes2),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = iconDescription2,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(title2),
            )
        }
    }
}