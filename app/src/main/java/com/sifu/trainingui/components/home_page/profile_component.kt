package com.sifu.trainingui.components.home_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.sifu.trainingui.R
import com.sifu.trainingui.ui.theme.Navy

@Composable
fun ProfileDisplay() {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(40.dp))
                    .size(50.dp),

                painter = painterResource(id = R.drawable.im_profile_image),

                contentDescription = "Profile Display"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = "Hello, SENGLEAPH\nProfile >", style = TextStyle(color = Navy))
            }
        }
    }
}