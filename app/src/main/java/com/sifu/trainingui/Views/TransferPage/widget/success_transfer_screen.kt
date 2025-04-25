package com.sifu.trainingui.Views.TransferPage.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sifu.trainingui.R
import com.sifu.trainingui.components.utils.navigation_bar.ButtonNavigationBar
import com.sifu.trainingui.components.utils.navigation_bar.Screen
import com.sifu.trainingui.ui.theme.AmberColor
import com.sifu.trainingui.ui.theme.Navy
import com.sifu.trainingui.ui.theme.Pink
import com.sifu.trainingui.ui.theme.Pink40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessTransferScreen(
    navController: NavController
) {
    // Full-screen background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                SmallTopAppBar(
                    // Centered ACleda logo
                    title = {
                        Image(
                            painter = painterResource(R.drawable.im_acleda_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .height(40.dp)
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.White
                    )
                )
            },
            bottomBar = {
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(30.dp)
                            )
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Home
                        NavButton(Icons.Outlined.Home, "Home") {
                            navController.navigate(Screen.Home.route) {
                                // popUpTo to avoid stacking too many copies, if you like:
                                popUpTo(Screen.Home.route) { inclusive = true }
                            }
                        }

                        // Repeat → transfer screen again
                        NavButton(Icons.Default.Refresh, "Repeat") {
                            navController.navigate(Screen.Transfer.route) {
                                popUpTo(Screen.Transfer.route) { inclusive = true }
                            }
                        }

                        // Accounts
                        NavButton(Icons.Default.AccountBox, "Accounts") {
                            //                        navController.navigate(Screen.Notification.route)
                        }

                        // Share (– you’ll need to implement your own share logic here)
                        NavButton(Icons.Default.Share, "Share") {
                            // e.g. show a share sheet or navController.navigate(...)
                        }
                    }
                }
            }

        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    // White card with rounded top corners
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        // Empty Box just to give the card some height
                        Box(modifier = Modifier.height(210.dp))
                    }

                    // “Carve” image that peeks under the card
                    Image(
                        painter = painterResource(R.drawable.carve1),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(49.dp) // adjust to match your carve graphic
                    )
                    Box(
                        modifier = Modifier
                            .height(320.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .background(
                                color = Color.White
                            )

                    ) {}
                    Image(
                        painter = painterResource(id = R.drawable.bot_carve),
                        contentDescription = "fadsfadsdsf",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                        //                        .height(.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(
                                Navy,
                                shape = RoundedCornerShape(100)
                            )
                    ){
                        SuccessAnimation()
                    }
                    Text(
                        text = "Success",
                        color = Color.Black,
                        modifier =  Modifier
                            .padding(top = 10.dp)
                    )
                    Box(
                        modifier = Modifier
                            .height(110.dp)
                            .fillMaxWidth()
                            .padding(start = 38.dp, top = 4.dp, end = 38.dp,)
                            .background(
                                color = Color.Gray.copy(alpha = .1f),
                                shape = RoundedCornerShape(15.dp)
                            )
                    ){
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 16.dp)
                        ){
                            Box(modifier = Modifier
                                .padding(start = 10.dp)
                                .size(50.dp)
//                        .padding(start = 20.dp)
                                .background(
                                    color = AmberColor,
                                    shape = RoundedCornerShape(100.dp)
                                ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = initials,
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.W400,
                                    textAlign = TextAlign.Center,
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .height(100.dp)
                            ) {
                                Text(
                                    text = "Transferred to",
                                    fontSize = 10.sp,
                                    color = Color.Black
                                )
                                Text(
                                    text = "SEANG SENGLEAPH",
                                    fontSize = 14.sp,
                                    color = Navy,
                                    style = TextStyle(fontWeight = FontWeight.W500)
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "4,002",
                                        fontSize = 20.sp,
                                        color = Pink40,
                                        style = TextStyle(fontWeight = FontWeight.W500)
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_riel_cash),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(15.dp),
                                        tint = Pink40
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 38.dp)
                            .background(
                                color = Color.Gray.copy(alpha = .1f),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .height(300.dp)
                            .fillMaxWidth()
                    ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                // Left column
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "From Account",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                    Text(
                                        text = "Account No.",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                    Text(
                                        text = "Debit Amount",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )

                                    // dashed divider

                                }
                                // Right column
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = ": SEANG SENGLEAP",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                    Text(
                                        text = ": 017 350 216 (USD)",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                    Text(
                                        text = ": -1.00 USD",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.W400,
                                        color = Color.Red
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth()
                                    .height(3.dp)
                                    .drawBehind {
                                        val stroke = 1.dp.toPx()
                                        drawLine(
                                            color = Color.Gray,
                                            start = Offset(0f, size.height),
                                            end = Offset(size.width, size.height),
                                            strokeWidth = stroke,
                                            pathEffect = PathEffect.dashPathEffect(
                                                floatArrayOf(20f, 20f),
                                                0f
                                            )
                                        )
                                    }
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                // Left column
                                Column(
                                    modifier = Modifier

                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "To Account",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                    Text(
                                        text = "Account No.",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                }
                                Spacer(modifier = Modifier.width(15.dp))
                                // Right column
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = ": SEANG SENGLEAP",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                    Text(
                                        text = ": 017 350 216 (KHR)",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth()
                                    .height(3.dp)
                                    .drawBehind {
                                        val stroke = 1.dp.toPx()
                                        drawLine(
                                            color = Color.Gray,
                                            start = Offset(0f, size.height),
                                            end = Offset(size.width, size.height),
                                            strokeWidth = stroke,
                                            pathEffect = PathEffect.dashPathEffect(
                                                floatArrayOf(20f, 20f),
                                                0f
                                            )
                                        )
                                    }
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                // Left column
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Exchange Rate",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                    Text(
                                        text = "Reference No.",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                    Text(
                                        text = "Date            ",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )

                                    // dashed divider

                                }
                                // Right column
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = ": 1 USD = 4,002 KHR",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                    Text(
                                        text = ": 51131287678",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                    Text(
                                        text = ": 23-Apr-2025 | 11:02 AM",
                                        fontSize = 12.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.W400
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NavButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(icon, contentDescription = label, tint = Color.Blue.copy(alpha = .5f))
        }
        Text(label, style = TextStyle(
            color = Color.Black,
            fontSize = 11.sp
        )
        )
    }
}

@Composable
fun SuccessAnimation() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.success)
    )
    // 2) animate it (here, infinite loop)
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )
    // 3) display
    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier
//            .size(1000.dp)
    )
}



