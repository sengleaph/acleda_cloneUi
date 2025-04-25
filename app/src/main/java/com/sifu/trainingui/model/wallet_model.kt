package com.sifu.trainingui.model

import android.service.quickaccesswallet.WalletCard
import com.sifu.trainingui.components.utils.BottomSheetState
import com.sifu.trainingui.components.utils.WalletAmount
import java.util.Currency


val WalletModel = listOf(
    BottomSheetState(
        phoneNumber= "017 350 216",
        subTitle  = "Wallet",
        currency  = "KHR",
        amount    = "175,870",
        onSelect  = {},
    ),
    BottomSheetState(
        phoneNumber= "017 350 216",
        subTitle  = "Wallet",
        currency  = "USA",
        amount    = "41,00",
        onSelect  = {},
    ),
)