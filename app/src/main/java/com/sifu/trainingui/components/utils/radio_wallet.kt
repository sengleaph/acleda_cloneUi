//package com.sifu.trainingui.components.utils
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.RadioButton
//import androidx.compose.material3.RadioButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun WalletRadioItem(
//    label: String,
//    subtitle: String,
//    selected: Boolean,
//    onSelect: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .clickable(onClick = onSelect)
//            .padding(vertical = 12.dp, horizontal = 8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column(modifier = Modifier.weight(1f)) {
//            Text(text = label, style = MaterialTheme.typography.bodyLarge)
//            Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
//        }
//        RadioButton(
//            selected = selected,
//            onClick = onSelect,
//            modifier = Modifier.size(24.dp),
//            colors = RadioButtonDefaults.colors(
//                selectedColor = MaterialTheme.colorScheme.primary,
//                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//        )
//    }
//}
//
//@Composable
//fun RadioItem(
//    option: RadioOption,
//    selected: Boolean,
//    onSelect: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .clickable(onClick = onSelect)
//            .padding(vertical = 8.dp, horizontal = 12.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column(modifier = Modifier.weight(1f)) {
//            Text(text = option.label, style = MaterialTheme.typography.bodyLarge)
//            Text(text = option.subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
//        }
//        RadioButton(
//            selected = selected,
//            onClick  = onSelect
//        )
//    }
//}
//
//@Composable
//fun RadioGroup(
//    options: List<Int>,
//    selectedIndex: Int,
//    onSelectionChanged: (Int) -> Unit,
//    modifier: Modifier = Modifier,
//    contentPadding: PaddingValues = PaddingValues(16.dp),
//    verticalSpacing: Dp = 8.dp
//) {
//    Column(modifier = modifier.padding(contentPadding)) {
//        options.forEachIndexed { index, option ->
//            RadioItem(
//                option   = option,
//                selected = (index == selectedIndex),
//                onSelect = { onSelectionChanged(index) }
//            )
//            if (index < options.lastIndex) {
//                Spacer(modifier = Modifier.height(verticalSpacing))
//            }
//        }
//    }
//}
//
//// 1️⃣ Data model for each option
//data class RadioOption(
//    val label: String,
//    val subtitle: String
//)