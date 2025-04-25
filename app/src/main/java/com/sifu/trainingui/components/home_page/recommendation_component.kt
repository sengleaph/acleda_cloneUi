package com.sifu.trainingui.components.sliders

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import androidx.compose.ui.util.lerp
import com.sifu.trainingui.model.advertsList
import com.sifu.trainingui.ui.theme.DarkPrimary

@Composable
fun RecommendedScreen(
    textStyle: TextStyle = TextStyle(
        color = DarkPrimary,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
) {
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Recommended",
                style = textStyle
            )
//            Button(
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = WhiteTransparent
//                ),
//                onClick = {}
//            ) {
//                Text(text = "View All", style = textStyle)
//            }
        }
        RecommendationScreen()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecommendationScreen(
    containerColor: Color = Color.Black,
    textStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 20.sp
    ),
    titleStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    subtitleStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 10.sp,
        fontWeight = FontWeight.W300
    ),
) {
    val pagerState = rememberPagerState(initialPage = 0)
    // Use OtherServiceData as the image slider source.
    val imageSlider = advertsList

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(3000)
            if (pagerState.pageCount > 0) {
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % pagerState.pageCount,
                    animationSpec = tween(durationMillis = 600)
                )
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            HorizontalPager(
                count = imageSlider.size,
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = 20.dp,
                ),
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth()
            ) { page ->
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            // Scale card based on page offset, clamped between 0.85 and 1.
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            // Adjust alpha based on offset.
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = imageSlider[page].imageRes),
                            contentDescription = imageSlider[page].title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black
                                        ),
                                        startY = 300f
                                    )
                                )
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Welcome",
                                    style = subtitleStyle
                                )
                                Text(
                                    text = imageSlider[page].title,
                                    style = titleStyle
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    modifier = Modifier.width(200.dp),
                                    text = imageSlider[page].subtitle,
                                    style = subtitleStyle
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        Brush.horizontalGradient(
                                            colors = listOf(
                                                Color(0xFF382eff),
                                                Color(0xFFfa00ff)
                                            )
                                        )
                                    )
                                    .clickable { }
                                    .padding(vertical = 8.dp, horizontal = 12.dp)
                            ) {
                                Text(
                                    text = "Click Here",
                                    style = subtitleStyle,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.White,
                inactiveColor = Color.LightGray,
                indicatorWidth = 8.dp,
                indicatorHeight = 8.dp,
                spacing = 4.dp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
            )
        }

    }
}
