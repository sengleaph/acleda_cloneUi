package com.sifu.trainingui.Views.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sifu.trainingui.R
import com.sifu.trainingui.components.home_page.*
import com.sifu.trainingui.components.sliders.RecommendedScreen
import com.sifu.trainingui.components.utils.navigation_bar.Screen

@Composable
fun HomePage(navController: NavHostController) {
    Scaffold(
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_ac_logo),
                    contentDescription = "Acleda Logo",
                    modifier = Modifier.height(48.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Image(
                        painter  = painterResource(R.drawable.ic_customer_support),
                        contentDescription = "Support",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { /*...*/ }
                    )
                    Image(
                        painter  = painterResource(R.drawable.ic_bakung),
                        contentDescription = "QR Bakong",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                navController.navigate(Screen.KHQR.route)
                            }
                    )
                }
            }
        }
    ) { inner ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter      = painterResource(R.drawable.background),
                    contentScale = ContentScale.Crop
                )
        ) {
            Column(
                Modifier
                    .padding(inner)
                    .verticalScroll(rememberScrollState())
            ) {
                ProfileDisplay()
                BalanceBank()
                GridCardComponent(
                    navController = navController
                )
                GridCardFeatureComponent(
                    navController = navController
                )
                ScrollHorizontalCardView()
                RecommendedScreen()
                SpacialOfferScreenComponent()
                OtherServiceScreenComponent()
                InsuranceScreen()
            }
        }
    }
}
