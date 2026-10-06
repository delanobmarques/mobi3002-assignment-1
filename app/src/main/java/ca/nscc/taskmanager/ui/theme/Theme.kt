package ca.nscc.taskmanager.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary            = PacificBlue,
    onPrimary          = Color.White,
    primaryContainer   = SkyBlue,
    onPrimaryContainer = StormyTeal,
    secondary          = StormyTeal,
    onSecondary        = Color.White,
    background         = Parchment,
    onBackground       = StormyTeal,
    surface            = Color.White,
    onSurface          = StormyTeal,
    error              = CoralRed,
    onError            = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    primary            = PacificBlue,
    onPrimary          = Color.White,
    primaryContainer   = DarkSurface,
    onPrimaryContainer = SkyBlue,
    secondary          = SkyBlue,
    onSecondary        = StormyTeal,
    background         = DarkSurface,
    onBackground       = Parchment,
    surface            = Color(0xFF243B40),
    onSurface          = Parchment,
    error              = CoralRed,
    onError            = Color.White,
)

@Composable
fun TaskManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = Typography,
        content     = content
    )
}