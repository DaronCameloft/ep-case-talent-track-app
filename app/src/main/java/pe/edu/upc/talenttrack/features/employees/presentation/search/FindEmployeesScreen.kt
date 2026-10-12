package pe.edu.upc.talenttrack.features.employees.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.edu.upc.talenttrack.features.employees.presentation.components.EmployeeDetailDialog
import pe.edu.upc.talenttrack.features.employees.presentation.components.EmployeeSearchItem

/**
 * Search employees by profession (API) and save them to Room.
 */
@Composable
fun FindEmployeesScreen(
    modifier: Modifier = Modifier,
    viewModel: FindEmployeesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    // Every time the tab is shown, sync the hearts with what is really in Room.
    LaunchedEffect(Unit) { viewModel.refreshSavedIds() }

    // One-shot messages: show once, then clear so they don't repeat.
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = uiState.query,
                    onValueChange = viewModel::onQueryChange,
                    label = { Text("Profession (e.g. abogado)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { viewModel.onSearchClick() }),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = viewModel::onSearchClick) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Search")
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                when {
                    uiState.isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))

                    uiState.errorMessage != null -> Text(
                        text = "Error: ${uiState.errorMessage}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    uiState.hasSearched && uiState.employees.isEmpty() -> Text(
                        text = "No employees found",
                        modifier = Modifier.align(Alignment.Center)
                    )

                    !uiState.hasSearched -> Text(
                        text = "Type a profession and press Search",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    else -> LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(items = uiState.employees, key = { it.id }) { employee ->
                            EmployeeSearchItem(
                                employee = employee,
                                isSaved = employee.id in uiState.savedIds,
                                onDetailClick = { viewModel.onDetailClick(employee) },
                                onSaveClick = { viewModel.onSaveClick(employee) }
                            )
                        }
                    }
                }
            }
        }
    }

    uiState.selectedEmployee?.let { employee ->
        EmployeeDetailDialog(employee = employee, onDismiss = viewModel::onDetailDismiss)
    }
}