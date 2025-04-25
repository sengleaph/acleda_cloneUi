package com.sifu.trainingui.components.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sifu.trainingui.R
import com.sifu.trainingui.ui.theme.AmberColor
import com.sifu.trainingui.ui.theme.Navy

/*
//@Composable
//fun CircularPropagation(
//    percentage: Float,
//    number: Int,
//    fontSize: TextUnit = 28.sp,
//    color: Color = Navy,
//    radius: Dp = 8.dp,
//    strokeWidth: Dp = 8.dp,
//    animDuration: Int = 1000,
//    animDelay: Int = 0
//
//) {
//    var animationPlayed by remember {
//        mutableStateOf(false)
//    }
//    val curPercentage = animateFloatAsState(
//        targetValue = if (animationPlayed) percentage else 0f,
//        animationSpec = tween(
//            durationMillis = animDuration,
//            delayMillis = animDelay
//        )
//    )
//    LaunchedEffect(key1 = true) {
//        animationPlayed = true
//    }
//
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.size(radius * 15f)
//    ){
//        Canvas(modifier = Modifier.size(radius * 15f)) {
//            drawArc(
//                color = color,
//                -90f,
//                360 * curPercentage.value,
//                useCenter = false,
//                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Square)
//            )
//        }
//       Column {
//           Icon(
//               painter = painterResource(id = R.drawable.ic_wallet),
//               contentDescription = "Icon Shopping",
//               tint = Navy,
//               modifier = Modifier.size(50.dp)
//
//           )
//           Text(text = "Account", style = TextStyle(Navy))
//       }
//    }
//}
*/


@Composable
fun ProgressIconItem(
    icon: Painter,
    label: String,
    progress: Float,            // 0f..1f
    size: Dp = 100.dp,
    strokeWidth: Dp = 6.dp,
    trackColor: Color = AmberColor.copy(alpha = 0.3f),
    progressColor: Color = Navy,
    labelStyle:TextStyle = TextStyle(
        fontSize = 10.sp,
        color = Navy
    ),
    tint : Color,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(size)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size)

        ) {
            // 1) Draw full grey track + progress arc
            Canvas(
                modifier = Modifier
                    .matchParentSize(),
            ) {
                val stroke = strokeWidth.toPx()
                val radius = size.toPx() / 2f
                // track
                drawArc(
                    color = trackColor,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = stroke, cap = StrokeCap.Round),
                    topLeft = Offset(stroke/2, stroke/2),
                    size = Size((radius*2) - stroke, (radius*2) - stroke)
                )
                // progress
                drawArc(
                    color = progressColor,
                    startAngle = -90f,
                    sweepAngle = 360f * progress.coerceIn(0f,1f),
                    useCenter = false,
                    style = Stroke(width = stroke, cap = StrokeCap.Round),
                    topLeft = Offset(stroke/2, stroke/2),
                    size = Size((radius*2) - stroke, (radius*2) - stroke)
                )
            }

            // 2) Center icon
            Column {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(size * 0.5f),
                    tint = Navy
                )
                Text(
                    text = label,
                    style = labelStyle
                )
            }
        }
    }
}
