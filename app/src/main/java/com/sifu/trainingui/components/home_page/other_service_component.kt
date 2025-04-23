package com.sifu.trainingui.components.home_page

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.sifu.trainingui.R
import com.sifu.trainingui.model.OtherServiceData
import com.sifu.trainingui.ui.theme.DarkPrimary
import com.sifu.trainingui.ui.theme.WhiteTransparent
import com.sifu.trainingui.ui.theme.WhiteTransparent40
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@Composable
fun OtherServiceScreenComponent(
    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
    textStyle: TextStyle = TextStyle(
        color = DarkPrimary,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = WhiteTransparent40),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .border(width = 0.dp, color = WhiteTransparent, shape = cornerShape)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Others Service",
                style = textStyle
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhiteTransparent
                ),
                onClick = {}
            ) {
                Text(text = "View All", style = textStyle)
            }
        }
        Column {
            OtherServiceScreen()
            ScrollOtherServiceCardView()
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OtherServiceScreen() {
    val pagerState = rememberPagerState(initialPage = 0)
    // Use OtherServiceData as the image slider source.
    val imageSlider = OtherServiceData

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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Column {
            HorizontalPager(
                count = imageSlider.size,
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = 20.dp,
                    vertical = 5.dp
                ),
                modifier = Modifier
                    .height(200.dp)
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
                    Image(
                        painter = painterResource(
                            id = imageSlider[page].backgroundImage ?: R.drawable.im_bg_water_park
                        ),
                        contentDescription = imageSlider[page].textTitle,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(16.dp),
            activeColor = Color.White,
            inactiveColor = Color.LightGray,
            spacing = 4.dp,
            indicatorHeight = 8.dp,
            indicatorWidth = 8.dp,
        )
    }
}

// ScrollHorizontalCardView: A horizontally scrolling row of feature cards.
@Composable
fun ScrollOtherServiceCardView() {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(OtherServiceData){ service ->
                Column(
                    modifier = Modifier
                        .size(140.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(70.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = service.imageIcon),
                            contentDescription = service.textTitle,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize(),
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        modifier = Modifier
//                            .height(100.dp)
                            .padding(vertical = 5.dp),
                        text = service.textTitle,
                        maxLines = 2,
                        style = TextStyle(
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
}
