package com.sifu.trainingui.components.home_page

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sifu.trainingui.Views.TransferPage.widget.extractInitials
import com.sifu.trainingui.Views.TransferPage.widget.name
import com.sifu.trainingui.components.utils.TransitionCardComponent
import com.sifu.trainingui.model.TrainsitionModel
import com.sifu.trainingui.ui.theme.DarkPrimary
import com.sifu.trainingui.ui.theme.Navy
import com.sifu.trainingui.ui.theme.WhiteTransparent
import com.sifu.trainingui.ui.theme.WhiteTransparent40

@Composable
fun TransactionComponent() {
    Column {
        Text(
            modifier = Modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp),
            text = "Recent Transactions",
            fontSize = 20.sp,
            color = DarkPrimary,
            fontWeight = FontWeight.SemiBold
        )
        // Pass in your model list here:
        ScrollTransactionCardView(items = TrainsitionModel)
    }
}

@Composable
fun ScrollTransactionCardView(
    items: List<TransitionCardComponent>,           // your data list
    containerColor: Color = WhiteTransparent,
    borderColor: Color = Color.White,
    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
    iconSize: Dp = 30.dp,
    textStyle: TextStyle = TextStyle(color = DarkPrimary, fontSize = 15.sp)
) {
    Card(
        colors   = CardDefaults.cardColors(containerColor = WhiteTransparent40),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(width = 0.dp, color = WhiteTransparent, shape = cornerShape)
    ) {
        LazyRow(
            contentPadding       = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = items,
                key   = { it.iconText }               // stable key
            ) { item ->
                HorizontalContactCard(
                    itemText     = item.iconText,
                    imageIcon    = item.imageIcon,
                    contentColor = containerColor,
                    borderColor  = borderColor,
                    cornerShape  = cornerShape,
                    iconSize     = iconSize,
                    textStyle    = textStyle
                )
            }
        }
    }
}

@Composable
fun HorizontalContactCard(
    itemText: String,
    imageIcon: Int?,                                              // nullable drawable
    contentColor: Color = WhiteTransparent,
    borderColor: Color = Color.White,
    cornerShape: RoundedCornerShape = RoundedCornerShape(100.dp),
    iconSize: Dp = 25.dp,
    textStyle: TextStyle = TextStyle(color = Navy, fontSize = 10.sp)
) {
    val initials = extractInitials(itemText)
    Column(
        modifier            = Modifier
            .width(100.dp)        // uniform card width
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = contentColor),
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .size(90.dp)
                .animateContentSize(),
        ) {
            if (imageIcon != null) {
                // show the provided drawable
                Image(
                    painter = painterResource(id = imageIcon),
                    contentDescription = itemText,
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize(),
                )
            } else {
                Text(
                    text = initials,
                    style = TextStyle(fontSize = 50.sp),
                    color = Navy,
                    modifier = Modifier
                        .padding(vertical = 20.dp, horizontal = 30.dp)
                )
            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text      = itemText,
            style     = textStyle,
            maxLines  = 1,
            overflow  = TextOverflow.Ellipsis
        )
    }
}



@Preview
@Composable
fun TransitionScreen(){
    TransactionComponent()
}