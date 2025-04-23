package com.sifu.trainingui.Views.TransferPage.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sifu.trainingui.R
import com.sifu.trainingui.components.utils.navigation_bar.Screen
import com.sifu.trainingui.ui.theme.AmberColor
import com.sifu.trainingui.ui.theme.Navy
import com.sifu.trainingui.ui.theme.WhiteTransparent
import com.sifu.trainingui.ui.theme.WhiteTransparent40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmTransferScreen(
    navController : NavHostController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop,
            )
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "My KHQR",
                            style = TextStyle(
                                Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W400
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(30.dp),
                                tint = Color.White,
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    actions = {
                        Image(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(end = 20.dp),
                            painter = painterResource(id = R.drawable.ic_acleda_icon),
                            contentDescription = "Acleda Logo",
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White,
                        navigationIconContentColor = Color.White,) ,
                )
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)

                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(top = 20.dp)
                                .background(WhiteTransparent)
                        ){
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Column(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .padding(start = 50.dp),
                                    verticalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(text = "SEANG SENGLEAPH",
                                        modifier = Modifier,
                                        style = TextStyle(
                                            color = Navy,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.W500
                                        ),
                                    )
                                    Text(text = "017 350 216 (KHR)",
                                        modifier = Modifier,
                                        style = TextStyle(
                                            color = Navy,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.W400
                                        ),
                                    )
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .height(260.dp)
                                .fillMaxWidth()
                                .background(WhiteTransparent40)
                        ){
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .background(color = Color.White.copy(alpha = 0.2f)),
                            ){
                                Row(
                                    modifier = Modifier
                                        .height(100.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        text = "Amount",
                                        style = TextStyle(
                                            color = Navy,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.W400
                                        ),
                                        modifier = Modifier
                                            .padding(start = 20.dp),
                                    )
                                    Text(
                                        text = "4,002 KHR",
                                        modifier = Modifier,
                                        style = TextStyle(
                                            color = Color.Red.copy(alpha = 8f),
                                            fontSize = 28.sp,
                                            fontWeight = FontWeight.W500
                                        ),
                                    )
                                    Spacer(modifier = Modifier.width(50.dp))
                                }
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = WhiteTransparent,
                                thickness = 1.dp
                            )
                            Box(
                                modifier = Modifier
                                    .height(80.dp)
                                    .fillMaxWidth()
                                    .background(color = Color.White.copy(alpha = 0.2f)),
                            ){
                                Row(
                                    modifier = Modifier
                                        .height(80.dp)
                                        .padding(vertical = 16.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.Top,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        text = "Transfer From",
                                        style = TextStyle(
                                            color = Navy,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.W400
                                        ),
                                        modifier = Modifier
                                            .padding(start = 20.dp),
                                    )
                                    Column(
                                        modifier = Modifier
                                            .height(50.dp),
                                        verticalArrangement = Arrangement.SpaceBetween,

                                    ) {
                                        Text(
                                            text = "SEANG SENGLEAPH",
                                            modifier = Modifier,
                                            style = TextStyle(
                                                color = Navy,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.W500
                                            ),
                                        )
                                        Text(
                                            text = "017 350 216 (USD)",
                                            modifier = Modifier,
                                            style = TextStyle(
                                                color = Navy,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.W400
                                            ),
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(100.dp))
                                }
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = WhiteTransparent,
                                thickness = 1.dp
                            )
                            Box(
                                modifier = Modifier
                                    .height(80.dp)
                                    .fillMaxWidth()
//                                    .background(color = Color.White.copy(alpha = 0.2f)),
                            ){
                                Row(
                                    modifier = Modifier
                                        .height(80.dp)
                                        .padding(vertical = 16.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.Top,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 20.dp)
                                            .height(100.dp),
                                        verticalArrangement = Arrangement.SpaceBetween,

                                        ) {
                                        Text(
                                            text = "Exchange Rate",
                                            modifier = Modifier,
                                            style = TextStyle(
                                                color = Navy,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.W400
                                            ),
                                        )
                                        Text(
                                            text = "Debit Amount",
                                            modifier = Modifier,
                                            style = TextStyle(
                                                color = Navy,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.W400
                                            ),
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .height(100.dp),
                                        verticalArrangement = Arrangement.SpaceBetween,

                                        ) {
                                        Text(
                                            text = "1 USD = 4,002 KHR",
                                            modifier = Modifier,
                                            style = TextStyle(
                                                color = Navy,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.W400
                                            ),
                                        )
                                        Text(
                                            text = "-1.00 USD",
                                            modifier = Modifier,
                                            style = TextStyle(
                                                color = Navy,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.W500
                                            ),
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(110.dp))
                                }
                            }
                        }
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = WhiteTransparent,
                            thickness = 1.dp
                        )
                    }
                    Box(modifier = Modifier
                        .padding(start = 10.dp)
                        .size(120.dp)
//                        .padding(start = 20.dp)
                        .background(
                            color = AmberColor,
                            shape = RoundedCornerShape(90000.dp)
                        )
                        .clickable {
                            navController.navigate(Screen.ConfirmTransfer.route) {
                                popUpTo(Screen.ConfirmTransfer.route) {
                                    inclusive = true
                                }
                            }
                        },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = initials,
                            color = Color.White,
                            fontSize = 50.sp,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
                        .background(
                            color = AmberColor,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable {
                            navController.navigate(Screen.SuccessTransfer.route) {
                                popUpTo(Screen.ConfirmTransfer.route) {
                                    inclusive = true
                                }
                            }
                        }
                        .height(60.dp),
                ) {
                    Text(
                        text = "Transfer",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Center,
                        modifier = Modifier

                            .fillMaxSize()
                            .padding(vertical = 12.dp)
                    )
                }
            }
        )
    }
}

fun extractInitials(fullName: String): String {
    return fullName
        .trim()
        .split("\\s+".toRegex())
        .mapNotNull { it.firstOrNull()?.uppercaseChar() }
        .joinToString("")
}

// Usage:
val name = "Seang Seng"
val initials = extractInitials(name)  // "SS"



//@Preview
//@Composable
//fun ConfirmTransferScreenPreview() {
//    ConfirmTransferScreen(
////        navController = NavHostController()
//    )
//}