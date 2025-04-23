package com.sifu.trainingui.components.utils.navigation_bar

import NavigationItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonNavigationBar(
    items: List<NavigationItem>,
    currentRoute: String,
    onItemSelected: (NavigationItem) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onItemSelected(item) }
                    .padding(vertical = 8.dp)
            ) {
                Icon(
                    painter           = if (item.route == currentRoute) item.selectedIcon else item.unSelectedIcon,
                    contentDescription= item.title,
                    tint              = if (item.route == currentRoute) Color(0xFFFFA000) else Color.Gray,
                    modifier          = Modifier.size(24.dp)
                )
                Text(
                    text = item.title,
                    fontSize = 12.sp,
                    color    = if (item.route == currentRoute) Color(0xFFFFA000) else Color.Gray
                )
            }
        }
    }
}
