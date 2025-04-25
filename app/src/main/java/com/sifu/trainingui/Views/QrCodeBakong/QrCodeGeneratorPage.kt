package com.sifu.trainingui.Views.QrCodeBakong

import android.widget.RadioGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.RadioButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sifu.trainingui.R
import com.sifu.trainingui.components.qr_code.KHQRScreen
import com.sifu.trainingui.components.qr_code.saveShareComponent
import com.sifu.trainingui.components.utils.CardComponent
import com.sifu.trainingui.ui.theme.AmberColor
import com.sifu.trainingui.ui.theme.AmberTransparent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun KHQRScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var showCard by remember { mutableStateOf(false) }
    var selectedAccount by remember { mutableStateOf(0) } // 0 = KHR, 1 = USD

    // right above:
    val qrData = if (selectedAccount == 0) {
        // KHR payload
        "00020101021129360009khqr@aclb01090173502160206ACLEDA3920001185518405506010" +
                "145204200053031165802KH5914SEANG SENGLEAP6010PHNOM PENH621302090173502166304E527"
    } else {
        // USD payload
        "00020101021129360009khqr@aclb01090173502160206ACLEDA3920001185518405506010145204200053038405802" +
                "KH5914SEANG SENGLEAP6010PHNOM PENH621302090173502166304F4E8"
    }

    // Trigger the slide‑in once the screen appears
    LaunchedEffect(Unit) { showCard = true }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(20.dp),
        scrimColor = Color.Transparent,
        sheetContent = {
            AccountSelectionSheet(
                selected        = selectedAccount,
                onSelectAccount = { idx ->
                    selectedAccount = idx
                    scope.launch { sheetState.hide() }
                }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
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
                        IconButton(
                            onClick = {
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
                        IconButton(onClick = { /* doSomething() */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_acleda_icon),
                                contentDescription = "ACLEDA Icon",
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(end = 20.dp)
                            )
                        }
                    },
                    backgroundColor = Color.White.copy(alpha = 0.0f),
                    elevation = 0.dp
                )
            },
            content = {
                innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .paint(
                            painter = painterResource(id = R.drawable.background),
                            contentScale = ContentScale.Crop,
                        )
                ) {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxWidth()
                        .align(Alignment.Center)
                ) {
                    AnimatedVisibility(
                        visible = showCard,
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight -> fullHeight/2 },
                            animationSpec  = tween(
                                durationMillis = 500,
                                easing         = FastOutSlowInEasing
                            )
                        ),
                        exit = slideOutVertically(
                            targetOffsetY  = { fullHeight -> fullHeight },
                            animationSpec  = tween(durationMillis = 300)
                        )
                    ) {
                        // QR Card
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                                .padding(horizontal = 16.dp)
                        )  {
                            Column(
                                modifier = Modifier
                                    .width(300.dp)
                                    .align(Alignment.Center)
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.im_khqr),
                                    contentDescription = "KhQR",
                                    modifier = Modifier
                                        .size(300.dp, 50.dp)
                                        .fillMaxWidth()
                                        .background(
                                            color = Color.Red,
                                            shape = RoundedCornerShape(
                                                topEnd = 10.dp,
                                                topStart = 10.dp
                                            )
                                        )
                                        .padding(10.dp)
                                        .align(Alignment.CenterHorizontally),

                                    )
                                Column() {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "SEANG SENGLEAP",
                                            fontSize = 20.sp,
                                            color = Color.Black,
                                            fontWeight = FontWeight.W400,
                                            modifier = Modifier
                                                .align(Alignment.CenterStart)
                                                .height(50.dp)
                                                .padding(start = 30.dp, top = 10.dp)
                                        )
                                        // 2) A Canvas below it to draw the triangle:
                                        Canvas(
                                            modifier = Modifier
                                                .size(30.dp)
                                                .align(Alignment.CenterEnd)
                                                .offset(y = (-10).dp)
                                        ) {
                                            val path = Path().apply {
                                                // start at bottom-center of canvas
                                                moveTo(size.width, size.height)
                                                // top-left
                                                lineTo(0f, 0f)
                                                // top-right
                                                lineTo(size.width, 0f)
                                                close()
                                            }
                                            drawPath(path, color = Color.Red)
                                        }

                                    }

                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = 30.dp),
                                        verticalAlignment = Alignment.Bottom,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Text(
                                            text = "0",
                                            fontSize = 20.sp,
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold,
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = "KHR",
                                            color = Color.Black,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.W400,
                                        )
                                    }
                                }

                                Box(
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .fillMaxWidth()
                                        .height(3.dp)
                                        .drawBehind {
                                            val stroke = 3.dp.toPx()
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

                                Box() {

                                    // 1) Place the QR-code composable as a child
                                    KHQRScreen(
                                        text = qrData,
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                    )

                                    // 2) Overlay your icon in a corner
                                    Icon(
                                        painter = painterResource(
                                            id = if (selectedAccount == 0)
                                                R.drawable.ic_qr_riel
                                            else
                                                R.drawable.dollar_coin // <-- your $ icon resource
                                        ),
                                        contentDescription = if (selectedAccount == 0) "Riel" else "Dollar",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            //                                .height(50.dp)
                                            .size(40.dp)
                                            .align(Alignment.Center)
                                            .background(
                                                color = Color.White,
                                                shape = RoundedCornerShape(50.dp)
                                            )

                                            .padding(1.dp)
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .height(10.dp)
                                        .clip(
                                            shape = RoundedCornerShape(
                                                bottomStart = 10.dp,
                                                bottomEnd = 10.dp
                                            )
                                        )
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 55.dp)
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(
                                color = AmberTransparent,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    // Handle click event here
                                    scope.launch {
                                        if (sheetState.isVisible) sheetState.hide() else sheetState.show()
                                    }
                                }
                                .padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Wallet KHR || 017 350 216",
                                fontSize = 15.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_drop_down),
                                contentDescription = "Scan QR",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    DotSaveShareFeature()
                }
            }
        })
    }
    }

@Composable
private fun AccountSelectionSheet(onClose: () -> Unit) {
    var selected by remember { mutableStateOf(0) }
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(AmberColor),
        ) {
            Text(
                "Select Account to Receive",
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.Center),
//                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
        }
        Spacer(Modifier.height(12.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            RadioItem(
                label = "KHR || 017 350 216",
                subtitle = "Wallet",
                selected = (selected == 0),
                onSelect = { selected = 0 }
            )
            Spacer(Modifier.height(8.dp))
            RadioItem(
                label = "USD || 017 350 216",
                subtitle = "Wallet",
                selected = (selected == 1),
                onSelect = { selected = 1 }
            )
        }
    }
}

@Composable
private fun RadioItem(
    label: String,
    subtitle: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) Color(0xFFC8E8FF) else Color.Transparent)
            .clickable(onClick = onSelect)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onSelect)
        Spacer(Modifier.width(12.dp))
        Column {
            Text(label, fontSize = 16.sp)
            Text(subtitle, fontSize = 12.sp, color = Color.Black)
        }
    }
}

@Composable
fun DotSaveShareFeature(
    items: List<CardComponent> = saveShareComponent
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .wrapContentWidth(Alignment.CenterHorizontally),
        horizontalArrangement = Arrangement.spacedBy(50.dp),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    ) {
        items(items) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier.size(50.dp),
                    colors = CardDefaults.cardColors(containerColor = AmberColor),
                    shape = RoundedCornerShape(50.dp),
                ) {
                    // Box to center the Icon in the Card
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = item.iconData),
                            contentDescription = item.iconText,
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = item.iconText,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
private fun AccountSelectionSheet(
    selected: Int,
    onSelectAccount: (Int) -> Unit
) {
    Column {
        Box(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(AmberColor)
        ) {
            Text(
                "Select Account to Receive",
                fontSize   = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color      = Color.White,
                modifier   = Modifier.align(Alignment.Center)
            )
        }

        Spacer(Modifier.height(12.dp))

        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            RadioItem(
                label    = "KHR || 017 350 216",
                subtitle = "Wallet",
                selected = (selected == 0),
                onSelect = { onSelectAccount(0) }
            )
            Spacer(Modifier.height(8.dp))
            RadioItem(
                label    = "USD || 017 350 216",
                subtitle = "Wallet",
                selected = (selected == 1),
                onSelect = { onSelectAccount(1) }
            )
        }
    }
}

@Preview
@Composable
fun KHQRScreenPreview() {
    KHQRScreen(navController = NavHostController(LocalContext.current))
}