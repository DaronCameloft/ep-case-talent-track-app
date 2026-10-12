package pe.edu.upc.talenttrack.features.employees.presentation.data

import pe.edu.upc.talenttrack.features.employees.domain.Employee

/**
 * Snapshot of the saved-employees screen: the Room list, the SharedPreferences total
 * and the employee currently being edited (null when no dialog is open).
 */
data class EmployeesDataUiState(
    val employees: List<Employee> = emptyList(),
    val totalSalary: Double = 0.0,
    val employeeToEdit: Employee? = null,
    val isLoading: Boolean = true,
    val userMessage: String? = null
)