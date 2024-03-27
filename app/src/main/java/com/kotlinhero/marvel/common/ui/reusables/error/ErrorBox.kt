package com.kotlinhero.marvel.common.ui.reusables.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlinhero.marvel.R
import com.kotlinhero.marvel.common.ui.reusables.buttons.DefaultButton

@Composable
fun ErrorBox(
    modifier: Modifier = Modifier,
    onClickTryAgain: () -> Unit,
    message: String = stringResource(R.string.your_internet_connection_might_be_having_some_issues)
) {
    Column(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.spider),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = message,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        DefaultButton(onClick = onClickTryAgain) {
            Text(
                text = "Try Again",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            )
        }
    }
}