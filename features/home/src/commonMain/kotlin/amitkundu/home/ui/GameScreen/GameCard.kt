package amitkundu.home.ui.GameScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage


@Composable
fun GameCard(
    modifier : Modifier = Modifier,
    image : String,
    title : String
){

    Column(
        modifier.fillMaxWidth()
            .padding(12.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.displayMedium.copy(
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))

        Spacer(modifier = Modifier.height(16.dp))

    }
}