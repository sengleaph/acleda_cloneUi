package com.sifu.trainingui.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sifu.trainingui.ui.theme.AmberColor
import java.util.Currency

data class BottomSheetState(
    val phoneNumber: String,
    val subTitle: String,
    val currency: String,
    val amount: String,
    val onSelect: () -> Unit,
)

enum class SheetType { Wallet, Favorite, Purpose, Amount}

@Composable
fun WalletAmount(
    phoneNumber: String,
    subTitle: String,
    currency: String,
    amount: String,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onSelect)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = "$phoneNumber",
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = subTitle,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = "$currency",
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp
                ),
                color = AmberColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = amount,
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                ),
                color = AmberColor
            )
        }
    }
}
@Composable
fun SwitchAmount(
    currency: String,
    amount: String,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onSelect)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = currency,
            style = TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 14.sp
            ),
            color = AmberColor
        )
    }
}


@Composable
fun FavoriteUserComponent(
    phoneNumber: String,
    subTitle: String,
    currency: String,
    amount: String,
    onSelect: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clickable(onClick = onSelect)
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
           Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier

           ) {
               Icon(
                   imageVector = Icons.Outlined.AccountBox,
                   contentDescription = "Account Box",
                     tint = AmberColor,
               )
                Spacer(modifier = Modifier.width(8.dp))
               Text(
                   text = "$subTitle",
                   fontWeight = FontWeight.W500,
                   fontSize = 18.sp,
                   color = MaterialTheme.colorScheme.background.copy(alpha = 0.9f)
               )
           }
            Text(text = "$phoneNumber",
                fontWeight = FontWeight.W500,
//                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.9f)
            )
        }
    }
}

@Composable
fun PurposeComponent(
    phoneNumber: String,
    subTitle: String,
    currency: String,
    amount: String,
    onSelect: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clickable(onClick = onSelect)
            .padding(16.dp)
    ) {
        Row(
             verticalAlignment = Alignment.CenterVertically,
             horizontalArrangement = Arrangement.Center,
             modifier = Modifier.fillMaxWidth(1f)
        ) {
            Text(
                text = "$subTitle",
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.9f)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun WalletAmountPreview() {
    WalletAmount(
        phoneNumber= "017 350 216",
        subTitle  = "Wallet",
        currency  = Currency.getInstance("KHM").symbol,
        amount    = "100.000",
//        onSelect  = false,
        onSelect  = {},
    )
}
