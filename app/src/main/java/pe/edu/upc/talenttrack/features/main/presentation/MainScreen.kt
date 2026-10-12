// File: features/main/presentation/MainScreen.kt
package pe.edu.upc.talenttrack.features.main.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.talenttrack.features.employees.presentation.data.EmployeesDataScreen
import pe.edu.upc.talenttrack.features.employees.presentation.search.FindEmployeesScreen

/** Root scaffold: Home (branding) as start destination plus the bottom navigation bar. */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = { MainNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<HomeRoute> { HomeScreen() }
            composable<FindEmployeesRoute> { FindEmployeesScreen() }
            composable<EmployeesDataRoute> { EmployeesDataScreen() }
        }
    }
}