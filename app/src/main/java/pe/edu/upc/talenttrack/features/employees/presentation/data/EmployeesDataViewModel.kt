package pe.edu.upc.talenttrack.features.employees.presentation.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.edu.upc.talenttrack.features.employees.application.DeleteEmployeeUseCase
import pe.edu.upc.talenttrack.features.employees.application.GetSavedEmployeesUseCase
import pe.edu.upc.talenttrack.features.employees.application.GetTotalSalaryUseCase
import pe.edu.upc.talenttrack.features.employees.application.UpdateEmployeeUseCase
import pe.edu.upc.talenttrack.features.employees.domain.Employee
import javax.inject.Inject

/**
 * Lists the employees saved in Room, edits/deletes them and exposes the salary total
 * kept in SharedPreferences (the repository recomputes it on every insert/update/delete).
 */
@HiltViewModel
class EmployeesDataViewModel @Inject constructor(
    private val getSavedEmployeesUseCase: GetSavedEmployeesUseCase,
    private val updateEmployeeUseCase: UpdateEmployeeUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase,
    private val getTotalSalaryUseCase: GetTotalSalaryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EmployeesDataUiState())
    val uiState: StateFlow<EmployeesDataUiState> = _uiState.asStateFlow()

    /** Reloads the list from Room and the total from SharedPreferences. */
    fun loadEmployees() {
        viewModelScope.launch {
            val employees = getSavedEmployeesUseCase()
            _uiState.update {
                it.copy(
                    employees = employees,
                    totalSalary = getTotalSalaryUseCase(),
                    isLoading = false
                )
            }
        }
    }

    fun onEditClick(employee: Employee) {
        _uiState.update { it.copy(employeeToEdit = employee) }
    }

    fun onEditDismiss() {
        _uiState.update { it.copy(employeeToEdit = null) }
    }

    fun onEditConfirm(updated: Employee) {
        viewModelScope.launch {
            updateEmployeeUseCase(updated)
            _uiState.update { it.copy(employeeToEdit = null, userMessage = "${updated.fullName} updated") }
            loadEmployees()
        }
    }

    fun onDeleteClick(employee: Employee) {
        viewModelScope.launch {
            deleteEmployeeUseCase(employee.id)
            _uiState.update { it.copy(userMessage = "${employee.fullName} deleted") }
            loadEmployees()
        }
    }

    fun onUserMessageShown() {
        _uiState.update { it.copy(userMessage = null) }
    }
}