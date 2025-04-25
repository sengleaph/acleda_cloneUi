import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sifu.trainingui.R

@Composable
fun CustomizeScreen() {
    AppearanceSection(
        onCustomizeClick = {
            // TODO: handle the button tap—navigate, show dialog, etc.
        }
    )
}
@Composable
fun AppearanceSection(onCustomizeClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // 1) full-width background
        Image(
            painter = painterResource(id = R.drawable.ic_acleda_icon_logo),
            contentDescription = "Festival background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(240.dp)
        )

        // 2) Heading in top-left
        Text(
            text = "Appearance",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF003366),
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
        )

        // 3) translucent card overlay
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.8f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // left illustration
                Image(
                    painter = painterResource(id = R.drawable.im_customize),
                    contentDescription = "Artist",
                    modifier = Modifier.size(80.dp)
                )

                // center “toggle” mock-up
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Text(
//                        text = "ិ",
//                        fontSize = 24.sp,
//                        color = Color(0xFF003366)
//                    )
//                    Spacer(modifier = Modifier.width(6.dp))
//                    Box(
//                        modifier = Modifier
//                            .size(8.dp)
//                            .background(Color(0xFF003366), CircleShape)
//                    )
//                    Spacer(modifier = Modifier.width(6.dp))
//                    Text(
//                        text = "គ",
//                        fontSize = 36.sp,
//                        color = Color(0xFF003366)
//                    )
//                }

                // right “Customize” button
                Button(
                    onClick = onCustomizeClick,
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Customize",
                        color = Color(0xFF003366),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Costom() {
    CustomizeScreen()
}