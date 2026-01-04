package amitkundu.theme



import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun GameHubTheme(
    content: @Composable () -> Unit
) {
    val isDark = isSystemInDarkTheme()

    MaterialTheme(
        colorScheme =LightColorScheme,// if (isDark) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}
