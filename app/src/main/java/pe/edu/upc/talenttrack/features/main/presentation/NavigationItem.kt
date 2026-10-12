// File: features/main/presentation/NavigationItem.kt
package pe.edu.upc.talenttrack.features.main.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

/** Destinations shown in the bottom navigation bar. */
sealed class NavigationItem(
    val route: Any,
    val icon: ImageVector,
    val title: String
) {
    data object FindEmployees :
        NavigationItem(FindEmployeesRoute, Icons.Default.Search, "Find Employees")

    data object EmployeesData :
        NavigationItem(EmployeesDataRoute, Icons.Default.Person, "Employees Data")

    companion object {
        // Getter (not a stored list) avoids null entries from sealed-class initialization order
        val tabs: List<NavigationItem> get() = listOf(FindEmployees, EmployeesData)
    }
}