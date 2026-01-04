package amitkundu.favorite.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun FavoriteScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .background(Color.Green),

        ) {
        Text("Favorite")
    }
}