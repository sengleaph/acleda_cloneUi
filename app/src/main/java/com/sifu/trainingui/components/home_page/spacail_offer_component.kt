package com.sifu.trainingui.components.home_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sifu.trainingui.R
import com.sifu.trainingui.ui.theme.DarkPrimary
import com.sifu.trainingui.ui.theme.WhiteTransparent

@Composable
fun SpacialOfferScreenComponent(
    textStyle: TextStyle = TextStyle(color = DarkPrimary, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Spacial Offers",
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
        GridAds()
    }
}

@Composable
fun GridAds() {
    val imageList = listOf(
        R.drawable.im_new_year_ac,
        R.drawable.im_ac_promotion_ad_1,
        R.drawable.im_ac_promotion_ad_2,
        R.drawable.im_ac_promotion_ad_3,
        R.drawable.im_ac_promotion_ad_4,
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),  // 2 columns
        modifier = Modifier.fillMaxWidth().height(610.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
//        items(imageList.size){index ->
//            AdCard(imageRes = imageList[index])
//        }
        item(
            span = { GridItemSpan(2) }
        ) {
            AdCard(
                imageRes = imageList[0],
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Remaining items: normal 2-column layout
        items(imageList.drop(1)) { imageRes ->
            AdCard(
                imageRes = imageRes,
                modifier = Modifier
//                    .fillMaxWidth()
                    .height(180.dp)
            )
        }
    }
}

@Composable
fun AdCard(
    imageRes: Int,
    modifier: Modifier = Modifier,
    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
) {
    Card(
        modifier = modifier,
        shape = cornerShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null, // For decorative images, contentDescription can be null
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    SpacialOfferScreenComponent()
}