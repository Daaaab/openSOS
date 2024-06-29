package com.meowsoft.opensos.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.meowsoft.opensos.ui.theme.dimen16

@Composable
fun VSPacer(height: Dp = dimen16) {
    Spacer(modifier = Modifier.height(height))
}