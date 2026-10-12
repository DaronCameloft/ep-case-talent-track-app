package pe.edu.upc.talenttrack.features.employees.presentation.search

import pe.edu.upc.talenttrack.features.employees.domain.Employee

/**
 * Single immutable snapshot of everything the Find Employees screen needs to draw itself.
 */
data class FindEmployeesUiState(
    val query: String = "",
    val employees: List<Employee> = emptyList(),
    val savedIds: Set<String> = emptySet(),
    val selectedEmployee: Employee? = null,
    val isLoading: Boolean = false,
    val hasSearched: Boolean = false,
    val errorMessage: String? = null,
    val userMessage: String? = null
)