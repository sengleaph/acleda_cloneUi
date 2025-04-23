package com.sifu.trainingui.components.home_page

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sifu.trainingui.R
import com.sifu.trainingui.components.utils.insuranceList
import com.sifu.trainingui.ui.theme.DarkPrimary
import com.sifu.trainingui.ui.theme.WhiteTransparent
import com.sifu.trainingui.ui.theme.WhiteTransparent40

@Composable
fun InsuranceScreen(
    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
    textStyle: TextStyle = TextStyle(
        color = DarkPrimary,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )
) {


    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = "Insurance",
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
        InsuranceComponent()
    }
}

@Composable
fun InsuranceComponent(
    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
    cardCornerShape: RoundedCornerShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
    textTitle: TextStyle = TextStyle(
        color = DarkPrimary,
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
    ),
    textSubtitle: TextStyle = TextStyle(
        color = DarkPrimary,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        )
){
    // Use LocalConfiguration to access screen dimensions for a responsive width.
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    // Each card will be 70% of the screen width.
    val cardWidth = screenWidth * 0.8f

    var maxCardHeightPx by remember{ mutableStateOf(0) }
    val desnity = LocalDensity.current
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        items(insuranceList.size) { index ->

            val minHeightDp = with(desnity){
                maxCardHeightPx.toDp()
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = WhiteTransparent40),
                shape = cornerShape,
                modifier = Modifier
                    .width(cardWidth)
                    // 3) Measure this card after layout
                    .onGloballyPositioned { coords ->
                        val h = coords.size.height
                        if (h > maxCardHeightPx) {
                            maxCardHeightPx = h
                        }
                    }
                    // 4) Enforce a minimum height equal to the tallest
                    .heightIn(min = minHeightDp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(vertical = 10.dp, horizontal = 10.dp)
                ) {
                    Image(
                        painterResource(id = insuranceList[index].imageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .clip(cardCornerShape)
                            .height(200.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp,)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.im_prodentail),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(50.dp))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = insuranceList[index].title,
                            style = textTitle,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = insuranceList[index].subtitle,
                        style = textSubtitle,
//                        maxLines = 3,
                    )
                }
            }
        }
    }
}
/*

@Preview
@Composable
fun InsuranceScreenPreview() {
    InsuranceScreen()
}*/
