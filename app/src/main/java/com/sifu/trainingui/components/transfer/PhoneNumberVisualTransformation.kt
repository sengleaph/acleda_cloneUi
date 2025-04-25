import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sifu.trainingui.R
import com.sifu.trainingui.ui.theme.AmberColor
import com.sifu.trainingui.ui.theme.Navy
import java.text.NumberFormat
import java.util.Locale

class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // 1) strip non-digits
        val digits = text.text.filter(Char::isDigit)

        // 2) build the formatted string
        val transformed = buildString {
            if (digits.length <= 9) {
                // <=9 digits: 3-3-3 blocks with spaces
                digits.forEachIndexed { i, c ->
                    append(c)
                    if ((i + 1) % 3 == 0 && i != digits.lastIndex) {
                        append(' ')
                    }
                }
            } else {
                // >9 digits: 4-8-2 blocks with dashes
                digits.forEachIndexed { i, c ->
                    append(c)
                    // dash after 4th and after 12th
                    if ((i == 3 || i == 11) && i != digits.lastIndex) {
                        append('-')
                    }
                    // limit to max 14 digits
                    if (i >= 13) return@forEachIndexed
                }
            }
        }

        // 3) cursor mapping
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return if (digits.length <= 9) {
                    // spaces inserted after positions 3 and 6
                    val spacesBefore = (offset / 3).coerceAtMost(2)
                    (offset + spacesBefore).coerceAtMost(transformed.length)
                } else {
                    // dashes inserted after positions 4 and 12
                    var dashesBefore = 0
                    if (offset > 4) dashesBefore++
                    if (offset > 12) dashesBefore++
                    (offset + dashesBefore).coerceAtMost(transformed.length)
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return if (digits.length <= 9) {
                    // subtract spaces at transformed indices 4 and 8
                    val spacesBefore = when {
                        offset <= 3 -> 0
                        offset <= 7 -> 1
                        else        -> 2
                    }
                    (offset - spacesBefore).coerceIn(0, digits.length)
                } else {
                    // subtract dashes at transformed indices 5 and 14
                    val dashesBefore = when {
                        offset <= 4  -> 0
                        offset <= 13 -> 1
                        else         -> 2
                    }
                    (offset - dashesBefore).coerceIn(0, digits.length)
                }
            }
        }

        return TransformedText(AnnotatedString(transformed), offsetMapping)
    }
}

@Composable
fun TransferAmountField(
    selectedCurrency: String,
    onCurrencyToggle: (String) -> Unit
) {
    // 1️⃣ Hold both text + cursor
    var amountState by remember {
        mutableStateOf(
            TextFieldValue(
                text = if (selectedCurrency == "KHR") "000,000" else "0.00",
                selection = TextRange(
                    if (selectedCurrency == "KHR") "000,000".length else "0.00".length
                )
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        val isRiel = selectedCurrency == "KHR"

        OutlinedTextField(
            value = amountState,
            onValueChange = { newValue ->
                // 2️⃣ Keep only digits + dot, then re-format
                var raw = newValue.text.filter { it.isDigit() || it == '.' }
                if (raw.startsWith(".")) raw = "0$raw"

                val formatted = if (isRiel) {
                    // group in thousands: "000,000"
                    runCatching {
                        NumberFormat
                            .getIntegerInstance(Locale.US)
                            .format(raw.toLong())
                    }.getOrNull() ?: raw
                } else {
                    // always two decimals: "0.00"
                    val parts = raw.split('.')
                    val intPart = (parts.getOrNull(0)?.takeIf { it.isNotEmpty() } ?: "0")
                    val fracPart = parts.getOrNull(1)?.take(2).orEmpty()
                    "$intPart.${fracPart.padEnd(2, '0')}"
                }

                // 3️⃣ Reset state with cursor at end
                amountState = TextFieldValue(
                    text = formatted,
                    selection = TextRange(formatted.length)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                color      = Navy,
                fontSize   = 18.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 70.sp
            ),
            trailingIcon = {
                IconButton(
                    onClick = {
                        // 4️⃣ Toggle currency and reset amountState
                        val next = if (selectedCurrency == "KHR") "USA" else "KHR"
                        onCurrencyToggle(next)

                        val defaultText = if (next == "KHR") "000,000" else "0.00"
                        amountState = TextFieldValue(
                            text = defaultText,
                            selection = TextRange(defaultText.length)
                        )
                    },
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, end = 10.dp)
                        .size(50.dp)
                        .background(
                            color = Color.Gray.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isRiel)
                                R.drawable.ic_qr_riel
                            else
                                R.drawable.ic_dollar_cash
                        ),
                        contentDescription = "Toggle currency",
                        tint = AmberColor,
                        modifier = Modifier.size(32.dp)
                    )
                }
            },
            isError = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor   = Navy,
                unfocusedBorderColor = Color.Black.copy(alpha = 0.1f),
                backgroundColor       = Color.White,
                textColor            = Navy
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
    }
}
