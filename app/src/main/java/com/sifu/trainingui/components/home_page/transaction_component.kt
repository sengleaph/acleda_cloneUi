//package com.sifu.trainingui.components.home_page
//
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.sifu.trainingui.R
//import com.sifu.trainingui.components.utils.ContactLoader
//import com.sifu.trainingui.ui.theme.DarkPrimary
//import com.sifu.trainingui.ui.theme.Navy
//import com.sifu.trainingui.ui.theme.WhiteTransparent
//import com.sifu.trainingui.ui.theme.WhiteTransparent40
//
//@Composable
//fun TransactionComponent() {
//    Column {
//        Text(
//            modifier = Modifier.padding(horizontal = 20.dp),
//            text = "Recent Transactions"
//        )
//        ScrollTransactionCardView()
//    }
//}
//
//@Composable
//fun ScrollTransactionCardView(
//    containerColor: Color = WhiteTransparent,
//    borderColor: Color = Color.White,
//    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
//    iconTint: Color = Navy,
//    iconSize: Dp = 30.dp,
//    textStyle: TextStyle = TextStyle(color = DarkPrimary, fontSize = 15.sp)
//) {
//    // Create your list of contacts
//    val gridCardFeature = listOf(
//        ContactLoader(R.drawable.ic_public_service, "Jee"),
//        ContactLoader(R.drawable.ic_csx_trading, "Paa"),
//        ContactLoader(R.drawable.ic_cambodia_market, "Fu Sim 1"),
//        ContactLoader(R.drawable.ic_exchange_rate, "SiFu"),
//        ContactLoader(R.drawable.ic_account_summary, "Sengleaph"),
//        ContactLoader(R.drawable.ic_location, "oum pros")
//    )
//
//    Card(
//        colors = CardDefaults.cardColors(containerColor = WhiteTransparent40),
//        modifier = Modifier
//            .padding(horizontal = 25.dp)
//            .border(width = 0.dp, color = WhiteTransparent, shape = cornerShape)
//    ) {
//        LazyRow(
//            contentPadding = PaddingValues(horizontal = 20.dp),
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(gridCardFeature.size) { index ->
//                val item = gridCardFeature[index]
//                HorizontalContactCard(
//                    itemText = item.contactText,
//                    iconText = item.contactLoader, // Use the icon resource from ContactLoader here.
//                    iconTint = iconTint,
//                    contentColor = containerColor,
//                    borderColor = borderColor,
//                    cornerShape = cornerShape,
//                    iconSize = iconSize,
//                    textStyle = textStyle
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun HorizontalContactCard(
//    itemText: String,
//    iconText: Int,
//    contentColor: Color = WhiteTransparent,
//    borderColor: Color = Color.White,
//    cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
//    iconTint: Color = Navy,
//    iconSize: Dp = 25.dp,
//    textStyle: TextStyle = TextStyle(color = Navy, fontSize = 10.sp)
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp)
//            .padding(5.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//        Card(
//            colors = CardDefaults.cardColors(containerColor = contentColor),
//            shape = cornerShape,
//            border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(4.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    painter = painterResource(id = iconText),
//                    contentDescription = itemText,
//                    tint = iconTint,
//                    modifier = Modifier.size(iconSize)
//                )
//            }
//        }
//        Spacer(modifier = Modifier.size(8.dp))
//        Text(
//            text = itemText,
//            style = textStyle
//        )
//    }
//}
