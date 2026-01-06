package amitkundu.theme


import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6750A4),
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    onPrimary = Color.Black,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White
)

// Color Definitions
 val PrimaryBlue = Color(0xFF3B82F6)
 val SecondaryPurple = Color(0xFF8B5CF6)
 val BackgroundDark = Color(0xFF0F172A)
 val SurfaceDark = Color(0xFF1E293B)
 val CardBackground = Color(0xFF1E293B)
 val BorderColor = Color(0xFF334155)
 val AccentOrange = Color(0xFFF59E0B)
 val SuccessGreen = Color(0xFF10B981)
 val ErrorRed = Color(0xFFEF4444)
 val TextPrimary = Color(0xFFFFFFFF)
 val TextSecondary = Color(0xFF94A3B8)
 val TextTertiary = Color(0xFF64748B)
 val StarYellow = Color(0xFFFBBF24)
 val TrendingBadge = Color(0xFFFFB800)
 val FavoriteButtonBg = Color(0xFF2D3748)

// Gradient Definitions
 val CardGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF5B6EE1),
        Color(0xFF6B46C1)
    )
)
