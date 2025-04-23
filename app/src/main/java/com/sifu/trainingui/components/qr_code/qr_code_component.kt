package com.sifu.trainingui.components.qr_code

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

@Composable
fun QRCodeImage(
    data: String,
    size: Dp,
    margin: Int = 1,
    modifier: Modifier = Modifier
) {
    // only regenerate when `data` or `size` changes
    val bitmap: Bitmap = remember(data, size, margin) {
        generateQRCode(data, size.value.toInt(), margin)
    }
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "QR code for $data",
        modifier = modifier.size(size),
        contentScale = ContentScale.FillWidth
    )
}

fun generateQRCode(
    text: String,
    sizePx: Int,
    margin: Int = 1
): Bitmap {
    val hints = (
            EncodeHintType.MARGIN to margin
            )
    val bitMatrix = QRCodeWriter()
        .encode(text, BarcodeFormat.QR_CODE, sizePx, sizePx)

    val bmp = Bitmap.createBitmap(sizePx, sizePx, Bitmap.Config.ARGB_8888)
    for (x in 0 until sizePx) {
        for (y in 0 until sizePx) {
            bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
        }
    }
    return bmp
}

@Composable
fun KHQRScreen(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
    ) {
        QRCodeImage(
            data = text,
            size = 300.dp,
            margin = 1,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}