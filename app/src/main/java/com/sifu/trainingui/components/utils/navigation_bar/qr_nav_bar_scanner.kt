package com.sifu.trainingui.components.utils.navigation_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sifu.trainingui.R
import com.sifu.trainingui.ui.theme.AmberColor

@Composable
fun NavigationBarQrCodeScanner(
    onClick: () -> Unit = {}
) {
    FloatingActionButton(

        containerColor = AmberColor,
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(50.dp))
            .border(
                3.dp,
                Color.White.copy(alpha = .5f),
                RoundedCornerShape(50.dp)
            )
            .shadow(elevation = 30.dp, ambientColor = AmberColor, shape = RoundedCornerShape(50.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_scan_qr),
            contentDescription = "Qr Code Scanner",
            modifier = Modifier
                .height(20.dp),
            tint = Color.White
        )
    }
}
//@Preview
//@Composable
//fun PreviewNavigationBarQrCodeScanner() {
//    NavigationBarQrCodeScanner()
//}