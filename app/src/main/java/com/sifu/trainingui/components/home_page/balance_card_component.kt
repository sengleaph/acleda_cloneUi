package com.sifu.trainingui.components.home_page

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.darkColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sifu.trainingui.R
import com.sifu.trainingui.ui.theme.Navy
import com.sifu.trainingui.ui.theme.WhiteTransparent
import com.sifu.trainingui.components.utils.ProgressIconItem
import com.sifu.trainingui.ui.theme.AmberColor
import com.sifu.trainingui.ui.theme.DarkPrimary

@Composable
fun BalanceBank(
    textStyle: TextStyle = TextStyle(color = Navy, fontSize = 20.sp, fontWeight = FontWeight.Medium),
    KhmerCash: Int = 40000,
    UsaCash: Int = 400
) {
    val isBlurred = remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .clickable {  }
            .padding(
                vertical = 10.dp,
                horizontal = 16.dp,
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(10.dp))
//                .blur(10.dp)
                .height(150.dp),
            colors = CardDefaults.cardColors(containerColor = WhiteTransparent),
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ProgressIconItem(
                    tint = Navy,
                    icon = painterResource(id = R.drawable.ic_wallet),
                    label = "Account",
                    progress = 0.4f,
                    size = 100.dp,
                    strokeWidth = 8.dp,
                    trackColor = AmberColor,
                    progressColor = Navy,

                    )
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),

                    ) {
                    Row(
                        modifier = Modifier
                            .clickable { isBlurred.value = !isBlurred.value },
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            text = "Total Balance",
                            style = TextStyle(Navy)
                        )
                        Spacer(modifier = Modifier.fillMaxWidth(.1f))
                        if(isBlurred.value){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_eye_show),
                                contentDescription = "hide balance",
                                Modifier
                                    .size(20.dp),
                                tint = Navy
                            )
                        }else{
                            Icon(
                                painter = painterResource(id = R.drawable.ic_eye_hide),
                                contentDescription = "hide balance",
                                Modifier.size(20.dp),
                                tint = Navy
                            )
                        }
                    }
                    Row(
                    ) {
                        Text(
                            modifier = if (isBlurred.value) Modifier.blur(10.dp) else Modifier,
                            text = "$KhmerCash",
                            style = textStyle,

                            )
//                        Spacer(modifier = Modifier.fillMaxWidth(.1f))
                        Icon(
                            modifier = Modifier
                                .width(20.dp)
                                .size(20.dp),
                            painter = painterResource(id = R.drawable.ic_riel_cash,),
                            contentDescription = "b'rak reil",
                            tint = AmberColor
                        )
                    }
                    Row(
                    ) {
                        Text(
                            modifier = if (isBlurred.value) Modifier.blur(10.dp) else Modifier,
                            text = "$UsaCash",
                            textAlign = TextAlign.End,
                            style = textStyle,

                            )
//                        Spacer(modifier = Modifier.fillMaxWidth(.1f))
                        Icon(
                            modifier = Modifier
                                .width(20.dp)
                                .size(30.dp),
                            tint = Navy,
                            painter = painterResource(id = R.drawable.ic_dollar_cash),
                            contentDescription = "Dollar cash"
                        )
                    }
                }
            }
        }
    }
}

