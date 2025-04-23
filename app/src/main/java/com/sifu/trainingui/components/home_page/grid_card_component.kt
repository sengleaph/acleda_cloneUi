package com.sifu.trainingui.components.home_page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.sifu.trainingui.R
import com.sifu.trainingui.ui.theme.Navy
import com.sifu.trainingui.ui.theme.WhiteTransparent
import com.sifu.trainingui.ui.theme.WhiteTransparent40
import com.sifu.trainingui.components.utils.CardComponent
import com.sifu.trainingui.components.utils.navigation_bar.Screen
import com.sifu.trainingui.model.gridTwoLineListItem
import com.sifu.trainingui.model.grid_six_card_model
import com.sifu.trainingui.ui.theme.DarkPrimary
import gridCardFeature

// GridCardComponent: A vertical grid with 2 columns.
@Composable
fun GridCardComponent(
    containerColor: Color = WhiteTransparent,
    borderColor: Color = Color.White,
    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
    iconTint: Color = Navy,
    iconSize: Dp = 24.dp,
    textStyle: TextStyle = TextStyle(color = DarkPrimary, fontSize = 15.sp),
    navController: NavHostController // ← add this
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp,),
        modifier = Modifier.height(70.dp)
    ) {
        items(gridTwoLineListItem) { item ->
            GridTwoLineListItem(
                itemText = item.iconText,
                iconRes = item.iconData,
                contentColor = containerColor,
                borderColor = borderColor,
                cornerShape = cornerShape,
                iconTint = iconTint,
                iconSize = iconSize,
                textStyle = textStyle,
                navController = navController, // ← pass the NavHostController here
            )
        }
    }
}

@Composable
fun GridTwoLineListItem(
    itemText: String,
    iconRes: Int,
    navController: NavController,       // ← add this
    contentColor: Color = WhiteTransparent,
    borderColor: Color = Color.White,
    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
    iconTint: Color = Navy,
    iconSize: Dp = 24.dp,
    textStyle: TextStyle = TextStyle(color = Navy, fontSize = 15.sp),
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = contentColor),
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, borderColor, cornerShape)
            .fillMaxWidth()
            .clickable {
                // Navigate to Transfer page
                navController.navigate(Screen.Transfer.route)
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = itemText,
                    tint = iconTint,
                    modifier = Modifier.size(iconSize)
                )
                Spacer(Modifier.width(8.dp))
                Text(text = itemText, style = textStyle)
            }
        }
    }
}



// GridCardFeatureComponent: A vertical grid with 3 columns.
@Composable
fun GridCardFeatureComponent(
    navController: NavHostController,          // ← add this
    containerColor: Color = WhiteTransparent,
    borderColor: Color = Color.White,
    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
    iconTint: Color = Navy,
    iconSize: Dp = 35.dp,
    textStyle: TextStyle = TextStyle(color = DarkPrimary, fontSize = 14.sp)
) {
    LazyVerticalGrid(
        columns        = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 10.dp),
        modifier       = Modifier.height(240.dp)
    ) {
        items(grid_six_card_model) { item ->
            GridFeatureComponentCard(
                itemText      = item.iconText,
                iconRes       = item.iconData,
                navController = navController,  // ← pass it here
                contentColor  = containerColor,
                borderColor   = borderColor,
                cornerShape   = cornerShape,
                iconSize      = iconSize,
                textStyle     = textStyle,
                iconTint      = iconTint
            )
        }
    }
}



@Composable
fun GridFeatureComponentCard(
    itemText: String,
    iconRes: Int,
    navController: NavHostController,           // ← add this
    contentColor: Color = WhiteTransparent,
    borderColor: Color = Color.White,
    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
    iconTint: Color = Navy,
    iconSize: Dp = 24.dp,
    textStyle: TextStyle = TextStyle(color = DarkPrimary, fontSize = 10.sp)
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = contentColor),
        shape = cornerShape,
        border = BorderStroke(1.dp, borderColor),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.Transfer.route)
            }
    ) {
        Column(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter           = painterResource(iconRes),
                contentDescription = itemText,
                tint              = iconTint,
                modifier          = Modifier.size(iconSize)
            )
            Spacer(Modifier.height(8.dp))
            Text(text = itemText, style = textStyle)
        }
    }
}


// ScrollHorizontalCardView: A horizontally scrolling row of feature cards.
@Composable
fun ScrollHorizontalCardView(
    containerColor: Color = WhiteTransparent,
    borderColor: Color = Color.White,
    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
    iconTint: Color = Navy,
    iconSize: Dp = 30.dp,
    textStyle: TextStyle = TextStyle(color = DarkPrimary, fontSize = 10.sp)
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = WhiteTransparent40),
        modifier = Modifier
            .padding(horizontal = 16.dp)
//            .background(WhiteTransparent)
            .border(width = 0.dp, color = WhiteTransparent, shape = cornerShape)
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(gridCardFeature.size) { card ->
                val item = gridCardFeature[card]
                HorizontalFeatureCard(
                    itemText = item.iconText,
                    iconText = item.iconData,
                    iconTint = iconTint,
                    contentColor = containerColor,
                    borderColor = borderColor,
                    cornerShape = cornerShape,
                    iconSize = iconSize,
                    textStyle = textStyle
                )
            }
        }
    }
}

@Composable
fun HorizontalFeatureCard(
    itemText: String,
    iconText: ImageVector,
    contentColor: Color = WhiteTransparent,
    borderColor: Color = Color.White,
    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
    iconTint: Color = Navy,
    iconSize: Dp = 25.dp,
    textStyle: TextStyle = TextStyle(color = DarkPrimary, fontSize = 10.sp, fontWeight = FontWeight.Medium)
) {

    Box(
        modifier = Modifier
            .padding(start = 5.dp, end = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = contentColor),
                shape = cornerShape,
                border = BorderStroke(1.dp, borderColor),
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(50.dp)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = iconText,
                        contentDescription = itemText,
                        tint = iconTint,
                        modifier = Modifier.size(iconSize)
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = itemText,
                style = textStyle
            )
        }
    }
}
