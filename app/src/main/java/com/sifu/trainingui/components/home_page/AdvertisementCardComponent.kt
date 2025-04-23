//package com.sifu.trainingui.components.home_page
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.sifu.trainingui.R
//import com.sifu.trainingui.components.utils.AdvertData
//
//class AdvertisementCardComponent(
//    private val advertData: AdvertData
//) {
//    @Composable
//    fun Render(modifier: Modifier = Modifier) {
//        Card(
//            modifier = modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            shape = RoundedCornerShape(16.dp),
//            colors = CardDefaults.cardColors(containerColor = Color.White),
//            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                // Image section:
//                Image(
//                    painter = painterResource(id = advertData.imageRes),
//                    contentDescription = advertData.title,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(180.dp)
//                        .clip(RoundedCornerShape(12.dp)),
//                    contentScale = ContentScale.Crop
//                )
//                // Title:
//                Text(
//                    text = advertData.title,
//                    style = MaterialTheme.typography.titleMedium.copy(
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//                )
//                // Subtitle:
//                Text(
//                    text = advertData.subtitle,
//                    style = MaterialTheme.typography.titleSmall.copy(
//                        color = Color.Gray
//                    )
//                )
//                // Description:
//                Text(
//                    text = advertData.description,
//                    style = MaterialTheme.typography.bodyMedium.copy(
//                        color = Color.DarkGray
//                    )
//                )
//            }
//        }
//    }
//}
//
//// Usage in a Composable context:
//@Preview(showBackground = true)
//@Composable
//fun AdvertisementCardComponentPreview() {
//    val advertData = AdvertData(
//        imageRes = R.drawable.acleda_advertise_im,  // Use your drawable resource here
//        title = "Amazing Offer!",
//        subtitle = "Limited Time Only",
//        description = "Enjoy exclusive benefits and savings with our special promotion. Don't miss out!"
//    )
//    val advertComponent = AdvertisementCardComponent(advertData)
//    advertComponent.Render()
//}
