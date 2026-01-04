package amitkundu.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun AndroidSystemBars() {
    val view = LocalView.current
    val isDark = false// isSystemInDarkTheme()

    SideEffect {
        val window = (view.context as Activity).window

        window.statusBarColor =
            if (isDark) Color.Black.toArgb() else Color.White.toArgb()

        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightStatusBars = !isDark
    }
}