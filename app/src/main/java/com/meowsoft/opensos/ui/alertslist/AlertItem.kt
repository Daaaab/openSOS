package com.meowsoft.opensos.ui.alertslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FlashlightOn
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meowsoft.opensos.domain.model.Alert
import com.meowsoft.opensos.ui.theme.Purple40
import com.meowsoft.opensos.ui.theme.dimen16

@Composable
fun AlertItem(
    alert: Alert,
    index: Int,
    onDismissClick: () -> Unit,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{ onItemClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "${index+1}.")
        Spacer(modifier = Modifier.width(dimen16))
        Column {
            Text(text = alert.phoneNumber)
            Text(text = alert.textMessage)
        }
        Row(
            modifier = Modifier
                .weight(1F),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "",
                tint = getColor(alert.isRingtoneActionOn)
            )

            Icon(
                imageVector = Icons.Rounded.FlashlightOn,
                contentDescription = "",
                tint = getColor(alert.isFlashlightActionOn)
            )
            IconButton({onDismissClick()}) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "",
                    tint = Color.Red
                )
            }
        }
    }
}

private fun getColor(isOn: Boolean) = if (isOn) {
    Purple40
} else {
    Color.LightGray
}

@Preview(showBackground = true)
@Composable
private fun AlertItemPreview() {
    AlertItem(
        alert = Alert(
           "508307069",
            "Message",
            isRingtoneActionOn = true,
            isFlashlightActionOn = false
        ),
        index = 0, {}) {}
}