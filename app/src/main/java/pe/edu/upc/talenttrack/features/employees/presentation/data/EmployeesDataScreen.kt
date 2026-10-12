package pe.edu.upc.talenttrack.features.employees.presentation.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.edu.upc.talenttrack.core.util.toCurrency
import pe.edu.upc.talenttrack.features.employees.presentation.components.EditEmployeeDialog
import pe.edu.upc.talenttrack.features.employees.presentation.components.EmployeeDataCard

/**
 * Saved employees (Room) with edit / delete and the total salary (SharedPreferences)
 * shown at the end of the list.
 */
@Composable
fun EmployeesDataScreen(
    modifier: Modifier = Modifier,
    viewModel: EmployeesDataViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    // Reload every time the tab is shown: the user may have saved employees in the other tab.
    LaunchedEffect(Unit) { viewModel.loadEmployees() }

    LaunchedEffect(uiState.userMessage) {
        uiState.userMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.onUserMessageShown()
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        contentWindowInsets = WindowInsets(0)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))

                uiState.employees.isEmpty() -> Text(
                    text = "No saved employees yet.\nSearch and save some from Find Employees.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )

                else -> LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items = uiState.employees, key = { it.id }) { employee ->
                        EmployeeDataCard(
                            employee = employee,
                            onEditClick = { viewModel.onEditClick(employee) },
                            onDeleteClick = { viewModel.onDeleteClick(employee) }
                        )
                    }
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Total salaries",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = uiState.totalSalary.toCurrency(),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    uiState.employeeToEdit?.let { employee ->
        EditEmployeeDialog(
            employee = employee,
            onDismiss = viewModel::onEditDismiss,
            onConfirm = viewModel::onEditConfirm
        )
    }
}