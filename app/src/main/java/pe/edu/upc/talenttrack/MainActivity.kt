// File: MainActivity.kt   (reemplaza el existente completo)
package pe.edu.upc.talenttrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import pe.edu.upc.talenttrack.core.designsystem.theme.TalentTrackTheme
import pe.edu.upc.talenttrack.features.main.presentation.MainScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TalentTrackTheme(dynamicColor = false) {
                MainScreen()
            }
        }
    }
}